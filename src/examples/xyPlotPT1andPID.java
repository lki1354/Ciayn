package examples;

import ciayn.elements.Input;
import ciayn.elements.signal.*;
import ciayn.elements.signal.unit.Unit;
import ciayn.elements.signal.unit.Voltage;
import ciayn.environmantal.Env;
import ciayn.environmantal.EnvController;
import ciayn.environmantal.EnvPlant;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.util.concurrent.TimeUnit;

/**
 * Created by lukas on 14.01.17.
 */

public class xyPlotPT1andPID extends ApplicationFrame {

    public xyPlotPT1andPID(final String title) throws Exception {
        super(title);

        Unit Volt = Voltage.getUnit();
        Signal uSignal = new SignalFloat(Volt);
        Signal xSignal = new SignalFloat(Volt);

        Input w = new Input(new ReadingFloat(2.0f, Volt));

        Env pid = EnvController.createEnvPI(ValueFloat.class, 5f, 0.05f, null, w, xSignal.getInput(), uSignal.getOutput());
        Env pt1 = EnvPlant.createEnvPT1(ValueFloat.class, 2f, 1f, 0.001f, uSignal.getInput(), xSignal.getOutput());

        Value value;
        final XYSeries series = new XYSeries("PT1 output x");
        int i = 0;
        while (i <= 2000) {
            pid.runOneIteration();
            pt1.runOneIteration();
            value = xSignal.getActualValue();
            series.add(value.getTimeStamp(), value.getValue());
            i++;
            TimeUnit.MILLISECONDS.sleep(10);
        }


        final XYSeriesCollection data = new XYSeriesCollection(series);
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Plot PT1 plant with PID controller",
                "time",
                "output",
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1000, 500));
        setContentPane(chartPanel);

    }

    public static void main(final String[] args) throws Exception {

        final xyPlotPT1andPID demo = new xyPlotPT1andPID("XY plot example");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}


