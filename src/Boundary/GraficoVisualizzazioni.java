package Boundary;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import Control.ControllerLogin;
import Entity.Accesso;
import Entity.Utente;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraficoVisualizzazioni extends JFrame {

    private JPanel panelGrafico;
    ControllerLogin cLog;
    Utente utente;

    /**
     * Costruttore: riceve la lista di accessi da rappresentare nel grafico.
     */
    public GraficoVisualizzazioni(ControllerLogin cLog,Utente utente) {
    	this.cLog=cLog;
    	this.utente=utente;
        setTitle("Visualizzazioni Profilo");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelGrafico = new JPanel();
        add(panelGrafico);

    }

    /**
     * Conta quanti accessi ci sono per ogni data e disegna il grafico:
     * asse X = data, asse Y = numero di accessi in quella data.
     */
    public void creaGrafico(List<Accesso> accessi) {

        // Due liste "parallele": per ogni giorno trovato, il suo conteggio di accessi.
        // Es: giorni.get(0) = 3 giugno 2026, conteggi.get(0) = 2 accessi in quel giorno
        List<Day> giorni = new ArrayList<Day>();
        List<Integer> conteggi = new ArrayList<Integer>();

        // Scorro tutti gli accessi e per ognuno controllo se il suo giorno
        // è già presente nella lista "giorni"
        for (int i = 0; i < accessi.size(); i++) {
            Accesso accesso = accessi.get(i);
            Day giornoDiQuestoAccesso = new Day(accesso.getData());

            int posizione = giorni.indexOf(giornoDiQuestoAccesso);

            if (posizione == -1) {
                // Non l'ho ancora visto: lo aggiungo con conteggio 1
                giorni.add(giornoDiQuestoAccesso);
                conteggi.add(1);
            } else {
                // L'ho già visto: aumento di 1 il conteggio già presente
                int valoreAttuale = conteggi.get(posizione);
                conteggi.set(posizione, valoreAttuale + 1);
            }
        }

        // Creo la serie temporale con i dati calcolati sopra
        TimeSeries serie = new TimeSeries("Accessi");
        for (int i = 0; i < giorni.size(); i++) {
            serie.add(giorni.get(i), conteggi.get(i));
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(serie);

        // Grafico a linee temporale (come nella versione originale)
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Accessi nel tempo",
                "Data",
                "Numero di accessi",
                dataset
        );
        XYPlot plot = chart.getXYPlot();
        DateAxis asseX = (DateAxis) plot.getDomainAxis();
        //asseX.setDateFormatOverride(new SimpleDateFormat("dd/MM/yyyy"));
        asseX.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, 1));
        ChartPanel chartPanel = new ChartPanel(chart);
        panelGrafico.setLayout(new BorderLayout());
        panelGrafico.add(chartPanel, BorderLayout.CENTER);
    }

   
    }
