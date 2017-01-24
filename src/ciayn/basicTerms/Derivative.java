package ciayn.basicTerms;

import ciayn.elements.Block;
import ciayn.elements.signal.Valueable;

/**
 * Created by lukas on 17.01.17.
 */
public class Derivative extends Block {
    private Number kd;
    private Number dt;
    private Number lastInput;
    private Valueable diff;

    public Derivative(Number kd, Number dt){
        this.kd = kd;
        this.dt = dt;
        this.lastInput = 0;
    }
    public Derivative(Number kd, Number dt, Number lastInit){
        this.kd = kd;
        this.dt = dt;
        this.lastInput = lastInit;
    }
    public Derivative(){
        this.kd = 0;
        this.lastInput = 0;
    }

    @Override
    public void loadInput(Valueable valueable) {
        this.diff = valueable;
        this.diff.subtractValue(this.lastInput);
        this.diff.multiplyValue(this.kd);
        this.diff.divideValue(this.dt);
        this.output.loadInput(this.diff);
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
}
