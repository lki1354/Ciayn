package examples;

import ciayn.elements.Callable;
import ciayn.elements.Input;
import ciayn.elements.Output;
import ciayn.elements.signal.ValueFloat;
import ciayn.environmantal.Env;
import ciayn.environmantal.EnvController;
import ciayn.environmantal.EnvPlant;

/**
 * Created by lukas on 14.01.17.
 */

public  class SimpleUse {
        public static void main(String args[]) throws Exception {

            // callback this will be called if the controller updates the output
            Callable uActual = value -> System.out.println("u="+value.getValue()+" after "+value.getTimeStamp()+"sec");

            Input w = new Input(new ValueFloat(2.0f));          //setpoint input of the controller
            Input x = new Input(new ValueFloat(0f));            // actual value (feedback) from the plant
            Output u = new Output(new ValueFloat(0f),uActual);  // output of the controller

            Env pid = EnvController.createEnvController(ValueFloat.class, 0.1f, 0.2f, 0.001f, w, x, u);
            pid.setInterval(10);


            pid.run();
        }
}
