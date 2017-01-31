package ciayn.environmantal;

import ciayn.elements.*;
import ciayn.elements.signal.Signal;


public class Environmental extends Env {

    public Environmental(Input input, Output output) {
        super(output);
        this.addInput(input);
    }

    public void addInput(Input input) {
        inputSignals.add(input);
    }

    public void addInput(Signal signal) {
        this.addInput(signal.getInput());
    }


}
