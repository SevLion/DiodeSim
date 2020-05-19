package chartpanel;

import de.gsi.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class SpiceControls {

    public void AddControls(Simulation sim, XYChart plot, GridPane diodeControls) {
        {//I_S Saturation current
            Label variable = new Label("IS: ");
            diodeControls.add(variable, 0, 0);
            NumberField varField = new NumberField();
            varField.setPromptText("Saturation current");
            diodeControls.add(varField, 1, 0);

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
            NumberField varField = new NumberField();
            varField.setPromptText("Emission coefficient");
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
            NumberField varField = new NumberField();
            varField.setPromptText("Ohmic resistance");
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
            NumberField varField = new NumberField();
            varField.setPromptText("Forward knee current");
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
            NumberField varField = new NumberField();
            varField.setPromptText("IS temperature exponent");
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
            NumberField varField = new NumberField();
            varField.setPromptText("Activation energy");
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
            NumberField varField = new NumberField();
            varField.setPromptText("Zero-bias junction cap");
            diodeControls.add(varField, 1, 6);

            varField.setOnAction(e -> {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.EG = varField.getDouble();
                    sim.simulate(plot);
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
                    sim.simulate(plot);
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
                    sim.simulate(plot);
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
                    sim.simulate(plot);
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
                    sim.simulate(plot);
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
                    sim.simulate(plot);
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
                    sim.simulate(plot);
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
                    sim.simulate(plot);
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
                    sim.simulate(plot);
                }
            });
        }
    }
}
