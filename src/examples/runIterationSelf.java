package examples;

import ciayn.elements.Callable;
import ciayn.elements.Input;
import ciayn.elements.Output;
import ciayn.elements.Updatable;
import ciayn.elements.signal.Value;
import ciayn.elements.signal.ValueFloat;
import ciayn.environmantal.Env;
import ciayn.environmantal.EnvController;

/**
 * Created by lukas on 14.01.17.
 */

public  class runIterationSelf {
        public static void main(String args[]) throws Exception {
            Value actualInputValue = new ValueFloat(10);

            // callback this will be called if the controller updates the output
            Callable uActual = value -> System.out.println("u="+value.getValue()+" after "+value.getTimeStamp()+"sec");
            // this will be called when the controller starts a new iteration of calculation

            Input w = new Input(new ValueFloat(2.0f));          //setpoint input of the controller
            Input x = new Input(new ValueFloat(0f));            // actual value (feedback) from the plant
            Output u = new Output(uActual);  // output of the controller

            Env pid = EnvController.createEnvPI(ValueFloat.class, 0.1f, 0.2f, 0.001f, w, x, u); //creates an PI controller


            x.setValue(actualInputValue);
            pid.runOneIteration();
            System.out.println("END");
        }
}
