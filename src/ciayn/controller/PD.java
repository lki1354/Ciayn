package ciayn.controller;

import ciayn.elements.Block;
import ciayn.elements.signal.Valuable;

/**
 * Created by lukas on 17.01.17.
 */
public class PD extends Block {
    private Number kd;
    private Number kp;
    private Number dt;
    private Valuable up = null;
    private Valuable ud = null;
    private Valuable e1 = null;

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
    public Valuable runAlgorithm(Valuable e) {
        this.ud = e;
        this.ud.subtractValue(this.e1.getValue());
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
