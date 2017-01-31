package examples;

import ciayn.elements.Callable;
import ciayn.elements.Input;
import ciayn.elements.Output;
import ciayn.elements.signal.ReadingFloat;
import ciayn.elements.signal.Signal;
import ciayn.elements.signal.SignalFloat;
import ciayn.elements.signal.ValueFloat;
import ciayn.elements.signal.unit.Unit;
import ciayn.elements.signal.unit.Voltage;
import ciayn.environmantal.Env;
import ciayn.environmantal.EnvController;
import ciayn.environmantal.EnvPlant;
import ciayn.logger.ConsoleLogger;
import ciayn.logger.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by lukas on 14.01.17.
 */

public class useSignal {
    public static void main(String args[]) throws Exception {

        float deltaTime = 0.01f; //sec

        Unit Volt = Voltage.getUnit();

        Signal uSignal = new SignalFloat(Volt);
        Logger ulog = new ConsoleLogger("controller output u =");
        uSignal.setLogger(ulog);

        Input w = new Input(new ReadingFloat(2.0f, Volt));
        //Output u = new Output(new ReadingFloat(0f, Volt),uActual);
        Output x = new Output(new ReadingFloat(0f, Voltage.getUnit()));     // same as use of Volt, because Voltage is a singleton class

        Env pid = EnvController.createEnvPI(ValueFloat.class, 0.1f, 0.2f, deltaTime, w, x.createInput(), uSignal.getOutput());
        Env pt1 = EnvPlant.createEnvPT1(ValueFloat.class, 2f, 1f, 0.001f, uSignal.getInput(), x);
        int i = 0;

        System.out.println("Start Simulation");
        while (i <= 4) {
            pid.runOneIteration();
            pt1.runOneIteration();
            i++;
            TimeUnit.MILLISECONDS.sleep((long) (deltaTime * 1000));
        }
        System.out.println("END simulation");
        System.out.println("print Signal values!");
        List<ValueFloat> Values = uSignal.getSignalValues();
        for (ValueFloat value : Values) {
            System.out.println("timestemp=" + value.getTimeStamp() + " : " + value.getValue() + " " + uSignal.getUnit().toString());
        }
        System.out.println("END print Signal values!");

    }
}
