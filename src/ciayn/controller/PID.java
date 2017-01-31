package ciayn.controller;

import ciayn.elements.signal.Value;
import ciayn.logger.LoggableAbstract;

/**
 * Created by lukas on 17.01.17.
 */
public class PID extends LoggableAbstract implements Controller {
    private Number k;
    private Number Td;
    private Number Ti;
    private Number dt;
    private Number lastInput;
    private Value sum;
    private Value up;
    private Value ui;
    private Value ud;

    private void initValues(Class c) throws IllegalAccessException, InstantiationException {
        this.sum = (Value) c.newInstance();
        this.up = (Value) c.newInstance();
        this.ui = (Value) c.newInstance();
        this.ud = (Value) c.newInstance();
    }

    public PID(Class c, Number k, Number Ti, Number Td, Number dt) throws IllegalAccessException, InstantiationException {
        this.k = k;
        this.Ti = Ti;
        this.Td = Td;
        this.dt = dt;
        this.lastInput = 0;
        initValues(c);
    }
    public PID(Class c, Number k, Number Ti, Number Td) throws InstantiationException, IllegalAccessException {
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

    protected Value runAlgorithmPIDController(Value e) {
        if (this.dt == null) {
            this.dt = e.getTimeStamp() - this.up.getTimeStamp();
            if (this.logger != null){
                this.logger.log("delta time calculated dt=",this.dt);
            }
        }
        this.up.setValue(e);

        this.ud.setValue(e);
        this.ud.subtractValue(this.lastInput);
        this.ud.divideValue(this.dt);

        this.ui.setValue(e);
        this.ui.addValue(this.sum);
        this.ui.multiplyValue(this.Ti);
        this.sum.addValue(e);

        this.up.addValue(this.ud);
        this.up.addValue(this.ui);
        this.up.multiplyValue(this.k);

        this.lastInput = e.getValue();
        if (this.logger != null){
            this.logger.log("error signal e",e);
            this.logger.log("output signal up",this.ui);
            this.logger.log("intern signal ud",this.ui);
            this.logger.log("intern signal lastInput",this.lastInput);
            this.logger.log("intern signal ud",this.ui);
            this.logger.log("intern signal ui",this.ui);
            this.logger.log("intern signal sum",this.sum);
        }
        return this.up;
    }

    @Override
    public Value runAlgorithm(Value e) {
        return runAlgorithmPIDController(e);
    }
}
