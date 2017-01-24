package ciayn.elements.signal;


import java.util.List;

/**
 * Created by lukas on 13.01.17.
 */


public abstract class Signal implements Updatable  {
    public abstract List getSignalValues();
    public abstract void setUpdater(Updater updater);
    public abstract void setActualValue(Number value);
    public abstract void setActualValue(Value value);
    public abstract void setActualValue(Number value, float timeStemp);
    public abstract Value getActualValue();
}
