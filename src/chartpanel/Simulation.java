package chartpanel;

import de.gsi.chart.XYChart;
import de.gsi.chart.axes.spi.DefaultNumericAxis;
import de.gsi.chart.plugins.DataPointTooltip;
import de.gsi.chart.plugins.EditAxis;
import de.gsi.chart.plugins.Zoomer;
import de.gsi.chart.renderer.Renderer;
import de.gsi.chart.renderer.spi.ErrorDataSetRenderer;
import de.gsi.dataset.spi.DoubleDataSet;

import static de.gsi.dataset.DataSet.DIM_X;
import static de.gsi.dataset.DataSet.DIM_Y;

public class Simulation {

    int N_SAMPLES = 25000;
    double Xmin = 0.2;
    double Xmax = 1.0;

    Diode diode = new Diode();

    boolean voltampere = true;
    boolean signalresponse = false;

    void simulate(XYChart chart) {
        if(voltampere) {
            Volt_Ampere(chart);
        }
        if(signalresponse) {
            Transient(chart);
        }
    }

    void Transient(XYChart chart) {

        final DoubleDataSet signal1 = new DoubleDataSet("signal1");
        final DoubleDataSet response = new DoubleDataSet("response");

        response.autoNotification().set(false);
        signal1.autoNotification().set(false);

        final double[] xValues = getRange(Xmin, Xmax, N_SAMPLES);
        double[] yValues1 = new double[N_SAMPLES];
        double[] rValues = new double[N_SAMPLES];


        Signal signal = new Signal();
        yValues1 = signal.sin(xValues, 1.5);
        rValues = diode.getResponse(yValues1);

        signal1.set(xValues, yValues1);
        response.set(xValues, rValues);


        response.autoNotification().set(true);
        signal1.autoNotification().set(true);

        chart.getDatasets().clear();
        chart.getDatasets().addAll(signal1);
        chart.getDatasets().addAll(response);
        chart.getYAxis().setName("U_d, В; I_d, А");
        chart.getXAxis().setName("t, с");

        Renderer renderer1 = new ErrorDataSetRenderer();
        renderer1.getDatasets().add(response);
    }

    void Volt_Ampere(XYChart chart) {
        final DoubleDataSet current_response = new DoubleDataSet("current_response");
        current_response.autoNotification().set(false);
        final double[] vin_values = getRange(Xmin, Xmax, N_SAMPLES);
        double[] cout_values = new double[N_SAMPLES];

        cout_values = diode.getResponse(vin_values);
        current_response.set(vin_values, cout_values);

        current_response.autoNotification().set(true);
        chart.getDatasets().clear();
        chart.getDatasets().addAll(current_response);
        chart.getYAxis().setName("I_d, А");
        chart.getXAxis().setName("V_d, В");
        current_response.getAxisDescription(DIM_X).set("Voltage", "V_d");
        current_response.getAxisDescription(DIM_Y).set("Current", "I_d");

        Renderer renderer1 = new ErrorDataSetRenderer();
        renderer1.getDatasets().add(current_response);
    }


    private double[] getRange(final double Xmin, final double Xmax, final int N_SAMPLES) {
        double[] range = new double[N_SAMPLES];
        double inc = Math.abs((Xmax - Xmin) / N_SAMPLES);
        range[0] = Xmin;
        for (int i = 1; i < N_SAMPLES; ++i) {
            range[i] = range[i - 1] + inc;
        }
        return range;
    }
}
