package ciayn.plant;

import ciayn.elements.signal.Value;

/**
 * Created by lukas on 17.01.17.
 */
public class PT1 extends Plant {
    private Number K;
    private Number T;
    private Number dt = null;
    private Number Ts;
    //private Value u = null;
    private Value y0 = null;
    private Value y1 = null;


    private void initValues(Class c) throws IllegalAccessException, InstantiationException {
        this.y0 = (Value) c.newInstance();
        this.y1 = (Value) c.newInstance();
    }

    public PT1(Class c,Number K,Number T, Number dt) throws InstantiationException, IllegalAccessException {
        this.K = K;
        this.T = T;
        this.dt = dt;
        this.Ts = 1/(1+T.floatValue()/dt.floatValue());
        initValues(c);
    }
    public PT1(Class c, Number K,Number T) throws InstantiationException, IllegalAccessException {
        this.K = K;
        this.T = T;
        initValues(c);
    }

    public Number getK() {
        return K;
    }

    public void setK(Number k) {
        K = k;
    }

    public Number getT() {
        return T;
    }

    public void setT(Number t) {
        T = t;
    }

    public Number getDt() {
        return dt;
    }

    public void setDt(Number dt) {
        this.dt = dt;
    }

    public Number getTs() {
        return Ts;
    }

    public void setTs(Number ts) {
        Ts = ts;
    }

    @Override
    public Value runAlgorithm(Value u) {
        this.y1 = this.y0;
        this.y0 = u;
        if(dt == null){
            this.Ts = 1/(1+this.T.floatValue()/(this.y0.getTimeStamp() - this.y1.getTimeStamp()));

        }else{
            this.Ts = 1/(1+T.floatValue()/dt.floatValue());
        }
        this.y0.multiplyValue(this.K);
        this.y0.subtractValue(this.y1.getValue());
        this.y0.multiplyValue(this.Ts);
        this.y0.addValue(this.y1.getValue());
        return this.y0;
    }

    @Override
    public void initPlant(Value value) {
        this.y0 = value;
        this.y1 = value;
    }
}
