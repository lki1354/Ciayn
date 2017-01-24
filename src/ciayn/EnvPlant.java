package ciayn;

import ciayn.elements.signal.Value;
import ciayn.plant.PT1;
import ciayn.plant.Plant;
import ciayn.elements.Input;
import ciayn.elements.Output;


public class EnvPlant extends Env {
    // Signals 0 is input
    private Plant plant = null;
    private Value x = null;

    private void initValues(Class c) throws IllegalAccessException, InstantiationException {
        this.x = (Value) c.newInstance();
    }

    public EnvPlant(Class c, Plant plant, Output output) throws InstantiationException, IllegalAccessException {
        super(output);
        this.setPlant(plant);
        initValues(c);
    }

    public static EnvPlant createEnvPT1(Class c,Number K,Number T, Number dt,Input input ,Output output) throws IllegalAccessException, InstantiationException {
        EnvPlant env = new EnvPlant(c,new PT1(c,K,T,dt),output);
        env.setInput(input);
        return env;
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
