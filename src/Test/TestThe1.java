package Test;

import ciayn.elements.*;

/**
 * Created by lukas on 14.01.17.
 */

public  class TestThe1 {
        public static void main(String args[]) {

                Updater<String> counter = new Updater<String>() {
                        private int i =0;
                        @Override
                        public String getValue() {
                                i++;
                                return "Run "+i+" is going on!";
                        }
                };
                Transferable show = new Wire() {
                    @Override
                    public void loadInput(Signal signal) {
                        System.out.println("Output: "+signal.getValue());
                    }
                };

               Signal<String> valueIN = new Signal<>("Hallo 0!");
               valueIN.setUpdate(counter);
               Wire inWire = new Wire();
               Wire outWire = new Wire();
               Environmental<String> hawaii = new Environmental<>();
               inWire.addOutputConnection(hawaii);
               hawaii.addOutputConnection(outWire);
               outWire.addOutputConnection(show);
               hawaii.setInterval(500);
               inWire.loadInput(valueIN);
               hawaii.run();
            System.out.println("start Test 1");
            try {
                Thread.sleep(2000);
                hawaii.stop();
                System.out.println("End Test 1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
}
