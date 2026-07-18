package Boundary;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

import Control.ControllerLogin;
import Entity.Accesso;
import Entity.Utente;

public class GraficoVisualizzazioni extends JFrame {

    private JPanel panelGrafico;
    private JPanel panelControlli;
    private JComboBox<String> comboBox;
    ControllerLogin cLog;
    Utente utente;
    private JButton Indietro;

    public GraficoVisualizzazioni(ControllerLogin cLog, Utente utente) {
        this.cLog = cLog;
        this.utente = utente;
		setBounds(100, 100, 900, 650);
		setLocationRelativeTo(null);
		setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panelControlli = new JPanel();
        comboBox = new JComboBox<>();
        comboBox.addItem("Giorni");
        comboBox.addItem("Mesi");
        comboBox.addItem("Anni");
        comboBox.setPreferredSize(new Dimension(190, 40));

        comboBox.addActionListener(e -> {
            String selezione = (String) comboBox.getSelectedItem();
            Date oggi = new Date(System.currentTimeMillis());//crea come oggetto la data di oggi

            if (selezione.equals("Giorni")) {//ogni scelta della combobox mostra versioni diverse del grafico
                creaGrafico(cLog.GetAccessiPerMese(oggi), "Giorni");
            } else if (selezione.equals("Mesi")) {
                creaGrafico(cLog.GetAccessiPerAnno(oggi), "Mesi");
            } else if (selezione.equals("Anni")) {
                creaGrafico(cLog.GetAllAccessi(), "Anni");
            }
        });

        Indietro = new JButton("Indietro");
        Indietro.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        Indietro.setPreferredSize(new Dimension(190, 80));
		Indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cLog.ProfiloToHome();
			}
		});
        panelControlli.add(Indietro);

        panelControlli.add(comboBox);
        getContentPane().add(panelControlli, BorderLayout.SOUTH);

        panelGrafico = new JPanel();
        getContentPane().add(panelGrafico, BorderLayout.CENTER);


    }

    public void creaGrafico(List<Accesso> accessi, String modalita) {

        // Due liste parallele,  periodo e conteggi, il primo contiene le date da mostrare, il secondo quale valore mostrare sull'asse y.
        List<RegularTimePeriod> periodi = new ArrayList<>();
        List<Integer> conteggi = new ArrayList<>();

        for (int i = 0; i < accessi.size(); i++) {
            Accesso accesso = accessi.get(i);
            RegularTimePeriod periodo;//classe di jfreechart, serve a descrivere un periodo di tempo, è astratta.

            if (modalita.equals("Mesi")) {//a seconda della modalità scelta del grafico, salveremo nella lista periodi, mesi anni o giorni
                periodo = new Month(accesso.getData());
            } else if (modalita.equals("Anni")) {
                periodo = new Year(accesso.getData());
            } else {
                periodo = new Day(accesso.getData());
            }

            int posizione = periodi.indexOf(periodo); //salva in posizione indice della lista in cui si trova periodo

            if (posizione == -1) {//se periodo non è presente, lo aggiunge
                periodi.add(periodo);
                conteggi.add(1);
            } else {//se il periodo è presente aggiunge alla lista dei conteggi +1 nella stessa posizione in cui si trova il periodo
                int valoreAttuale = conteggi.get(posizione);
                conteggi.set(posizione, valoreAttuale + 1);
            }
        }

        TimeSeries serie = new TimeSeries("Accessi"); // classe di JFreeChart, riunisce i valori delle due liste parallele create in precedenza.
        
        if (!periodi.isEmpty()) {
            RegularTimePeriod minPeriodo = periodi.get(0);// inizializiamo i due valori
            RegularTimePeriod maxPeriodo = periodi.get(0);
            for (RegularTimePeriod p : periodi) {//scorriamo tutti i periodi per trovate il minimo e il massimo
                if (p.compareTo(minPeriodo) < 0) {//compare to è il metodo di JFreeChart per confrontare due reulartimeperiod, gli operatori unari non bastano
                    minPeriodo = p;				  //se i periodi sono uguali restituisce zero, se p viene prima restituisce -1, se p viene dopo restituisce 1
                }
                if (p.compareTo(maxPeriodo) > 0) {
                    maxPeriodo = p;
                }
            }
 
           
            RegularTimePeriod corrente = minPeriodo;
            while (corrente.compareTo(maxPeriodo) <= 0) {
                int posizione = periodi.indexOf(corrente);
                int valore;
                if (posizione == -1) {
                    // corrente non è tra i periodi  
                    valore = 0;
                } else {
                    // corrente è tra i periodi con accessi: prendo il conteggio già calcolato
                    valore = conteggi.get(posizione);
                }
                serie.add(corrente, valore);
                corrente = corrente.next();//passa al prossimo time period, anche se non era stato creato in passato
            }
        }
       

        TimeSeriesCollection dataset = new TimeSeriesCollection();//classe di JFreeChart necessaria per essere presa in input dal ChartFactory
        dataset.addSeries(serie);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(//classe di JFreeChart, crea effettivamente il grafico
                "Visualizzazioni di "+utente.getUsername(),
                "Data",
                "Visualizzazioni",
                dataset
        );

        XYPlot plot = chart.getXYPlot();

        DateAxis asseX = (DateAxis) plot.getDomainAxis();
        if (modalita.equals("Mesi")) {// a seconda del tipo di grafico, impostiamo un diverso tipo di data mostrata sulle tacche del grafico
            asseX.setTickUnit(new DateTickUnit(DateTickUnitType.MONTH, 1));//forziamo tutte le tacche ad avere un unità di distanza
            asseX.setDateFormatOverride(new SimpleDateFormat("MMM yyyy")); //invece di mostrare la data per intero, mostriamo solo mese e anno
        } else if (modalita.equals("Anni")) {
        	asseX.setTickUnit(new DateTickUnit(DateTickUnitType.YEAR, 1));
            asseX.setDateFormatOverride(new SimpleDateFormat("yyyy"));//mostriamo solo anno
        } else {
            asseX.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, 1));
            asseX.setDateFormatOverride(new SimpleDateFormat("dd/MM"));//mostriamo solo giorno e mese
        }

        NumberAxis asseY = (NumberAxis) plot.getRangeAxis();
        asseY.setStandardTickUnits(NumberAxis.createIntegerTickUnits());// forza l'uso di numeri interi

        ChartPanel chartPanel = new ChartPanel(chart);

        // Rimuovo il grafico precedente
        panelGrafico.removeAll();
        panelGrafico.setLayout(new BorderLayout());
        panelGrafico.add(chartPanel, BorderLayout.CENTER);

        panelGrafico.revalidate();
        panelGrafico.repaint();
    }
}