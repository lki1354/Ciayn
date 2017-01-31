package ciayn.controller;

import ciayn.elements.signal.Value;

/**
 * Created by lukas on 17.01.17.
 */
public class PD extends DController {
    private Number k;
    private Value up = null;

    /**
     * Instructor for proportional and differential controller
     *
     * @param c  Class which data type the controller has to use
     * @param k  Controller multiplication constant
     * @param Td time constant for the differential part
     * @param dt sample time of the controller
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public PD(Class c, Number k, Number Td, Number dt) throws IllegalAccessException, InstantiationException {
        super(c, Td, dt);
        this.k = k;
        initValues(c);
    }

    /**
     * Instructor for proportional and differential controller
     *
     * @param c  Class which data type the controller has to use
     * @param k  Controller multiplication constant
     * @param Td time constant for the differential part
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public PD(Class c, Number k, Number Td) throws InstantiationException, IllegalAccessException {
        super(c, Td);
        this.k = k;
        initValues(c);
    }

    private void initValues(Class c) throws IllegalAccessException, InstantiationException {
        this.up = (Value) c.newInstance();
    }

    @Override
    public Value runAlgorithm(Value e) {

        this.up.setValue(e);
        this.up.addValue(this.runAlgorithmDController(e));
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
