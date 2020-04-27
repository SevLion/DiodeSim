package JFXapp;

import javafx.scene.chart.XYChart;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;

import java.util.function.Function;

public class Graph {

    private XYChart<Double, Double> graph;
    private double range;

    public Graph(final XYChart<Double, Double> graph, final double range) {
        this.graph = graph;
        //graph.setId("Dplot");
        this.range = range;
    }

    public void plotLine(final Function<Double, Double> function) {
        final XYChart.Series<Double, Double> series = new XYChart.Series<Double, Double>();
        for (double x = -range; x <= range; x = x + 0.01) {
            plotPoint(x, function.apply(x), series);
        }
        graph.getData().add(series);
    }

    private void plotPoint(final double x, final double y, final XYChart.Series<Double, Double> series) {
        series.getData().add(new XYChart.Data<Double, Double>(x, y));
    }

    public void clear() {
        graph.getData().clear();
    }
}