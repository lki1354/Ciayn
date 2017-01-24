package ciayn;

import ciayn.elements.Input;
import ciayn.elements.Operable;
import ciayn.elements.Output;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public abstract class Env implements Operable {
    private long interval = 1;
    private Timer timekeeper = new Timer();
    protected List<Input> inputSignals = new ArrayList<>();
    protected Output outputSignal = null;

    public Env(Output output){
        this.setOutput(output);
    }
   public void setOutput(Output output){
        this.outputSignal = output;
   }

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
    private void updateInputSignals(){
         for (Input input: this.inputSignals ) {
            input.updateActualValue();
        }
    }
    private void process(){
        this.updateInputSignals();
        this.doOneIteration();
    }
    protected void doOneIteration(){
        System.out.println("... do one iteration");
    }
    public void runOneIteration(){
        process();
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
    public long getInterval() {
        return this.interval;
    }

}
