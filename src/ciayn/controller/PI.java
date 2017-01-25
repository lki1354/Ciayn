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

      private void initValues(Class c) throws IllegalAccessException, InstantiationException {
        this.sum = (Value) c.newInstance();
        this.u = (Value) c.newInstance();
        this.i = (Value) c.newInstance();
    }

    public PI(Class c,Number Ti, Number dt) throws InstantiationException, IllegalAccessException {
        this.Ti = Ti;
        this.dt = dt;
        initValues(c);
    }
    public PI(Class c,Number kd, Number dt, Number initSum) throws InstantiationException, IllegalAccessException {
        this.Ti = kd;
        this.dt = dt;
        this.sum.setValue(initSum);
        initValues(c);
    }
    public PI(Class c) throws InstantiationException, IllegalAccessException {
        this.Ti = 0;
        initValues(c);
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
            this.dt = e.getTimeStamp() - this.i.getTimeStamp();
        }
        this.u.setValue(e);

        this.i.setValue(e);
        this.sum.addValue(e);
        this.i.addValue(this.sum);
        this.i.multiplyValue(this.Ti);


        this.u.addValue(this.i);
        this.u.multiplyValue(this.k);

        return this.u;
    }

}
