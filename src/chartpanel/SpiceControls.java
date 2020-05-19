package chartpanel;

import de.gsi.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;

public class SpiceControls {

    public void AddControls(Simulation sim, XYChart plot, GridPane diodeControls) {
        {//I_S Saturation current
            //Слева в таблицу добавляем Label, справа - NumberField
            Label variable = new Label("IS: ");
            diodeControls.add(variable, 0, 0);
            NumberField varField = new NumberField(sim.diode.I_S);
            Tooltip tooltip = new Tooltip("Saturation current");
            varField.setTooltip(tooltip);
            varField.setEditable(true);
            diodeControls.add(varField, 1, 0);

            //По прерыванию меняем значение I_S и перестраиваем график
            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.I_S = varField.getDouble();
                    sim.simulate(plot);
                }
            });
        }
        {//N Emission coefficient
            Label variable = new Label("N: ");
            diodeControls.add(variable, 0, 1);
            NumberField varField = new NumberField(sim.diode.N);
            Tooltip tooltip = new Tooltip("Emission coefficient");
            varField.setTooltip(tooltip);
            varField.setEditable(true);
            diodeControls.add(varField, 1, 1);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.N = varField.getDouble();
                    sim.simulate(plot);
                }
            });
        }
        {//Rs Ohmic resistance
            Label variable = new Label("Rs: ");
            diodeControls.add(variable, 0, 2);
            NumberField varField = new NumberField(sim.diode.RS);
            Tooltip tooltip = new Tooltip("Ohmic resistance");
            varField.setTooltip(tooltip);
            varField.setEditable(true);
            diodeControls.add(varField, 1, 2);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.RS = varField.getDouble();
                    sim.simulate(plot);
                }
            });
        }
        {//Ikf Forward knee current
            Label variable = new Label("Ikf: ");
            diodeControls.add(variable, 0, 3);
            NumberField varField = new NumberField(sim.diode.IKF);
            Tooltip tooltip = new Tooltip("Forward knee current");
            varField.setTooltip(tooltip);
            varField.setEditable(true);
            diodeControls.add(varField, 1, 3);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.IKF = varField.getDouble();
                    sim.simulate(plot);
                }
            });
        }
        {//Xti Forward knee current
            Label variable = new Label("Xti: ");
            diodeControls.add(variable, 0, 4);
            NumberField varField = new NumberField(sim.diode.XTI);
            Tooltip tooltip = new Tooltip("IS temperature exponent");
            varField.setTooltip(tooltip);
            varField.setEditable(true);
            diodeControls.add(varField, 1, 4);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.XTI = varField.getDouble();
                    sim.simulate(plot);
                }
            });
        }
        {//Eg Activation energy
            Label variable = new Label("Eg: ");
            diodeControls.add(variable, 0, 5);
            NumberField varField = new NumberField(sim.diode.EG);
            Tooltip tooltip = new Tooltip("Activation energy");
            varField.setTooltip(tooltip);
            varField.setEditable(true);
            diodeControls.add(varField, 1, 5);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.EG = varField.getDouble();
                    sim.simulate(plot);
                }
            });
        }
        {//Cjo Zero-bias junction capacitance
            Label variable = new Label("Cjo: ");
            diodeControls.add(variable, 0, 6);
            NumberField varField = new NumberField(sim.diode.CJO);
            Tooltip tooltip = new Tooltip("Zero-bias junction capacitance");
            varField.setTooltip(tooltip);
            varField.setEditable(true);
            diodeControls.add(varField, 1, 6);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.CJO = varField.getDouble();
                    sim.simulate(plot);
                }
            });
        }
        {//M Grading coefficient
            Label variable = new Label("M: ");
            diodeControls.add(variable, 0, 7);
            NumberField varField = new NumberField(sim.diode.M);
            Tooltip tooltip = new Tooltip("Grading coefficient");
            varField.setTooltip(tooltip);
            varField.setEditable(true);
            diodeControls.add(varField, 1, 7);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.M = varField.getDouble();
                    sim.simulate(plot);
                }
            });
        }
        {//Vj Junction potential
            Label variable = new Label("Vj: ");
            diodeControls.add(variable, 0, 8);
            NumberField varField = new NumberField(sim.diode.VJ);
            Tooltip tooltip = new Tooltip("Junction potential");
            varField.setTooltip(tooltip);
            varField.setEditable(true);
            diodeControls.add(varField, 1, 8);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.VJ = varField.getDouble();
                    sim.simulate(plot);
                }
            });
        }
        {//Fc Forward bias depletion capacitance coefficient
            Label variable = new Label("Fc: ");
            diodeControls.add(variable, 0, 9);
            NumberField varField = new NumberField(sim.diode.FC);
            Tooltip tooltip = new Tooltip("Forward bias depletion capacitance coefficient");
            varField.setTooltip(tooltip);
            varField.setEditable(true);
            diodeControls.add(varField, 1, 9);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.FC = varField.getDouble();
                    sim.simulate(plot);
                }
            });
        }
        {//Isr Forward bias depletion capacitance coefficient
            Label variable = new Label("Isr: ");
            diodeControls.add(variable, 0, 10);
            NumberField varField = new NumberField(sim.diode.I_SR);
            Tooltip tooltip = new Tooltip("Recombination current");
            varField.setTooltip(tooltip);
            varField.setEditable(true);
            diodeControls.add(varField, 1, 10);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.I_SR = varField.getDouble();
                    sim.simulate(plot);
                }
            });
        }
        {//Nr Reverse emission coefficient
            Label variable = new Label("Nr: ");
            diodeControls.add(variable, 0, 11);
            NumberField varField = new NumberField(sim.diode.NR);
            Tooltip tooltip = new Tooltip("Reverse emission coefficient");
            varField.setTooltip(tooltip);
            varField.setEditable(true);
            diodeControls.add(varField, 1, 11);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.NR = varField.getDouble();
                    sim.simulate(plot);
                }
            });
        }
        {//Bv Reverse breakdown voltage
            Label variable = new Label("Bv: ");
            diodeControls.add(variable, 0, 12);
            NumberField varField = new NumberField(sim.diode.BV);
            Tooltip tooltip = new Tooltip("Reverse breakdown voltage");
            varField.setTooltip(tooltip);
            varField.setEditable(true);
            diodeControls.add(varField, 1, 12);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.BV = varField.getDouble();
                    sim.simulate(plot);
                }
            });
        }
        {//Ibv Reverse breakdown current
            Label variable = new Label("Ibv: ");
            diodeControls.add(variable, 0, 13);
            NumberField varField = new NumberField(sim.diode.IBV);
            Tooltip tooltip = new Tooltip("Reverse breakdown current");
            varField.setTooltip(tooltip);
            varField.setEditable(true);
            diodeControls.add(varField, 1, 13);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.IBV = varField.getDouble();
                    sim.simulate(plot);
                }
            });
        }
        {//Tt Transit time
            Label variable = new Label("Tt: ");
            diodeControls.add(variable, 0, 14);
            NumberField varField = new NumberField(sim.diode.TT);
            Tooltip tooltip = new Tooltip("Transit time");
            varField.setTooltip(tooltip);
            varField.setEditable(true);
            diodeControls.add(varField, 1, 14);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.TT = varField.getDouble();
                    sim.simulate(plot);
                }
            });
        }
    }
}
