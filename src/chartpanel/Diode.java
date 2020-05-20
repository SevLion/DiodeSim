package chartpanel;

import java.util.concurrent.Executor;

public class Diode {

    boolean temperature = false;

    //Diode Temperature
    double T_c = 300.15; //Circuit temperature
    double TOFFSET = 0; //Offset local circuit temperature
    double T = T_c + TOFFSET; //Diode temperature

    //Minimal Conduction
    double GMIN = 1e-12; //Minimal conductance

    //Thermal Voltage
    double N = 1.768; //Emission coefficient
    double k = 8.617e-5; //Boltzmann constant
    double q = 1; //Elementary charge on an electron
    double V_t = (N * k * T) / q; //Thermal Voltage

    double I_S = 2.2e-9; //Saturation current
    //double I_SR = 7.832e-9; //Recombination current
    //double IKF = 8.512e-3; //Forward knee current
    double TIKF = 0; //Linear IKF temperature coefficient
    double VJ = 0.5; //Junction potential
    //double NR = 2; //Reverse emission coefficient
    double NBV = 1; //Reverse breakdown emission coefficient
    double NBVL = 1; //Low-level reverse breakdown ideality factor
    double M = 88.82e-3; //Grading coefficient
    double BV = 75; //Reverse breakdown voltage
    double IBV = 5e-6; //Reverse breakdown current
    double IBVL = 0; //Low-level reverse breakdown knee current
    //double RS = 0.5927; //Ohmic resistance

    double I_d; //Diode current
    double V_d; //Diode voltage

    //Junction Charge Model
    double Q_d; //Diode charge
    double FC = 0.5; //Forward bias depletion capacitance coefficient
    double TT = 5.771e-9; //Transit time
    double CJO = 1.725e-12; //Zero-bias junction capacitance
    double F1 = VJ * (1 - Math.pow(1 - FC, 1 - M)) / (1 - M);
    double F2 = Math.pow(1 - FC, 1 + M);
    double F3 = 1 - FC * (1 + M);

    //Geometry-Adjusted Variables
    double AREA = 1; //Area of the diode
    double SCALE = 1; //Number of parallel connected diodes
    double CJO_d = CJO * AREA * SCALE; //Geometry-adjusted zero-bias junction capacitance
    double IBV_d = IBV * AREA * SCALE; //Geometry-adjusted reverse breakdown current
    double IS_d = I_S * AREA * SCALE; //Geometry-adjusted saturation current
    //double RS_d = RS / (AREA * SCALE); //Geometry-adjusted series resistance

    void junctionCharge() {
        F1 = (1 - Math.pow(1 - FC, 1 - M)) * (VJ / (1 - M));
        F2 = Math.pow(1 - FC, 1 + M);
        F3 = 1 - FC * (1 + M);

        if(V_d < FC * VJ) {
            Q_d = TT * I_d + CJO_d * VJ * ((1 - Math.pow((1 - (V_d / VJ)), 1 - M)) / (1 - M));
        } else {
            Q_d = TT * I_d + CJO_d * (F1 + (F3 * (V_d - FC * VJ) + M / (2 * VJ) * (Math.pow(V_d, 2) - Math.pow(FC * VJ, 2))) / F2);
        }
    }

    //Temperature Dependence
    double TMEAS = 300.15; //Parameter extraction temperature
    double XTI = 3.0; //Saturation current temperature exponent
    double EG = 1.11; //Activation energy
    double TBV1 = 0; //Linear BV temperature coefficient
    double TBV2 = 0; //Quadratic BV temperature coefficient
    double TRS1 = 0; //Linear RS temperature coefficient
    double TRS2 = 0; //Quadratic RS temperature coefficient

    //Temperature correct vars
    double IS_d_T;
    double I_SR_T;
    double IKF_T;
    double BV_T;
    double RS_T;
    double VJ_T;
    double CJO_d_T;

    void geometryAdjustedVariables() {
        CJO_d = CJO * AREA * SCALE; //Geometry-adjusted zero-bias junction capacitance
        IBV_d = IBV * AREA * SCALE; //Geometry-adjusted reverse breakdown current
        IS_d = I_S * AREA * SCALE; //Geometry-adjusted saturation current
        //RS_d = RS / (AREA * SCALE); //Geometry-adjusted series resistance
    }

    void temperatureDependene() {
        V_t = (N * k * T) / q;
        IS_d_T = IS_d * Math.pow((T / TMEAS), XTI / N) * Math.exp(((T / TMEAS) - 1) * (EG / (V_t)));


        //Корректировка полученных значений согласно температурным зависимостям
        //IS_d_T = IS_d * Math.pow((T / TMEAS), XTI / N) * Math.exp(((T / TMEAS) - 1) * (EG / (N * V_t))); //Relationship between the geometry-adjusted saturation current and the diode temperature
        //I_SR_T = I_SR * Math.pow(T / TMEAS, XTI / NR) * Math.exp(((T / TMEAS) - 1) * (EG / (NR * V_t))); //Relationship between the recombination current and the diode temperature
        //IKF_T = IKF * (1 + TIKF * (T - TMEAS)); //Relationship between the forward knee current and the diode temperature
        BV_T = BV * (1 + TBV1 * (T - TMEAS) + TBV2 * Math.pow(T - TMEAS, 2)); //Relationship between the breakdown voltage and the diode temperature
        //RS_T = RS * (1 + TRS1 * (T - TMEAS) + TRS2 * Math.pow(T - TMEAS, 2)); //Relationship between the ohmic resistance and the diode temperature
        double EG_TMEAS = 1.16 - (7.02 * Math.E - 4 * Math.pow(TMEAS, 2)) / (TMEAS + 1108); //Activation energy for the temperature at which the diode parameters were measured
        double EG_T = 1.16 - (7.02 * Math.E - 4 * Math.pow(T, 2)) / (T + 1108); //Activation energy for the diode temperature
        VJ_T = VJ * (T / TMEAS) - 3 * V_t * Math.log(T / TMEAS) - (T / TMEAS) * EG_TMEAS + EG_T; //Relationship between the junction potential and the diode temperature
        CJO_d_T = CJO_d * (1 + M * (400 * Math.E - 6 * (T - TMEAS) - (VJ_T - VJ) / (VJ))); //Relationship between the geometry-adjusted diode zero-bias junction capacitance and the diode temperature

    }

    void diodeCurrent(){
        //Вычисление токов диода
        if(!temperature) {
            if (V_d > 80 * V_t) {
                I_d = IS_d * ((V_d / V_t - 79) * Math.exp(80) - 1) + V_d * GMIN;
            } else if (V_d >= -3 * V_t) {
                I_d = IS_d * (Math.exp(V_d / V_t) - 1) + V_d * GMIN;
            } else if (V_d >= -BV) {
                I_d = -IS_d * (1 + 27 / (Math.pow(V_d / V_t, 3) * Math.exp(3)));
            } else {
                I_d = -IBV_d * (Math.exp(-(BV + V_d) / V_t) - 1) - IS_d * (1 - Math.pow((3 * V_t) / (Math.E * BV), 3)) + V_d * GMIN;
            }
        }
        else{
            if (V_d > 80 * V_t) {
                I_d = IS_d_T * ((V_d / V_t - 79) * Math.exp(80) - 1) + V_d * GMIN;
            } else if (V_d >= -3 * V_t) {
                I_d = IS_d_T * (Math.exp(V_d / V_t) - 1) + V_d * GMIN;
            } else if (V_d >= -BV_T) {
                I_d = -IS_d_T * (1 + 27 / (Math.pow(V_d / V_t, 3) * Math.exp(3)));
            } else {
                I_d = -IBV_d * (Math.exp(-(BV_T + V_d) / V_t) - 1) - IS_d * (1 - Math.pow((3 * V_t) / (Math.E * BV_T), 3)) + V_d * GMIN;
            }
        }
    }

    void calculate() {
        temperatureDependene();
        geometryAdjustedVariables();
        diodeCurrent();
        junctionCharge();
    }

    //Вычисление тока при фиксированном значении напряжения на диоде
    private double doStepV(double voltage) {
        V_d = voltage;
        calculate();
        //junctionCharge();
        return I_d;
    }

    private double doStepQ(double voltage) {
        V_d = voltage;
        calculate();
        //junctionCharge();
        return Q_d;
    }

    //Получаем массив с значениями токов (responseValues[]) в зависимости от значений напряжений (signalValues[])
    double[] getResponse(double[] signalValues) {
        double[] responseValues = new double[signalValues.length];
        for (int i = 0; i < signalValues.length; ++i) {
            responseValues[i] = doStepV(signalValues[i]);
        }
        return responseValues;
    }

    double[] getResponseQ(double[] signalValues) {
        double[] responseValues = new double[signalValues.length];
        for (int i = 0; i < signalValues.length; ++i) {
            responseValues[i] = doStepQ(signalValues[i]);
        }
        return responseValues;
    }
}
