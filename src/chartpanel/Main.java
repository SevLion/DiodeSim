package chartpanel;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.layout.StackPane;
import de.gsi.chart.XYChart;
import de.gsi.chart.axes.spi.DefaultNumericAxis;
import de.gsi.dataset.spi.DoubleDataSet;

public class Main extends Application {
    public static void Main(final String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(final Stage PrimaryStage) throws Exception {
        final StackPane root = new StackPane();

        final XYChart chart = new XYChart(new DefaultNumericAxis(), new DefaultNumericAxis());
        //Button button = new Button("Pull!");
        root.getChildren().add(chart);

        Simulation sim = new Simulation();
        sim.Volt_Ampere(chart);

        PrimaryStage.setTitle("DiodeSim");
        PrimaryStage.setScene(new Scene(root, 800, 600));
        PrimaryStage.setOnCloseRequest(evt -> System.exit(0));
        PrimaryStage.show();
    }
}

/*
        Image image = new Image("https://static.scientificamerican.com/sciam/cache/file/5C51E427-1715-44E6-9B14D9487D7B7F2D_source.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(256);
        imageView.setFitWidth(384);

        button.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                imageView.setFitWidth(imageView.getFitWidth()*2);
            }
        });


        root.getChildren().add(imageView);
        root.getChildren().addAll(button);
*/