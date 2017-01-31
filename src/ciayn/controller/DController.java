package ciayn.controller;

import ciayn.elements.signal.Value;
import ciayn.logger.Loggable;
import ciayn.logger.LoggableAbstract;

/**
 * Created by lukas on 17.01.17.
 */
public abstract class DController extends LoggableAbstract implements Controller {
    private Number Td;
    private Number dt;
    private Number lastInput;
    private Value ud;

    /**
     * Instructor for differential controller
     *
     * @param c  Class which data type the controller has to use
     * @param Td time constant for the differential part
     * @param dt sample time of the controller
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public DController(Class c, Number Td, Number dt) throws IllegalAccessException, InstantiationException {
        this.Td = Td;
        this.dt = dt;
        this.lastInput = 0;
        initD(c);
    }

    /**
     * Instructor for differential controller
     *
     * @param c  Class which data type the controller has to use
     * @param Td time constant for the differential part
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public DController(Class c, Number Td) throws InstantiationException, IllegalAccessException {
        this.Td = Td;
        this.lastInput = 0;
        initD(c);
    }

    private void initD(Class c) throws IllegalAccessException, InstantiationException {
        this.ud = (Value) c.newInstance();
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

    public Value runAlgorithmDController(Value e) {
        if (this.dt == null) {
            this.dt = e.getTimeStamp() - this.ud.getTimeStamp();
            if (this.logger != null) {
                this.logger.log("delta time calculated dt=", this.dt);
            }
        }

        this.ud.setValue(e);
        this.ud.subtractValue(this.lastInput);
        this.ud.divideValue(this.dt);


        this.lastInput = e.getValue();
        if (this.logger != null) {
            this.logger.log("error signal e", e);
            this.logger.log("output signal ud", this.ud);
        }
        return this.ud;
    }
}
