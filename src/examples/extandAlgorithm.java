package examples;

import ciayn.controller.Controller;
import ciayn.controller.PController;
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

public  class extandAlgorithm {
        public static void main(String args[]) throws Exception {
            float actualInputValue = 0;

            // callback this will be called if the controller updates the output
            Callable uActual = value -> System.out.println("u="+value.getValue()+" after "+value.getTimeStamp()+"sec");
            // this will be called when the controller starts a new iteration of calculation
            Updatable xActual = () -> new ValueFloat(actualInputValue);

            Input w = new Input(new ValueFloat(2.0f));          //setpoint input of the controller
            Input x = new Input(new ValueFloat(0f),xActual);            // actual value (feedback) from the plant
            Output u = new Output();  // output of the controller
            Controller selfMade = new PController(ValueFloat.class,0.1) {
                float maxOutputValue = 100;
                float minOutputValue = -100;
                @Override
                public Value runAlgorithm(Value e) {
                    Value u = this.runAlgorithmPController(e);
                    if (u.getValue().floatValue() > maxOutputValue){
                        u.setValue(maxOutputValue);
                    }else if (u.getValue().floatValue() < minOutputValue){
                        u.setValue(minOutputValue);
                    }
                    System.out.println("Example LOG: "+u.toString()+": value="+u.getValue());
                    return u;
                }
            };

            Env pid = new EnvController(ValueFloat.class,selfMade,w, x, u);
            pid.setInterval(10);


            pid.run();
            System.out.println("Example extandAlgorithm");
            try {
                Thread.sleep(50);
                pid.stop();
                System.out.println("End");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // \todo info:example not working

        }
}
