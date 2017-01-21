package ciayn.basicTerms;

import ciayn.elements.Block;
import ciayn.elements.Signal;

/**
 * Created by lukas on 17.01.17.
 */
public class Derivative extends Block {
    private float kd;
    private float dt;
    private float lastInput;
    private Signal diff = new Signal();

    public Derivative(float kd, float dt){
        this.kd = kd;
        this.dt = dt;
        this.lastInput = 0;
    }
    public Derivative(float kd, float dt, float lastInit){
        this.kd = kd;
        this.dt = dt;
        this.lastInput = lastInit;
    }
    public Derivative(){
        this.kd = 0;
        this.lastInput = 0;
    }

    @Override
    public void loadInput(Signal signal) {
        this.diff.setValue((signal.getValue()-this.lastInput) * this.kd / this.dt);
        this.output.loadInput(this.diff);
    }

    public float getKd() {
        return this.kd;
    }

    public void setKd(float kd) {
        this.kd = kd;
    }

    public float getDt() {
        return this.dt;
    }

    public void setDt(float dt) {
        this.dt = dt;
    }
}
