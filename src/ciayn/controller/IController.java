package ciayn.controller;

import ciayn.elements.signal.Value;
import ciayn.logger.LoggableAbstract;

/**
 * Created by lukas on 17.01.17.
 */
public abstract class IController extends LoggableAbstract implements Controller {
    private Number Ti;
    protected Number dt;
    private Value sum;
    private Value ui;

    /**
     * Instructor for integral controller
     *
     * @param c Class which data type the controller has to use
     * @param Ti time constant for the integral part
     * @param dt sample time of the controller
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public IController(Class c, Number Ti,Number dt) throws IllegalAccessException, InstantiationException {
        this.Ti = Ti;
        this.dt = dt;
        initI(c);
    }
      /**
     * Instuctor for itegral controller
     *
     * @param c Class which data type the controller has to use
     * @param Ti time constant for the integral part
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public IController(Class c,Number Ti) throws InstantiationException, IllegalAccessException {
        this.Ti = Ti;
        initI(c);
    }

    private void initI(Class c) throws IllegalAccessException, InstantiationException {
        this.sum = (Value) c.newInstance();
        this.ui = (Value) c.newInstance();
    }

    public Number getDt() {
        return this.dt;
    }

    public void setDt(Number dt) {
        this.dt = dt;
    }

    public Number getKi() {
        return Ti;
    }

    public void setKi(Number Ti) {
        this.Ti = Ti;
    }

    protected Value runAlgorithmIController(Value e) {
        if (this.dt == null) {
            this.dt = e.getTimeStamp() - this.ui.getTimeStamp();
            if (this.logger != null){
                this.logger.log("delta time calculated dt=",this.dt);
            }
        }

        this.ui.setValue(e);
        this.ui.addValue(this.sum);
        this.ui.multiplyValue(this.Ti);
        this.sum.addValue(e);

        if (this.logger != null){
            this.logger.log("error signal e",e);
            this.logger.log("output signal ui",this.ui);
            this.logger.log("intern signal sum",this.sum);
        }

        return this.ui;
    }
}
