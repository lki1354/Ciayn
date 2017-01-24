package ciayn;

import ciayn.elements.signal.Value;
import ciayn.plant.Plant;
import ciayn.elements.Input;
import ciayn.elements.Output;


public class EnvPlant extends Env {
    // Signals 0 is input
    private Plant plant = null;
    private Value x = null;


    public EnvPlant(Plant plant, Output output) {
        super(output);
        this.setPlant(plant);
    }

    public void setPlant(Plant plant){
       this.plant = plant;
    }
    public Plant getPlant() {
        return plant;
    }

    @Override
    protected void doOneIteration(){
        this.x = this.plant.runAlgorithm(this.inputSignals.get(0).getValue());
        this.outputSignal.setValue(this.x);
    }


    public void setInput(Input input) {
        this.inputSignals.add(0,input);
    }
}
