package ciayn.elements;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lukas on 13.01.17.
 */
public class Environmental<T> extends Block implements Operable{
    private  int interval;
    private Timer timekeeper = new Timer();
    private Signal<T> internalSignal;
    //private Transferable output = null;
/*
    public void addInputSignal(Signal signal){
        internalSignal = signal;
    }
*/
    @Override
    public void run() {
        TimerTask runProcess = new TimerTask() {
            @Override
            public void run() {
                process();
            }
        };
        timekeeper.scheduleAtFixedRate(runProcess,0,interval);
    }
    private void process(){
        internalSignal.update();
        doOneIteration();
    }
    private void doOneIteration(){
        System.out.println("Env: "+internalSignal.getValue());
        output.loadInput(internalSignal);

    }

    @Override
    public void pause() {
        timekeeper.cancel();
    }

    @Override
    public void pause(int waitTime) {

    }

    @Override
    public void stop() {
        timekeeper.cancel();
    }

    @Override
    public void setInterval(int deltaTime) {
        interval = deltaTime;
    }

    @Override
    public void loadInput(Signal signal) {
        internalSignal = signal;
        doOneIteration();
    }

}
