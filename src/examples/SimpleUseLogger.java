package examples;

import ciayn.elements.Callable;
import ciayn.elements.Input;
import ciayn.elements.Output;
import ciayn.elements.Updatable;
import ciayn.elements.signal.ValueFloat;
import ciayn.environmantal.Env;
import ciayn.environmantal.EnvController;
import ciayn.logger.ConsoleLogger;
import ciayn.logger.Logger;

/**
 * Created by lukas on 14.01.17.
 */

public  class SimpleUseLogger {
        public static void main(String args[]) throws Exception {
            float actualInputValue = 0;
            Logger ulog = new ConsoleLogger("controller output u =");

            // this will be called when the controller starts a new iteration of calculation
            Updatable xActual = () -> new ValueFloat(actualInputValue);

            Input w = new Input(new ValueFloat(2.0f));          //setpoint input of the controller
            Input x = new Input(new ValueFloat(0f),xActual);            // actual value (feedback) from the plant
            Output u = new Output();  // output of the controller
            u.setLogger(ulog);

            Env pid = EnvController.createEnvController(ValueFloat.class, 0.1f, 0.2f, 0.001f, w, x, u); //creates an PI controller
            pid.setInterval(10);


            pid.run();

            System.out.println("start Example SimpleUseLogger");
            try {
                Thread.sleep(70);
                pid.stop();
                System.out.println("End ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
}
