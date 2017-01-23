package ciayn.controller;

import ciayn.elements.Block;
import ciayn.elements.signal.Signal;

/**
 * Created by lukas on 17.01.17.
 */
public class PD extends Block {
    private float kd;
    private float kp;
    private float dt;
    private float lastInput;
    private Signal out = new Signal();
    private Signal e = new Signal();

    public PD(float kd, float dt){
        this.kd = kd;
        this.dt = dt;
        this.lastInput = 0;
    }
    public PD(float kd, float dt, float lastInit){
        this.kd = kd;
        this.dt = dt;
        this.lastInput = lastInit;
    }
    public PD(){
        this.kd = 0;
        this.lastInput = 0;
    }

    @Override
    public void loadInput(Signal error) {
        this.out.setValue((error.getValue()-this.lastInput) * this.kd / this.dt + this.kp*error.getValue());
        this.output.loadInput(this.out );
    }

    public void loadInput(Signal target, Signal actual) {
        this.e.setValue(target.getValue()-actual.getValue());
        this.out.setValue((this.e.getValue()-this.lastInput) * this.kd / this.dt + this.kp*this.e.getValue());
        this.output.loadInput(this.out );
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
