package ciayn.elements.signal;

import ciayn.logger.Loggable;

/**
 * Created by lukas on 22.01.17.
 */
public abstract class Value implements Loggable {
    public abstract Number getValue();
    public abstract void setValue(Number value);
    public abstract void setValue(Value value);
    public abstract void setValue(Number value, float timeStemp);
    public abstract void addValue(Number toAdd);
    public abstract void addValue(Value toAdd);
    public abstract void subtractValue(Number toSubtract);
    public abstract void subtractValue(Value toSubtract);
    public abstract void multiplyValue(Number toMultiply);
    public abstract void divideValue(Number toDivide);
    public abstract float getTimeStamp();
    public abstract void setTimeStamp(float timeStamp);
}
