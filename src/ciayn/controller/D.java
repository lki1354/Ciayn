package ciayn.controller;

import ciayn.elements.signal.Value;

/**
 * Created by lukas on 25.01.17.
 */
public class D extends DController {
    /**
     * Instructor for differential controller
     *
     * @param c  Class which data type the controller has to use
     * @param Td time constant for the differential part
     * @param dt sample time of the controller
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public D(Class c, Number Td, Number dt) throws IllegalAccessException, InstantiationException {
        super(c, Td, dt);
    }

    /**
     * Instructor for differential controller
     *
     * @param c  Class which data type the controller has to use
     * @param Td time constant for the differential part
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public D(Class c, Number Td) throws InstantiationException, IllegalAccessException {
        super(c, Td);
    }

    @Override
    public Value runAlgorithm(Value e) {
        return this.runAlgorithmDController(e);
    }
}
