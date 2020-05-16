package chartpanel;

import de.gsi.chart.axes.spi.LogarithmicAxis;
import de.gsi.chart.plugins.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.application.Application;
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
        HBox scope = new HBox();
        scope.setSpacing(10);

        DefaultNumericAxis xAxis = new DefaultNumericAxis();
        DefaultNumericAxis yAxis = new DefaultNumericAxis();
        XYChart plot1 = new XYChart(xAxis, yAxis);

        plot1.getPlugins().add(new Zoomer());
        plot1.getPlugins().add(new EditAxis());
        //plot1.getPlugins().add(new EditDataSet());
        plot1.getPlugins().add(new DataPointTooltip());
        //plot1.getPlugins().add(new UpdateAxisLabels());

        GridPane scopeControls = new GridPane();
        scopeControls.setPadding(new Insets(10, 10, 10, 10));
        scopeControls.setVgap(5);
        scopeControls.setHgap(5);

        GridPane diodeControls = new GridPane();
        diodeControls.setPadding(new Insets(10, 10, 10, 10));
        diodeControls.setVgap(5);
        diodeControls.setHgap(5);

        scope.getChildren().addAll(plot1, scopeControls, diodeControls);

        Simulation sim = new Simulation();
        sim.Volt_Ampere(plot1);

        VBox.setVgrow(plot1, Priority.ALWAYS);
        HBox.setHgrow(plot1, Priority.ALWAYS);

        //SPICE PARAM SET
        {//I_S Saturation current
            Label variable = new Label("IS: ");
            diodeControls.add(variable, 0, 0);
            NumberField varField = new NumberField();
            varField.setPromptText("Saturation current");
            diodeControls.add(varField, 1, 0);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.I_S = varField.getDouble();
                    sim.Volt_Ampere(plot1);
                }
            });
        }
        {//N Emission coefficient
            Label variable = new Label("N: ");
            diodeControls.add(variable, 0, 1);
            NumberField varField = new NumberField();
            varField.setPromptText("Emission coefficient");
            diodeControls.add(varField, 1, 1);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.N = varField.getDouble();
                    sim.Volt_Ampere(plot1);
                }
            });
        }
        {//Rs Ohmic resistance
            Label variable = new Label("Rs: ");
            diodeControls.add(variable, 0, 2);
            NumberField varField = new NumberField();
            varField.setPromptText("Ohmic resistance");
            diodeControls.add(varField, 1, 2);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.RS = varField.getDouble();
                    sim.Volt_Ampere(plot1);
                }
            });
        }
        {//Ikf Forward knee current
            Label variable = new Label("Ikf: ");
            diodeControls.add(variable, 0, 3);
            NumberField varField = new NumberField();
            varField.setPromptText("Forward knee current");
            diodeControls.add(varField, 1, 3);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.IKF = varField.getDouble();
                    sim.Volt_Ampere(plot1);
                }
            });
        }
        {//Xti Forward knee current
            Label variable = new Label("Xti: ");
            diodeControls.add(variable, 0, 4);
            NumberField varField = new NumberField();
            varField.setPromptText("IS temperature exponent");
            diodeControls.add(varField, 1, 4);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.XTI = varField.getDouble();
                    sim.Volt_Ampere(plot1);
                }
            });
        }
        {//Eg Activation energy
            Label variable = new Label("Eg: ");
            diodeControls.add(variable, 0, 5);
            NumberField varField = new NumberField();
            varField.setPromptText("Activation energy");
            diodeControls.add(varField, 1, 5);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.EG = varField.getDouble();
                    sim.Volt_Ampere(plot1);
                }
            });
        }
        {//Cjo Zero-bias junction capacitance
            Label variable = new Label("Cjo: ");
            diodeControls.add(variable, 0, 6);
            NumberField varField = new NumberField();
            varField.setPromptText("Zero-bias junction cap");
            diodeControls.add(varField, 1, 6);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.EG = varField.getDouble();
                    sim.Volt_Ampere(plot1);
                }
            });
        }
        {//M Grading coefficient
            Label variable = new Label("M: ");
            diodeControls.add(variable, 0, 7);
            NumberField varField = new NumberField();
            varField.setPromptText("Grading coefficient");
            diodeControls.add(varField, 1, 7);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.M = varField.getDouble();
                    sim.Volt_Ampere(plot1);
                }
            });
        }
        {//Vj Junction potential
            Label variable = new Label("Vj: ");
            diodeControls.add(variable, 0, 8);
            NumberField varField = new NumberField();
            varField.setPromptText("Junction potential");
            diodeControls.add(varField, 1, 8);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.VJ = varField.getDouble();
                    sim.Volt_Ampere(plot1);
                }
            });
        }
        {//Fc Forward bias depletion capacitance coefficient
            Label variable = new Label("Fc: ");
            diodeControls.add(variable, 0, 9);
            NumberField varField = new NumberField();
            varField.setPromptText("Forward bias depletion capacitance coefficient");
            diodeControls.add(varField, 1, 9);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.FC = varField.getDouble();
                    sim.Volt_Ampere(plot1);
                }
            });
        }
        {//Isr Forward bias depletion capacitance coefficient
            Label variable = new Label("Isr: ");
            diodeControls.add(variable, 0, 10);
            NumberField varField = new NumberField();
            varField.setPromptText("Recombination current");
            diodeControls.add(varField, 1, 10);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.I_SR = varField.getDouble();
                    sim.Volt_Ampere(plot1);
                }
            });
        }
        {//Nr Reverse emission coefficient
            Label variable = new Label("Nr: ");
            diodeControls.add(variable, 0, 11);
            NumberField varField = new NumberField();
            varField.setPromptText("Reverse emission coefficient");
            diodeControls.add(varField, 1, 11);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.NR = varField.getDouble();
                    sim.Volt_Ampere(plot1);
                }
            });
        }
        {//Bv Reverse breakdown voltage
            Label variable = new Label("Bv: ");
            diodeControls.add(variable, 0, 12);
            NumberField varField = new NumberField();
            varField.setPromptText("Reverse breakdown voltage");
            diodeControls.add(varField, 1, 12);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.BV = varField.getDouble();
                    sim.Volt_Ampere(plot1);
                }
            });
        }
        {//Ibv Reverse breakdown current
            Label variable = new Label("Ibv: ");
            diodeControls.add(variable, 0, 13);
            NumberField varField = new NumberField();
            varField.setPromptText("Reverse breakdown current");
            diodeControls.add(varField, 1, 13);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.IBV = varField.getDouble();
                    sim.Volt_Ampere(plot1);
                }
            });
        }
        {//Tt Transit time
            Label variable = new Label("Tt: ");
            diodeControls.add(variable, 0, 14);
            NumberField varField = new NumberField();
            varField.setPromptText("Transit time");
            diodeControls.add(varField, 1, 14);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.TT = varField.getDouble();
                    sim.Volt_Ampere(plot1);
                }
            });
            /*
            Button Set = new Button("Set");
            diodeControls.add(Set, 2, 13);

            Set.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if ((varField.getText() != null && !varField.getText().isEmpty())) {
                        sim.diode.TT = varField.getDouble();
                        sim.Volt_Ampere(plot1);
                    }
                }
            });*/
        }

        {//Scope settings
            {
                Label variable = new Label("Xmin: ");
                scopeControls.add(variable, 0, 0);
                NumberField varField = new NumberField();
                varField.setPromptText("Xmin");
                scopeControls.add(varField, 1, 0);

                varField.setOnAction(e -> {
                    if ((varField.getText() != null && !varField.getText().isEmpty())) {
                        sim.Xmin = varField.getDouble();
                        xAxis.set(sim.Xmin, sim.Xmax);
                        sim.Volt_Ampere(plot1);
                        sim.Volt_Ampere(plot1);
                    }
                });

            }
            {
                Label variable = new Label("Xmax: ");
                scopeControls.add(variable, 2, 0);
                NumberField varField = new NumberField();
                varField.setPromptText("Xmax");
                scopeControls.add(varField, 3, 0);

                varField.setOnAction(e -> {
                    if ((varField.getText() != null && !varField.getText().isEmpty())) {
                        sim.Xmax = varField.getDouble();
                        xAxis.set(sim.Xmin, sim.Xmax);
                        sim.Volt_Ampere(plot1);
                        sim.Volt_Ampere(plot1);
                    }
                });
            }
            {
                Label variable = new Label("N: ");
                scopeControls.add(variable, 0, 1);
                IntNumberField varField = new IntNumberField();
                varField.setPromptText("Samples");
                scopeControls.add(varField, 1, 1);

                varField.setOnAction(e -> {
                    if ((varField.getText() != null && !varField.getText().isEmpty())) {
                        sim.N_SAMPLES = varField.getInt();
                        xAxis.set(sim.Xmin, sim.Xmax);
                        sim.Volt_Ampere(plot1);
                        sim.Volt_Ampere(plot1);
                    }
                });
            }


            CheckBox logYAxis = new CheckBox("Log Y axis");
            scopeControls.add(logYAxis, 1, 2);
            logYAxis.setOnAction(e -> {
                if(logYAxis.isSelected()) {
                    yAxis.setLogAxis(true);
                    yAxis.setLogarithmBase(10);
                    sim.Volt_Ampere(plot1);
                }
                else {
                    yAxis.setLogAxis(false);
                    sim.Volt_Ampere(plot1);
                }
            });



        }

        root.getChildren().add(scope);

        PrimaryStage.setTitle("DiodeSim");
        PrimaryStage.setScene(new Scene(root, 1200, 800));
        PrimaryStage.setOnCloseRequest(evt -> System.exit(0));
        PrimaryStage.show();

        /*button1.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                sim.diode.I_S += 1;
                plot1.getAllDatasets().removeAll();
                sim.Volt_Ampere(plot1);
            }
        });*/

    }
}