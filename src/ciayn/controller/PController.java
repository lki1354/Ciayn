package ciayn.controller;

import ciayn.elements.signal.Value;
import ciayn.logger.LoggableAbstract;

/**
 * Created by lukas on 17.01.17.
 */
public abstract class PController extends LoggableAbstract implements Controller {
    private Number k;
    private Value uk;

    /**
     *  Instructor for the proportional controller
     * @param c Class which data type the controller has to use
     * @param k Controller multiplication constant
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public PController(Class c, Number k) throws IllegalAccessException, InstantiationException {
        this.k = k;
        initK(c);
    }

    private void initK(Class c) throws IllegalAccessException, InstantiationException {
        this.uk = (Value) c.newInstance();
    }

    public Number getK() {
        return this.k;
    }

    public void setK(Number k) {
        this.k = k;
    }

    protected Value runAlgorithmPController(Value e) {
        this.uk.setValue(e);

        this.uk.multiplyValue(this.k);

        if (this.logger != null){
            this.logger.log("error signal e",e);
            this.logger.log("output signal ud",this.uk);
        }

        return this.uk;
    }
}
