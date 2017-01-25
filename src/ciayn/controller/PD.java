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

    private void initValues(Class c) throws IllegalAccessException, InstantiationException {
        this.up = (Value) c.newInstance();
        this.ud = (Value) c.newInstance();
        this.e1 = (Value) c.newInstance();
    }

    public PD(Class c,Number kd, Number kp) throws InstantiationException, IllegalAccessException {
        this.kd = kd;
        this.kp= kp;
        initValues(c);
    }
    public PD(Class c,Number kd, Number kp, Number dt) throws InstantiationException, IllegalAccessException {
        this.kd = kd;
        this.kp = kp;
        this.dt = dt;
        initValues(c);
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
        if (this.dt == null) {
            this.dt = e.getTimeStamp() - this.ud.getTimeStamp();
        }
        this.ud.setValue(e);
        this.ud.subtractValue(this.e1);
        this.ud.multiplyValue(this.kd);
        this.ud.divideValue(this.dt);

        this.up.setValue(e);
        this.up.addValue(this.ud.getValue());
        this.up.multiplyValue(this.kp);

        this.e1.setValue(e);
        return this.up;
    }

    public void setKp(Number kp) {
        this.kp = kp;
    }
    public Number getKp() {
        return this.kp;
    }

}
