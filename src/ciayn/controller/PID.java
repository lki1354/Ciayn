package ciayn.controller;

import ciayn.elements.Block;
import ciayn.elements.signal.Signal;

/**
 * Created by lukas on 17.01.17.
 */
public class PID extends Block {
    private float kd;
    private float kp;
    private float ki;
    private float dt;
    private float lastInput;
    private Signal out = new Signal();
    private Signal e = new Signal();
    private Signal p = new Signal();
    private Signal i = new Signal();
    private Signal d = new Signal();

    public PID(float kp, float ki, float kd, float dt){
        this.kd = kd;
        this.dt = dt;
        this.lastInput = 0;
    }
    public PID(float kd, float dt, float lastInit){
        this.kd = kd;
        this.dt = dt;
        this.lastInput = lastInit;
    }
    public PID(){
        this.kd = 0;
        this.lastInput = 0;
    }

    @Override
    public void loadInput(Signal error) {
        this.p.setValue(error.getValue()*this.kp);
        this.i.addValue(error.getValue()*this.ki*this.dt);
        this.d.setValue((error.getValue()-this.lastInput) * this.kd / this.dt);
        this.out.setValue(p.getValue()+i.getValue()+d.getValue());
        this.output.loadInput(this.out );
    }

    public void loadInput(Signal target, Signal actual) {
        this.e.setValue(target.getValue()-actual.getValue());
        this.p.setValue(e.getValue()*this.kp);
        this.i.addValue(e.getValue()*this.ki*this.dt);
        this.d.setValue((e.getValue()-this.lastInput) * this.kd / this.dt);
        this.out.setValue(p.getValue()+i.getValue()+d.getValue());
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

    public float getKp() {
        return this.kp;
    }

    public void setKp(float kp) {
        this.kp = kp;
    }

    public float getKi() {
        return ki;
    }

    public void setKi(float ki) {
        this.ki = ki;
    }
}
