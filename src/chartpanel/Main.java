package chartpanel;

import de.gsi.chart.XYChart;
import de.gsi.chart.axes.spi.DefaultNumericAxis;
import de.gsi.chart.plugins.DataPointTooltip;
import de.gsi.chart.plugins.EditAxis;
import de.gsi.chart.plugins.Zoomer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
    public static void Main(final String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(final Stage PrimaryStage) throws Exception {
        final StackPane root = new StackPane();
        HBox scope = new HBox();
        scope.setSpacing(10);

        DefaultNumericAxis xAxis = new DefaultNumericAxis();
        DefaultNumericAxis yAxis = new DefaultNumericAxis();
        XYChart plot = new XYChart(xAxis, yAxis);

        plot.getPlugins().add(new Zoomer());
        plot.getPlugins().add(new EditAxis());
        //plot.getPlugins().add(new EditDataSet());
        plot.getPlugins().add(new DataPointTooltip());
        //plot.getPlugins().add(new UpdateAxisLabels());


        GridPane scopeControls = new GridPane();
        scopeControls.setPadding(new Insets(10, 10, 10, 10));
        scopeControls.setVgap(5);
        scopeControls.setHgap(5);

        GridPane diodeControls = new GridPane();
        diodeControls.setPadding(new Insets(10, 10, 10, 10));
        diodeControls.setVgap(5);
        diodeControls.setHgap(5);

        scope.getChildren().addAll(plot, scopeControls, diodeControls);

        Simulation sim = new Simulation();
        sim.simulate(plot);

        VBox.setVgrow(plot, Priority.ALWAYS);
        HBox.setHgrow(plot, Priority.ALWAYS);

        SpiceControls spiceControls = new SpiceControls();
        spiceControls.AddControls(sim, plot, diodeControls);

        ScopeControls scopControls = new ScopeControls();
        scopControls.AddControls(sim, plot, xAxis, yAxis, scopeControls);

        root.getChildren().add(scope);

        PrimaryStage.setTitle("DiodeSim");
        PrimaryStage.setScene(new Scene(root, 1200, 800));
        PrimaryStage.setOnCloseRequest(evt -> System.exit(0));
        PrimaryStage.show();
    }
}