package Boundary;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class GraficoVisualizzazioni extends JFrame {

    private JPanel panelGrafico;

    public GraficoVisualizzazioni() {
        setTitle("Visualizzazioni Profilo");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelGrafico = new JPanel();
        add(panelGrafico);

        creaGrafico();
    }

    private void creaGrafico() {
        // ✅ Serie temporale
        TimeSeries serie = new TimeSeries("Visualizzazioni");

        // dati esempio (data, valore)
        serie.add(new Day(1, 6, 2026), 100);
        serie.add(new Day(2, 6, 2026), 150);
        serie.add(new Day(3, 6, 2026), 130);
        serie.add(new Day(4, 6, 2026), 180);

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(serie);

        // ✅ Grafico a linee temporale
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Visualizzazioni nel tempo",
                "Data",
                "Visualizzazioni",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(chart);

        panelGrafico.setLayout(new BorderLayout());
        panelGrafico.add(chartPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GraficoVisualizzazioni().setVisible(true);
        });
    }
}
