package ciayn.controller;

import ciayn.elements.signal.Value;

/**
 * Created by lukas on 17.01.17.
 */
public class PI extends IController {
    private Number k;
    private Value up = null;

    /**
     * Instructor for proportional and integral controller
     *
     * @param c  Class which data type the controller has to use
     * @param k  Controller multiplication constant
     * @param Ti time constant for the integral part
     * @param dt sample time of the controller
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public PI(Class c, Number k, Number Ti, Number dt) throws IllegalAccessException, InstantiationException {
        super(c, Ti, dt);
        this.k = k;
        initValues(c);
    }

    /**
     * Instructor for proportional and integral controller
     *
     * @param c  Class which data type the controller has to use
     * @param k  Controller multiplication constant
     * @param Ti time constant for the integral part
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public PI(Class c, Number k, Number Ti) throws InstantiationException, IllegalAccessException {
        super(c, Ti);
        this.k = k;
        initValues(c);
    }

    private void initValues(Class c) throws IllegalAccessException, InstantiationException {
        this.up = (Value) c.newInstance();
    }

    @Override
    public Value runAlgorithm(Value e) {

        this.up.setValue(e);
        this.up.addValue(this.runAlgorithmIController(e));
        this.up.multiplyValue(this.k);

        if (this.logger != null) {
            this.logger.log("output signal up", this.up);
        }
        return this.up;
    }

    public Number getK() {
        return k;
    }

    public void setK(Number k) {
        this.k = k;
    }
}
