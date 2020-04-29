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

    private static final int N_SAMPLES = 100000;
    private static  double I_s = 0.0013;
    private static double U_T = 0.026;

    @Override
    public void start(final Stage PrimaryStage) throws Exception {
        final StackPane root = new StackPane();

        final XYChart chart = new XYChart(new DefaultNumericAxis(), new DefaultNumericAxis());
        Button button = new Button("Pull!");
        root.getChildren().add(chart);

        final DoubleDataSet dataSet1 = new DoubleDataSet("data #1");
        chart.getDatasets().addAll(dataSet1);

        final double[] xValues = getRange(-0.1, 0.8, N_SAMPLES);
        final double[] yValues1 = new double[N_SAMPLES];
        for (int i = 0; i < N_SAMPLES; i++) {
            //xValues[n] = n;
            yValues1[i] = I_s*Math.exp(xValues[i]/U_T - 1);
        }
        dataSet1.set(xValues, yValues1);

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

        PrimaryStage.setTitle("DiodeSim");
        PrimaryStage.setScene(new Scene(root, 800, 600));
        PrimaryStage.setOnCloseRequest(evt -> System.exit(0));
        PrimaryStage.show();
    }

    private double[] getRange(final double Xmin, final double Xmax, final int N_SAMPLES) {
        double[] range = new double[N_SAMPLES];
        double inc = Math.abs((Xmax - Xmin)/N_SAMPLES);
        range[0] = Xmin;
        for(int i = 1; i < N_SAMPLES; ++i) {
            range[i] = range[i - 1] + inc;
        }
        return range;
    }
}