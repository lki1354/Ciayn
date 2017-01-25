package ciayn.elements.signal;


import ciayn.elements.Callable;
import ciayn.elements.Input;
import ciayn.elements.Output;
import ciayn.elements.Updatable;

import java.util.List;

/**
 * Created by lukas on 13.01.17.
 */


public abstract class Signal implements Updatable,Callable {
    protected Input input;
    protected Output output;
    public abstract List getSignalValues();
    public abstract void setUpdater(Updatable updater);
    public abstract void setActualValue(Number value);
    public abstract void setActualValue(Value value);
    public abstract void setActualValue(Number value, float timeStemp);
    public abstract Value getActualValue();
    public Input getInput(){
        return input;
    }
    public Output getOutput(){
        return output;
    }
}
