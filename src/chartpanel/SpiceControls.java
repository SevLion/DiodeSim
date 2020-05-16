package chartpanel;

import de.gsi.chart.XYChart;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class SpiceControls {
    private  int rowIndex = 0;

    public void AddControl(Simulation sim, XYChart plot, GridPane diodeControls, String label, String  promptText) {
        Label variable = new Label(label + ": ");
        diodeControls.add(variable, 0, rowIndex);
        NumberField varField = new NumberField();
        varField.setPromptText(promptText);
        diodeControls.add(varField, 1, rowIndex);
        Button Set = new Button("Set");
        diodeControls.add(Set, 2, rowIndex);
        rowIndex++;

        Set.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if ((varField.getText() != null && !varField.getText().isEmpty())) {
                    sim.diode.I_S = varField.getDouble();
                    sim.Volt_Ampere(plot);
                    sim.Volt_Ampere(plot);
                }
            }
        });
    }
}
