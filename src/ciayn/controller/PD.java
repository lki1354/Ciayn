package ciayn.controller;

import ciayn.elements.signal.Value;

/**
 * Created by lukas on 17.01.17.
 */
public class PD implements Controller{
    private Number kd;
    private Number kp;
    private Number dt;
    private Value up = null;
    private Value ud = null;
    private Value e1 = null;

    public PD(Number kd, Number kp){
        this.kd = kd;
        this.kp= kp;
    }
    public PD(Number kd, Number kp, Number dt){
        this.kd = kd;
        this.kp = kp;
        this.dt = dt;
    }
    public PD(){
    }

    public Number getKd() {
        return this.kd;
    }

    public void setKd(Number kd) {
        this.kd = kd;
    }

    public Number getDt() {
        return this.dt;
    }

    public void setDt(Number dt) {
        this.dt = dt;
    }

    @Override
    public Value runAlgorithm(Value e) {
        this.ud = e;
        this.ud.subtractValue(this.e1);
        this.ud.multiplyValue(this.kd);
        this.ud.divideValue(this.dt);

        this.up = e;
        this.up.multiplyValue(this.kp);
        this.up.addValue(this.ud.getValue());
        return this.up;
    }

    public void setKp(Number kp) {
        this.kp = kp;
    }
    public Number getKp() {
        return this.kp;
    }

}
