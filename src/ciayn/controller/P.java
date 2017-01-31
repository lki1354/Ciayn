package ciayn.controller;

import ciayn.elements.signal.Value;

/**
 * Created by lukas on 25.01.17.
 */
public class P extends PController {

    /**
     * Instructor for the proportional controller
     *
     * @param c Class which data type the controller has to use
     * @param k Controller multiplication constant
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public P(Class c, Number k) throws IllegalAccessException, InstantiationException {
        super(c, k);
    }

    @Override
    public Value runAlgorithm(Value e) {
        return this.runAlgorithmPController(e);
    }
}
