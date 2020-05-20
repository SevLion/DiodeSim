package chartpanel;

import javafx.concurrent.Task;

public class DiodeSimulationTask extends Task<double[]> {

    Diode diode;
    boolean isVoltage;
    double signalValues[];

    DiodeSimulationTask(Diode diode, double signalValues[], boolean isVoltage){
        this.diode = diode;
        this.isVoltage = isVoltage;
    }

    @Override
    public double[] call(){
        if(isVoltage) {
            return diode.getResponse(signalValues);
        }
        return diode.getResponseQ(signalValues);

    }
}
