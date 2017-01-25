package ciayn.controller;

import ciayn.elements.signal.Value;

/**
 * Created by lukas on 25.01.17.
 */
public class I extends IController {
    /**
     * Instuctor for itegral controller
     *
     * @param c  Class which data type the controller has to use
     * @param Ti time constant for the integral part
     * @param dt sample time of the controller
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public I(Class c, Number Ti, Number dt) throws IllegalAccessException, InstantiationException {
        super(c, Ti, dt);
    }

    /**
     * Instuctor for itegral controller
     *
     * @param c  Class which data type the controller has to use
     * @param Ti time constant for the integral part
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public I(Class c, Number Ti) throws InstantiationException, IllegalAccessException {
        super(c, Ti);
    }

    @Override
    public Value runAlgorithm(Value e) {
        return this.runAlgorithmIController(e);
    }
}
