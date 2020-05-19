package chartpanel;

import de.gsi.chart.XYChart;
import de.gsi.chart.axes.spi.DefaultNumericAxis;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ScopeControls {
    public void AddControls(Simulation sim, XYChart showingPlot, DefaultNumericAxis xAxis, DefaultNumericAxis yAxis, GridPane scopeControls) {
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
                        sim.simulate(showingPlot);
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
                        sim.simulate(showingPlot);
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
                        sim.simulate(showingPlot);
                    }
                });
            }

            {
                CheckBox logYAxis = new CheckBox("Log Y axis");
                scopeControls.add(logYAxis, 1, 3);
                logYAxis.setOnAction(e -> {
                    if (logYAxis.isSelected()) {
                        yAxis.setLogAxis(true);
                        yAxis.setLogarithmBase(10);
                        sim.Volt_Ampere(showingPlot);
                    } else {
                        yAxis.setLogAxis(false);
                        sim.simulate(showingPlot);
                    }
                });
            }
            {
                ChoiceBox<String> modeSelector = new ChoiceBox();
                modeSelector.getItems().addAll("Volt-Ampere", "Signal response", "Temperature");
                modeSelector.setValue("Volt-Ampere");
                scopeControls.add(modeSelector, 1, 2);

                modeSelector.setOnAction(e -> {
                    if (modeSelector.getValue() == "Volt-Ampere") {
                        sim.voltampere = true;
                        sim.signalresponse = false;
                        sim.simulate(showingPlot);
                    } else if (modeSelector.getValue() == "Signal response") {
                        sim.voltampere = false;
                        sim.signalresponse = true;
                        sim.Transient(showingPlot);
                    } else if (modeSelector.getValue() == "Temperature") {

                    }
                });
            }
        }
    }
}
