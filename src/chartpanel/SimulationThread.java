package chartpanel;


import de.gsi.chart.XYChart;

//Попытка в многопоточность
public class SimulationThread extends Thread{

    Simulation sim;
    XYChart chart;

    SimulationThread(Simulation simulation, XYChart chart) {
        sim = simulation;
        this.chart = chart;
    }

    @Override
    public void run(){
        sim.simulate(chart);
        System.out.println(this.getName());
        this.interrupt();
    }
}
