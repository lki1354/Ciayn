package ciayn.controller;

import ciayn.elements.signal.Value;

/**
 * Created by lukas on 17.01.17.
 */
public class PID implements Controller {
    private Number k;
    private Number Td;
    private Number Ti;
    private Number dt;
    private Number lastInput;
    private Value sum;
    private Value u;
    private Value i;
    private Value d;

    private void initValues(Class c) throws IllegalAccessException, InstantiationException {
        this.sum = (Value) c.newInstance();
        this.u = (Value) c.newInstance();
        this.i = (Value) c.newInstance();
        this.d = (Value) c.newInstance();
    }

    public PID(Class c,Number k, Number Ti, Number Td, Number dt) throws IllegalAccessException, InstantiationException {
        this.k = k;
        this.Ti = Ti;
        this.Td = Td;
        this.dt = dt;
        this.lastInput = 0;
        initValues(c);
    }
    public PID(Class c,Number k, Number Ti, Number Td) throws InstantiationException, IllegalAccessException {
        this.k = k;
        this.Ti = Ti;
        this.Td = Td;
        this.lastInput = 0;
        initValues(c);
    }

    public Number getTd() {
        return this.Td;
    }

    public void setKd(Number Td) {
        this.Td = Td;
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
        if (this.dt == null) {
            this.dt = e.getTimeStamp() - this.u.getTimeStamp();
        }
        this.u.setValue(e);

        this.d.setValue(e);
        this.d.subtractValue(this.lastInput);
        this.d.divideValue(this.dt);

        this.i.setValue(e);
        this.sum.addValue(e);
        this.i.addValue(this.sum);
        this.i.multiplyValue(this.Ti);

        this.u.addValue(this.d);
        this.u.addValue(this.i);
        this.u.multiplyValue(this.k);

        this.lastInput = e.getValue();
        return this.u;
    }
}
