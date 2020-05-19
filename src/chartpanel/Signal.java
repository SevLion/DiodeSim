package chartpanel;

public class Signal {
    //Возвращает массивы с различными сигналами
    //Надо переписать
    boolean sin = true;
    boolean triangle = false;
    boolean sawtooth = false;

    double[] getSignal(double[] range) {
        if(sin) {return sin(range, 0.9);}
        if(sawtooth) {return sawtooth(range, 0.9, 1);}
        if(triangle) {return triangle(range, 0.9, 1);}
        return sin(range, 0.9);
    }

    double[] sin(double[] range, double U_max) {
        double[] vValues = new double[range.length];
        for (int i = 0; i < range.length; ++i) {
            vValues[i] = Math.sin(range[i]) * U_max;
        }
        return vValues;
    }

    double[] triangle(double[] range, double U_max, double tan) {
        double t = tan;
        double U_0 = -U_max;
        double[] vValues = new double[range.length];
        double x0 = range[0];
        for (int i = 0; i < range.length; ++i) {
            vValues[i] = t * (range[i] - x0) * U_max + U_0;
            if (Math.abs(vValues[i]) >= U_max) {
                vValues[i] = Math.signum(vValues[i]) * U_max;
                t *= -1;
                x0 = range[i];
                U_0 *= -1;
            }
        }
        return vValues;
    }

    double[] sawtooth(double[] range, double U_max, double tan) {
        double U_0 = -U_max;
        double[] vValues = new double[range.length];
        double x0 = range[0];
        for (int i = 0; i < range.length; ++i) {
            vValues[i] = tan * (range[i] - x0) * U_max + U_0;
            if (Math.abs(vValues[i]) >= U_max) {
                vValues[i] = -U_max;
                x0 = range[i];
            }
        }
        return vValues;
    }
}
