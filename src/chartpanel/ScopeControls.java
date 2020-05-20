package chartpanel;

import de.gsi.chart.XYChart;
import de.gsi.chart.axes.spi.DefaultNumericAxis;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;

public class ScopeControls {
    public void AddControls(Simulation sim, XYChart showingPlot, DefaultNumericAxis xAxis, DefaultNumericAxis yAxis, GridPane scopeControls) {
        //Добавление полей
        {//Scope settings
            {
                //Слева в таблицу добавляем Label, справа - NumberField
                Label variable = new Label("Xmin: ");
                scopeControls.add(variable, 0, 0);
                NumberField varField = new NumberField(sim.Xmin);
                Tooltip tooltip = new Tooltip("Xmin");
                varField.setTooltip(tooltip);
                varField.setEditable(true);
                scopeControls.add(varField, 1, 0);

                //Прерывание при действии с NumberField (нажатие Enter)
                varField.setOnAction(e -> {
                    if ((varField.getText() != null && !varField.getText().isEmpty())) {
                        //Устанавливаем новое значение Xmin из varField
                        sim.Xmin = varField.getDouble();
                        xAxis.set(sim.Xmin, sim.Xmax);
                        //Перестраиваем график с новым Xmin
                        sim.simulate(showingPlot);
                    }
                });

            }
            //Далее аналогично
            {
                Label variable = new Label("Xmax: ");
                scopeControls.add(variable, 2, 0);
                NumberField varField = new NumberField(sim.Xmax);
                Tooltip tooltip = new Tooltip("Xmax");
                varField.setTooltip(tooltip);
                varField.setEditable(true);
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
                IntNumberField varField = new IntNumberField(sim.N_SAMPLES);
                Tooltip tooltip = new Tooltip("Samples count");
                varField.setTooltip(tooltip);
                varField.setEditable(true);
                scopeControls.add(varField, 1, 1);

                varField.setOnAction(e -> {
                    if ((varField.getText() != null && !varField.getText().isEmpty())) {
                        sim.N_SAMPLES = varField.getInt();
                        xAxis.set(sim.Xmin, sim.Xmax);
                        sim.simulate(showingPlot);
                    }
                });
            }

            CheckBox logYAxis = new CheckBox("Log Y axis");
            {
                //Изменение оси на логарифмическую
                scopeControls.add(logYAxis, 3, 2);
                logYAxis.setOnAction(e -> {
                    if (logYAxis.isSelected()) {
                        yAxis.setLogAxis(true);
                        yAxis.setLogarithmBase(10);
                        sim.simulate(showingPlot);
                    } else {
                        yAxis.setLogAxis(false);
                        sim.simulate(showingPlot);
                    }
                });
            }

            Label t1label = new Label("T1: ");
            NumberField t1varField = new NumberField(sim.T1);
            {
                scopeControls.add(t1label, 0, 3);
                t1label.setVisible(false);
                t1varField.setVisible(false);
                Tooltip tooltip = new Tooltip("Temperature");
                t1varField.setTooltip(tooltip);
                t1varField.setEditable(true);
                scopeControls.add(t1varField, 1, 3);

                t1varField.setOnAction(e -> {
                    if ((t1varField.getText() != null && !t1varField.getText().isEmpty())) {
                        sim.T1 = t1varField.getDouble();
                        xAxis.set(sim.Xmin, sim.Xmax);
                        sim.simulate(showingPlot);
                    }
                });
            }

            Label t2label = new Label("T2: ");
            NumberField t2varField = new NumberField(sim.T2);
            {
                scopeControls.add(t2label, 0, 4);
                t2label.setVisible(false);
                t2varField.setVisible(false);
                Tooltip tooltip = new Tooltip("Temperature");
                t2varField.setTooltip(tooltip);
                t2varField.setEditable(true);
                scopeControls.add(t2varField, 1, 4);

                t2varField.setOnAction(e -> {
                    if ((t2varField.getText() != null && !t2varField.getText().isEmpty())) {
                        sim.T2 = t2varField.getDouble();
                        xAxis.set(sim.Xmin, sim.Xmax);
                        sim.simulate(showingPlot);
                    }
                });
            }

            Label t3label = new Label("T2: ");
            NumberField t3varField = new NumberField(sim.T3);
            {
                scopeControls.add(t3label, 0, 5);
                t3label.setVisible(false);
                t3varField.setVisible(false);
                Tooltip tooltip = new Tooltip("Temperature");
                t3varField.setTooltip(tooltip);
                t3varField.setEditable(true);
                scopeControls.add(t3varField, 1, 5);

                t3varField.setOnAction(e -> {
                    if ((t3varField.getText() != null && !t3varField.getText().isEmpty())) {
                        sim.T3 = t3varField.getDouble();
                        xAxis.set(sim.Xmin, sim.Xmax);
                        sim.simulate(showingPlot);
                    }
                });
            }

            ChoiceBox<String> signalSelector = new ChoiceBox();
            {
                //Выпадающее меню с опциями
                ChoiceBox<String> modeSelector = new ChoiceBox();
                modeSelector.getItems().addAll("Volt-Ampere", "Signal response", "Temperature", "Junction charge");
                modeSelector.setValue("Volt-Ampere");
                scopeControls.add(modeSelector, 1, 2);

                modeSelector.setOnAction(e -> {
                    if (modeSelector.getValue() == "Volt-Ampere") {
                        //Устанавливаем параметры моделирования
                        sim.diode.temperature = false;
                        sim.voltampere = true;
                        sim.signalresponse = false;
                        sim.junctioncharge = false;
                        sim.temperature = false;
                        signalSelector.setVisible(false);
                        logYAxis.setVisible(true);

                        t1label.setVisible(false);
                        t1varField.setVisible(false);
                        t2label.setVisible(false);
                        t2varField.setVisible(false);
                        t3label.setVisible(false);
                        t3varField.setVisible(false);

                        sim.simulate(showingPlot);
                    } else if (modeSelector.getValue() == "Signal response") {
                        sim.diode.temperature = false;
                        sim.voltampere = false;
                        sim.signalresponse = true;
                        sim.junctioncharge = false;
                        sim.temperature = false;
                        signalSelector.setVisible(true);
                        logYAxis.setVisible(false);

                        t1label.setVisible(false);
                        t1varField.setVisible(false);
                        t2label.setVisible(false);
                        t2varField.setVisible(false);
                        t3label.setVisible(false);
                        t3varField.setVisible(false);

                        sim.simulate(showingPlot);
                    } else if (modeSelector.getValue() == "Temperature") {
                        sim.diode.temperature = true;
                        sim.voltampere = false;
                        sim.signalresponse = false;
                        sim.junctioncharge = false;
                        sim.temperature = true;
                        signalSelector.setVisible(false);
                        logYAxis.setVisible(true);

                        t1label.setVisible(true);
                        t1varField.setVisible(true);
                        t2label.setVisible(true);
                        t2varField.setVisible(true);
                        t3label.setVisible(true);
                        t3varField.setVisible(true);

                        sim.simulate(showingPlot);
                    } else if (modeSelector.getValue() == "Junction charge") {
                        sim.diode.temperature = false;
                        sim.voltampere = false;
                        sim.signalresponse = false;
                        sim.junctioncharge = true;
                        sim.temperature = false;
                        signalSelector.setVisible(false);
                        logYAxis.setVisible(true);

                        t1label.setVisible(false);
                        t1varField.setVisible(false);
                        t2label.setVisible(false);
                        t2varField.setVisible(false);
                        t3label.setVisible(false);
                        t3varField.setVisible(false);

                        sim.simulate(showingPlot);
                    }
                });
            }

            {
                signalSelector.setVisible(false);
                signalSelector.getItems().addAll("Sin", "Sawtooth", "Triangle");
                signalSelector.setValue("Sin");
                scopeControls.add(signalSelector, 1, 3);

                signalSelector.setOnAction(e -> {
                    if (signalSelector.getValue() == "Sin") {
                        //Устанавливаем параметры моделирования
                        sim.signal.sin = true;
                        sim.signal.sawtooth = false;
                        sim.signal.triangle = false;
                        sim.simulate(showingPlot);
                    } else if (signalSelector.getValue() == "Sawtooth") {
                        sim.signal.sin = false;
                        sim.signal.sawtooth = true;
                        sim.signal.triangle = false;
                        sim.simulate(showingPlot);
                    } else if (signalSelector.getValue() == "Triangle") {
                        sim.signal.sin = false;
                        sim.signal.sawtooth = false;
                        sim.signal.triangle = true;
                        sim.simulate(showingPlot);
                    }
                });
            }
        }
    }
}
