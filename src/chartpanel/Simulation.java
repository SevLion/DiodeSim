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
    Signal signal = new Signal();

    //Флаги для определения того, какой график строить
    boolean voltampere = true;
    boolean signalresponse = false;
    boolean temperature = false;
    boolean junctioncharge = false;


    //Вызывается снаружи для построения графика
    void simulate(XYChart chart) {
        if(voltampere) {
            Volt_Ampere(chart);
        }
        if(signalresponse) {
            Transient(chart);
        }
        if(junctioncharge) {
            JunctionCharge(chart);
        }
        if(temperature) {
            Temperature(chart);
        }
    }

    void Transient(XYChart chart) {

        //Два датасета для библиотеки, в которые нужно передать массивы с данными для отрисовки
        final DoubleDataSet signal1 = new DoubleDataSet("signal1");
        final DoubleDataSet response = new DoubleDataSet("response");

        //Запрещаем проверять, обновились ли датасеты, чтобы не отрисовывать график частично
        response.autoNotification().set(false);
        signal1.autoNotification().set(false);

        final double[] xValues = getRange(Xmin, Xmax, N_SAMPLES);
        double[] yValues1 = new double[N_SAMPLES];
        double[] rValues = new double[N_SAMPLES];


        //В yValues1 записываем sin(x)
        yValues1 = signal.getSignal(xValues);

        //Передаём yValues1 в качестве напряжения на диоде для моделирования тока
        rValues = diode.getResponse(yValues1);

        //Отдаём в датасеты полученные массивы
        signal1.set(xValues, yValues1);
        response.set(xValues, rValues);

        //Разрешаем перестроить график
        response.autoNotification().set(true);
        signal1.autoNotification().set(true);

        //Очищаем график, чттобы не было старых кривых
        chart.getDatasets().clear();
        //Передаём датасеты в график
        chart.getDatasets().addAll(signal1);
        chart.getDatasets().addAll(response);
        //Названия осей
        chart.getYAxis().setName("U_d, В; I_d, А");
        chart.getXAxis().setName("t, с");

        //??
        Renderer renderer1 = new ErrorDataSetRenderer();
        renderer1.getDatasets().add(response);
    }

    void Volt_Ampere(XYChart chart) {
        //Датасет для библиотеки, в который нужно передать массив с данными для отрисовки
        final DoubleDataSet current_response = new DoubleDataSet("current_response");

        //Запрещаем проверять, обновились ли датасеты, чтобы не отрисовывать график частично
        current_response.autoNotification().set(false);

        //Получаем значения напряжения для моделирования
        final double[] vin_values = getRange(Xmin, Xmax, N_SAMPLES);
        //Передаём vin_values в качестве напряжения на диоде для моделирования тока
        double[] cout_values = new double[N_SAMPLES];

        //Моделируем
        cout_values = diode.getResponse(vin_values);
        //Передаём в датасет
        current_response.set(vin_values, cout_values);

        //Разрешаем перестроить график
        current_response.autoNotification().set(true);

        //Очищаем график, чттобы не было старых кривых
        chart.getDatasets().clear();
        //Передаём датасеты в график
        chart.getDatasets().addAll(current_response);
        //Названия осей
        chart.getYAxis().setName("I_d, А");
        chart.getXAxis().setName("V_d, В");
        //Названия осей ??
        current_response.getAxisDescription(DIM_X).set("Voltage", "V_d");
        current_response.getAxisDescription(DIM_Y).set("Current", "I_d");

        //??
        Renderer renderer1 = new ErrorDataSetRenderer();
        renderer1.getDatasets().add(current_response);
    }

    void JunctionCharge(XYChart chart) {
        //Датасет для библиотеки, в который нужно передать массив с данными для отрисовки
        final DoubleDataSet charge = new DoubleDataSet("charge");

        //Запрещаем проверять, обновились ли датасеты, чтобы не отрисовывать график частично
        charge.autoNotification().set(false);

        //Получаем значения напряжения для моделирования
        final double[] vin_values = getRange(Xmin, Xmax, N_SAMPLES);
        //Передаём vin_values в качестве напряжения на диоде для моделирования тока
        double[] charge_values = new double[N_SAMPLES];

        //Моделируем
        charge_values = diode.getResponseQ(vin_values);
        //Передаём в датасет
        charge.set(vin_values, charge_values);

        //Разрешаем перестроить график
        charge.autoNotification().set(true);

        //Очищаем график, чттобы не было старых кривых
        chart.getDatasets().clear();
        //Передаём датасеты в график
        chart.getDatasets().addAll(charge);
        //Названия осей
        chart.getYAxis().setName("Q, Кл");
        chart.getXAxis().setName("V_d, В");
        //Названия осей ??
        charge.getAxisDescription(DIM_X).set("Voltage", "V_d");
        charge.getAxisDescription(DIM_Y).set("Charge", "Q_d");

        //??
        Renderer renderer1 = new ErrorDataSetRenderer();
        renderer1.getDatasets().add(charge);
    }

    void Temperature(XYChart chart) {
        //Датасет для библиотеки, в который нужно передать массив с данными для отрисовки
        final DoubleDataSet current_response1 = new DoubleDataSet("current_response at 17C");
        final DoubleDataSet current_response2 = new DoubleDataSet("current_response at 27C");
        final DoubleDataSet current_response3 = new DoubleDataSet("current_response at 37C");

        //Запрещаем проверять, обновились ли датасеты, чтобы не отрисовывать график частично
        current_response1.autoNotification().set(false);
        current_response2.autoNotification().set(false);
        current_response3.autoNotification().set(false);

        //Получаем значения напряжения для моделирования
        final double[] vin_values = getRange(Xmin, Xmax, N_SAMPLES);
        //Передаём vin_values в качестве напряжения на диоде для моделирования тока
        double[] cout_values = new double[N_SAMPLES];

        double T = diode.T;

        //Моделируем
        diode.T = 290.15;
        cout_values = diode.getResponse(vin_values);
        current_response1.set(vin_values, cout_values);
        diode.T = 300.15;
        cout_values = diode.getResponse(vin_values);
        current_response2.set(vin_values, cout_values);
        diode.T = 310.15;
        cout_values = diode.getResponse(vin_values);
        current_response3.set(vin_values, cout_values);

        //Разрешаем перестроить график
        current_response1.autoNotification().set(true);
        current_response2.autoNotification().set(true);
        current_response3.autoNotification().set(true);

        //Очищаем график, чттобы не было старых кривых
        chart.getDatasets().clear();
        //Передаём датасеты в график
        chart.getDatasets().addAll(current_response1, current_response2, current_response3);
        //Названия осей
        chart.getYAxis().setName("I_d, А");
        chart.getXAxis().setName("V_d, В");
        //Названия осей ??
        //current_response1.getAxisDescription(DIM_X).set("Voltage", "V_d");
        //current_response1.getAxisDescription(DIM_Y).set("Current", "I_d");

        //??
        Renderer renderer1 = new ErrorDataSetRenderer();
        renderer1.getDatasets().addAll(current_response1, current_response2, current_response3);

        diode.T = T;
    }


    double[] getRange(final double Xmin, final double Xmax, final int N_SAMPLES) {
        double[] range = new double[N_SAMPLES];
        double inc = Math.abs((Xmax - Xmin) / N_SAMPLES);
        range[0] = Xmin;
        for (int i = 1; i < N_SAMPLES; ++i) {
            range[i] = range[i - 1] + inc;
        }
        return range;
    }
}
