package ciayn.systems;

import ciayn.elements.Block;
import ciayn.elements.signal.Signal;
import ciayn.elements.signal.Valuable;

/**
 * Created by lukas on 17.01.17.
 */
public class PT1 extends Block {
    private Number K;
    private Number T;
    private Number dt;
    private Number Ts;
    //private Valuable u = null;
    private Valuable y0 = null;
    private Valuable y1 = null;

    public PT1(Number K,Number T, Number dt){
        this.K = K;
        this.T = T;
        this.dt = dt;
        this.Ts = 1/(1+T.floatValue()/dt.floatValue());
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
    public Valuable runAlgorithm(Valuable u) {
        this.y1 = this.y0;
        this.y0 = u;
        this.y0.multiplyValue(this.K);
        this.y0.subtractValue(this.y1.getValue());
        this.y0.multiplyValue(this.Ts);
        this.y0.addValue(this.y1.getValue());
        return this.y0;
    }

}
