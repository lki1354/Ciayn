package Test;

import ciayn.environmantal.Env;
import ciayn.environmantal.EnvController;
import ciayn.environmantal.EnvPlant;
import ciayn.elements.*;
import ciayn.elements.signal.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by lukas on 14.01.17.
 */

public  class TestThe1 {
        public static void main(String args[]) throws Exception {

            float deltaTime = 0.01f; //sec

            Callable uActual = value -> System.out.println("u="+value.getValue()+" after "+value.getTimeStamp()+"sec");
            Callable xActual = value -> System.out.println("x="+value.getValue()+" after "+value.getTimeStamp()+"sec");

            Input w = new Input(new ValueFloat(2.0f));
            Output u = new Output(new ValueFloat(0f),uActual);
            Output x = new Output(new ValueFloat(0f),xActual);

            //Env pid = EnvController.createEnvPID(ValueFloat.class, 0.1f, 0.2f, 0f, 0.001f, w, x.createInput(), u);
            Env pid = EnvController.createEnvController(ValueFloat.class, 0.1f, 0.2f, deltaTime, w, x.createInput(), u);
            //Env pid = EnvController.createEnvPI(ValueFloat.class, 0.1f, 0.2f, 0.001f, w, x.createInput(), u);

            Env pt1 = EnvPlant.createEnvPT1(ValueFloat.class,2f,1f,0.001f,u.createInput(),x);
            int i = 0;

            while (i<= 2000) {
                pid.runOneIteration();
                pt1.runOneIteration();
                i++;
                TimeUnit.MILLISECONDS.sleep((long)(deltaTime*1000));
            }
            System.out.println("END");
        }
}
