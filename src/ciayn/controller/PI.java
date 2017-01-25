package ciayn.controller;

import ciayn.elements.signal.Value;

/**
 * Created by lukas on 17.01.17.
 */
public class PI implements Controller{
    private Number k;
    private Number Ti;
    private Number dt;
    private Value sum;
    private Value u;
    private Value i;

    public PI(Number Ti, Number dt){
        this.Ti = Ti;
        this.dt = dt;
    }
    public PI(Number kd, Number dt, Number initSum){
        this.Ti = kd;
        this.dt = dt;
        this.sum.setValue(initSum);
    }
    public PI(){
        this.Ti = 0;
    }


    public Number getDt() {
        return this.dt;
    }

    public void setDt(Number dt) {
        this.dt = dt;
    }

    public Number getKp() {
        return this.k;
    }

    public void setKp(Number k) {
        this.k = k;
    }

    public Number getKi() {
        return Ti;
    }

    public void setKi(Number Ti) {
        this.Ti = Ti;
    }

    @Override
    public Value runAlgorithm(Value e) {
        this.dt = e.getTimeStamp() - this.u.getTimeStamp();
        this.u = e;

        this.i = e;
        this.sum.addValue(e);
        this.i.addValue(this.sum);
        this.i.multiplyValue(this.Ti);


        this.u.addValue(this.i);
        this.u.multiplyValue(this.k);

        return this.u;
    }

}
