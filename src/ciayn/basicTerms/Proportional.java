package ciayn.basicTerms;

import ciayn.elements.Block;
import ciayn.elements.Signal;

/**
 * Created by lukas on 17.01.17.
 */
public class Proportional extends Block {
    private float kp;
    private Signal p = new Signal();

    public Proportional(float kp){
        this.kp = kp;
    }
    public Proportional(){
        this.kp = 0;
    }

    @Override
    public void loadInput(Signal signal) {
        this.p.setValue(signal.getValue() * this.kp);
        this.output.loadInput(this.p);
    }

    public float getKp() {
        return this.kp;
    }

    public void setKp(float kp) {
        this.kp = kp;
    }
}
