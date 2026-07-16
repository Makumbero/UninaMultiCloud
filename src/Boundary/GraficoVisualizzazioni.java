package Boundary;

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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
        setTitle("Visualizzazioni Profilo");
		setBounds(100, 100, 900, 650);
		setLocationRelativeTo(null);
		setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelControlli = new JPanel();
        comboBox = new JComboBox<String>();
        comboBox.addItem("Giorni");
        comboBox.addItem("Mesi");
        comboBox.addItem("Anni");

        comboBox.addActionListener(e -> {
            String selezione = (String) comboBox.getSelectedItem();
            Date oggi = new Date(System.currentTimeMillis());

            if (selezione.equals("Giorni")) {
                creaGrafico(cLog.GetAccessiPerMese(oggi), "Giorni");
            } else if (selezione.equals("Mesi")) {
                creaGrafico(cLog.GetAccessiPerAnno(oggi), "Mesi");
            } else if (selezione.equals("Anni")) {
                creaGrafico(cLog.GetAllAccessi(), "Anni");
            }
        });
        
        Indietro = new JButton("Indietro");
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

        // Mostro inizialmente un grafico  di default "Giorni"

    }

    /**
     * Conta quanti accessi ci sono per ogni periodo e disegna il grafico.
     * @param accessi   lista di accessi da conteggiare
     * @param modalita  "Giorni", "Mesi" o "Anni": decide sia il raggruppamento
     *                  dei dati sia l'unità mostrata sull'asse X
     */
    public void creaGrafico(List<Accesso> accessi, String modalita) {

        // Due liste "parallele": per ogni periodo trovato, il suo conteggio di accessi.
        // Il tipo di periodo (giorno/mese/anno) cambia in base alla modalità scelta:
        // così con "Anni" i dati vengono raggruppati per anno e non per ogni singolo giorno.
        List<RegularTimePeriod> periodi = new ArrayList<RegularTimePeriod>();
        List<Integer> conteggi = new ArrayList<Integer>();

        for (int i = 0; i < accessi.size(); i++) {
            Accesso accesso = accessi.get(i);
            RegularTimePeriod periodo;

            if (modalita.equals("Mesi")) {
                periodo = new Month(accesso.getData());
            } else if (modalita.equals("Anni")) {
                periodo = new Year(accesso.getData());
            } else {
                periodo = new Day(accesso.getData());
            }

            int posizione = periodi.indexOf(periodo);

            if (posizione == -1) {
                periodi.add(periodo);
                conteggi.add(1);
            } else {
                int valoreAttuale = conteggi.get(posizione);
                conteggi.set(posizione, valoreAttuale + 1);
            }
        }

        TimeSeries serie = new TimeSeries("Accessi");
        for (int i = 0; i < periodi.size(); i++) {
            serie.add(periodi.get(i), conteggi.get(i));
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(serie);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Accessi nel tempo",
                "Data",
                "Numero di accessi",
                dataset
        );

        XYPlot plot = chart.getXYPlot();

        DateAxis asseX = (DateAxis) plot.getDomainAxis();
        if (modalita.equals("Mesi")) {
            asseX.setTickUnit(new DateTickUnit(DateTickUnitType.MONTH, 1));
            asseX.setDateFormatOverride(new SimpleDateFormat("MMM yyyy"));
        } else if (modalita.equals("Anni")) {
            asseX.setTickUnit(new DateTickUnit(DateTickUnitType.YEAR, 1));
            asseX.setDateFormatOverride(new SimpleDateFormat("yyyy"));
        } else {
            asseX.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, 1));
            asseX.setDateFormatOverride(new SimpleDateFormat("dd/MM"));
        }

        NumberAxis asseY = (NumberAxis) plot.getRangeAxis();
        asseY.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        asseY.setNumberFormatOverride(new DecimalFormat("0"));

        ChartPanel chartPanel = new ChartPanel(chart);

        // Rimuovo il grafico precedente 
        panelGrafico.removeAll();
        panelGrafico.setLayout(new BorderLayout());
        panelGrafico.add(chartPanel, BorderLayout.CENTER);

        panelGrafico.revalidate();
        panelGrafico.repaint();
    }
}