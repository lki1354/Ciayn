package examples;

import ciayn.elements.Input;
import ciayn.elements.Output;
import ciayn.elements.signal.ValueFloat;
import ciayn.environmantal.Env;
import ciayn.environmantal.EnvController;
import ciayn.environmantal.EnvPlant;
import ciayn.logger.ConsoleLogger;
import ciayn.logger.Logger;

import java.util.concurrent.TimeUnit;

/**
 * Created by lukas on 14.01.17.
 */

public class ControllerAndPlant {
    public static void main(String args[]) throws Exception {

        float deltaTime = 0.01f; //sec

        Logger uLog = new ConsoleLogger("controller output u =");
        Logger xLog = new ConsoleLogger("controller output x =");
        Logger pt1Log = new ConsoleLogger("controller output x =");

        Input w = new Input(new ValueFloat(2.0f));
        Output u = new Output();
        u.setLogger(uLog);
        Output x = new Output(new ValueFloat(0f));
        x.setLogger(xLog);

        Env pid = EnvController.createEnvPID(ValueFloat.class, 0.1f, 0.2f, 0f, deltaTime, w, x.createInput(), u);

        Env pt1 = EnvPlant.createEnvPT1(ValueFloat.class, 2f, 1f, 0.001f, u.createInput(), x);

        int i = 0;

        while (i <= 5) {
            pid.runOneIteration();
            pt1.runOneIteration();
            i++;
            TimeUnit.MILLISECONDS.sleep((long) (deltaTime * 1000));
        }
        System.out.println("END");
    }
}
