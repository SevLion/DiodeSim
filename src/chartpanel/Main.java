package chartpanel;

import de.gsi.chart.XYChart;
import de.gsi.chart.axes.spi.DefaultNumericAxis;
import de.gsi.chart.plugins.DataPointTooltip;
import de.gsi.chart.plugins.EditAxis;
import de.gsi.chart.plugins.UpdateAxisLabels;
import de.gsi.chart.plugins.Zoomer;
import de.gsi.chart.ui.geometry.Side;
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

        //Две оси и график с этими осями
        DefaultNumericAxis xAxis = new DefaultNumericAxis();
        xAxis.setMinorTickCount(5);
        DefaultNumericAxis yAxis = new DefaultNumericAxis();
        yAxis.setMinorTickCount(5);
        XYChart plot = new XYChart(xAxis, yAxis);
        plot.setTitle("ВАХ");

        //Добавляем плагины, чтобы можно было интерактивно взаимодействовать с графиком
        plot.getPlugins().add(new Zoomer());
        plot.getPlugins().add(new EditAxis());
        //plot.getPlugins().add(new EditDataSet());
        plot.getPlugins().add(new DataPointTooltip());
        //plot.getPlugins().add(new UpdateAxisLabels());


        //Таблица с полями для управления графиком
        GridPane scopeControls = new GridPane();
        scopeControls.setPadding(new Insets(10, 10, 10, 10));
        scopeControls.setVgap(5);
        scopeControls.setHgap(5);

        //Таблица с полями для изменения параметров модели
        GridPane diodeControls = new GridPane();
        diodeControls.setPadding(new Insets(10, 10, 10, 10));
        diodeControls.setVgap(5);
        diodeControls.setHgap(5);

        //Добавляем всё на HBox
        scope.getChildren().addAll(plot, scopeControls, diodeControls);

        //Создаём симуляцию и запускаем первый раз
        Simulation sim = new Simulation();
        sim.simulate(plot);

        //Чтобы график растягивался при масштабировании окна
        VBox.setVgrow(plot, Priority.ALWAYS);
        HBox.setHgrow(plot, Priority.ALWAYS);

        //Добавляем поля для ввода
        SpiceControls spiceControls = new SpiceControls();
        spiceControls.AddControls(sim, plot, diodeControls);

        //Добавляем поля для ввода
        ScopeControls scopControls = new ScopeControls();
        scopControls.AddControls(sim, plot, xAxis, yAxis, scopeControls);

        //Отдаём заполненный HBox root
        root.getChildren().add(scope);

        //Параметры окна
        PrimaryStage.setTitle("DiodeSim");
        PrimaryStage.setScene(new Scene(root, 1200, 800));
        PrimaryStage.setOnCloseRequest(evt -> System.exit(0));
        PrimaryStage.show();
    }
}