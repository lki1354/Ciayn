package Test;

import ciayn.Environmental;
import ciayn.basicTerms.Adder;
import ciayn.basicTerms.Integral;
import ciayn.basicTerms.Proportional;
import ciayn.elements.*;
import ciayn.elements.signal.Signal;

/**
 * Created by lukas on 14.01.17.
 */

public  class TestThe1 {
        public static void main(String args[]) throws TransferableException {

                Updater counter = new Updater() {
                        private int i =0;
                        @Override
                        public float getValue() {
                                i++;
                                return i;
                        }
                };
                Transferable show = new Wire() {
                    @Override
                    public void loadInput(Signal signal) {
                        System.out.println("Output: "+signal.getValue());
                    }
                };
            double dt = 0.001;
               Signal setPoint= new Signal(10);
               setPoint.setUpdate(counter);
               Wire inWire = new Wire();
               Proportional pController = new Proportional(10);
            Integral iTerm = new Integral(10f, 0.001f);
            Adder sum = new Adder();
               Wire outWire = new Wire();
            Wire outWire2 = new Wire();
               Environmental testEnv= new Environmental();
               testEnv.setInput(inWire);
               testEnv.connect(inWire,pController);
            testEnv.connect(inWire,iTerm);
               testEnv.connect(pController,outWire);
            testEnv.connect(iTerm,outWire2);
            testEnv.connect(outWire2,sum);
            testEnv.connect(outWire,sum);
               testEnv.setOutput(sum);
               sum.addOutputConnection(show);
            testEnv.setInterval(1);
               testEnv.loadInput(setPoint);
               testEnv.run();
               System.out.println("start Test 1");
                try {
                    Thread.sleep(5);
                    testEnv.stop();
                    System.out.println("End Test 1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //inWire.draw();


        }
}
