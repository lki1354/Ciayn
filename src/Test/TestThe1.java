package Test;

import ciayn.EnvController;
import ciayn.EnvPlant;
import ciayn.controller.Controller;
import ciayn.controller.PID;
import ciayn.elements.*;
import ciayn.elements.signal.*;
import ciayn.plant.PT1;
import ciayn.plant.Plant;

/**
 * Created by lukas on 14.01.17.
 */

public  class TestThe1 {
        public static void main(String args[]) throws Exception {

            Updater counter = new Updater() {
                private int i =0;
                @Override
                public Value updateActualValue() {
                    i++;
                    return new ValueFloat(i,System.nanoTime());
                }


            };

            Wire uWire = new Wire(new ValueFloat(0));
            Wire x = new Wire(new ValueFloat(0));
            Callbackable uActual = new Callbackable() {
                @Override
                public void callbackActualValue(Value value) {
                    uWire.loadInput(value);
                    System.out.println("u="+value.getValue()+" at "+value.getTimeStamp()+"sec! Controller");
                }
            };
            Callbackable xActual = new Callbackable() {
                @Override
                public void callbackActualValue(Value value) {
                    x.loadInput(value);
                    System.out.println("x="+value.getValue()+" at "+value.getTimeStamp()+"sec! Plant");
                }
            };
            //double dt = 0.001;
            Updater feedbackUpdate = new Updater() {
                @Override
                public Value updateActualValue() {
                    Value actual = new ValueFloat();
                    actual.setValue(x.getWireOutput().getValue());
                    actual.setTimeStamp(System.nanoTime()/1000000000f );
                    return  actual;
                }
            };
            Updater uUpdate = new Updater() {
                @Override
                public Value updateActualValue() {

                    return uWire.getWireOutput().getValue();
                }
            };
            Input w = new Input(new ValueFloat(10, System.nanoTime()/1000000000f ));
            Input xI = new Input(new ValueFloat(0),feedbackUpdate);
            Controller pid = new PID(ValueFloat.class,10f,0.0f,0.0f,0.001f);
            //pid.initController(new ValueFloat(0f,0.001f));
            Output u = new Output(uActual);
            EnvController envPID = new EnvController(ValueFloat.class,pid,u);
            envPID.setSetpoint(w);
            envPID.setFeedback(xI);

            Plant pt1 = new PT1(ValueFloat.class,1f,1f,0.001f);
            //pt1.initPlant(new ValueFloat(0f,0.001f));
            Output xO = new Output(xActual);
            EnvPlant envPT1 = new EnvPlant(pt1,xO);
            //uWire.addInputConnection(u);
            Input uI = new Input(new ValueFloat(0),uUpdate );
            envPT1.setInput(uI);
            uWire.addOutputConnection(uI);
            x.addInputConnection(xO);
            x.addOutputConnection(xI);

            envPID.runOneIteration();
            envPT1.runOneIteration();
            envPID.runOneIteration();
            envPT1.runOneIteration();
            envPID.runOneIteration();
            envPT1.runOneIteration();

            System.out.println("End Test!");
            /*
               testEnv.run();
               System.out.println("start Test 1");
                try {
                    Thread.sleep(5);
                    testEnv.stop();
                    System.out.println("End Test 1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               */

        }
}
