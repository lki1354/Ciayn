package ciayn;

import ciayn.elements.*;
import ciayn.elements.signal.Signal;


public abstract class Environmental extends Env {

    public Environmental(Input input, Output output){
        super(output);
        this.addInput(input);
    }

    public void addInput(Input input){
        inputSignals.add(input);
    }

    public void addInput(Wire input){
        this.addInput(new Input(input.getWireOutput().getValue()) );
    }


}
