package chartpanel;

import de.gsi.chart.XYChart;
import de.gsi.dataset.spi.DoubleDataSet;

public class Simulation {

    private static final int N_SAMPLES = 100000;

    void Transient(XYChart chart) {
        final DoubleDataSet signal1 = new DoubleDataSet("signal1");
        final DoubleDataSet response = new DoubleDataSet("response");

        final double[] xValues = getRange(0, 100, N_SAMPLES);
        double[] yValues1 = new double[N_SAMPLES];
        double[] rValues = new double[N_SAMPLES];

        Diode diode = new Diode();
        Signal signal = new Signal();
        yValues1 = signal.sin(xValues, 1);
        rValues = diode.getResponse(yValues1);



        signal1.set(xValues, yValues1);
        response.set(xValues, rValues);

        chart.getDatasets().addAll(signal1);
        chart.getDatasets().addAll(response);
    }

    void Volt_Ampere(XYChart chart) {
        //chart.getYAxis().getLogAxisType();
        final DoubleDataSet current_response = new DoubleDataSet("current_response");

        final double[] vin_values = getRange(-1, 1, N_SAMPLES);
        double[] cout_values = new double[N_SAMPLES];
        //double cout_max = 10;
        //double cout_min = -10;

        Diode diode = new Diode();
        cout_values = diode.getResponse(vin_values);
        //cout_values = setYRange(cout_values, cout_min, cout_max);
        System.out.print(vin_values[85000]);
        System.out.println();
        System.out.print(cout_values[85000]);

        current_response.set(vin_values, cout_values);

        chart.getDatasets().addAll(current_response);
        chart.getYAxis().setName("I_d, А");
        chart.getXAxis().setName("V_in, В");
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

    double[] setYRange(double[] data, double ymin, double ymax) {
        for(double i:data) {
            if(i > ymax || i < ymin) {
                i = 0;
            }
        }
        return data;
    }
}
