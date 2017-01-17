package ciayn.basicTerms;

import ciayn.elements.Block;
import ciayn.elements.Signal;

/**
 * Created by lukas on 17.01.17.
 */
public class Integral extends Block {
    private float ki;
    private float dt;
    private Signal sum = new Signal();

    public Integral(float ki, float dt){
        this.ki = ki;
        this.dt = dt;
        this.sum.setValue(0);
    }
    public Integral(float ki, float dt, float sumInit){
        this.ki = ki;
        this.dt = dt;
        this.sum.setValue(sumInit);
    }
    public Integral(){
        this.ki = 0;
        this.sum.setValue(0);
    }

    @Override
    public void loadInput(Signal signal) {
        this.sum.addUp(signal.getValue() * this.ki / this.dt);
        this.output.loadInput(this.sum);
    }

    public float getKi() {
        return this.ki;
    }

    public void setKi(float ki) {
        this.ki = ki;
    }

    public float getDt() {
        return this.dt;
    }

    public void setDt(float dt) {
        this.dt = dt;
    }
}
