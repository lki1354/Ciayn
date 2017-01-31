package ciayn.elements.signal;

import ciayn.logger.Logger;

/**
 * Created by lukas on 22.01.17.
 */
public class ValueFloat extends Value {
    protected float value;
    protected float timeStamp;
    protected Logger logger = null;

    public ValueFloat() {
        this.value = 0;
        this.timeStamp = System.nanoTime() / 1000000000f;
    }

    public ValueFloat(float value) {
        this.setValue(value);
        this.timeStamp = System.nanoTime() / 1000000000f;
    }

    public ValueFloat(float value, float timeStamp) {
        this.setValue(value, timeStamp);
    }

    @Override
    public Number getValue() {
        return this.value;
    }

    @Override
    public void setValue(Number value) {
        this.value = value.floatValue();
        this.timeStamp = System.nanoTime() / 1000000000f;
        logThis();
    }

    @Override
    public void setValue(Value value) {
        this.setValue(value.getValue());
        this.setTimeStamp(value.getTimeStamp());
        logThis();
    }

    @Override
    public void setValue(Number value, float timeStamp) {
        this.value = value.floatValue();
        this.timeStamp = timeStamp;
        logThis();
    }

    @Override
    public void addValue(Number toAdd) {
        this.value += toAdd.floatValue();
        logThis("Value was added");
    }

    @Override
    public void addValue(Value toAdd) {
        this.addValue(toAdd.getValue());
    }

    @Override
    public void subtractValue(Number toSubtract) {
        this.value -= toSubtract.floatValue();
        logThis("Value was subtracted");
    }

    @Override
    public void subtractValue(Value toSubtract) {
        this.subtractValue(toSubtract.getValue());
    }

    @Override
    public void multiplyValue(Number toMultiply) {
        this.value *= toMultiply.floatValue();
        logThis("Value was multiplied");
    }

    @Override
    public void divideValue(Number toDivide) {
        this.value /= toDivide.floatValue();
        if (Float.isNaN(value)) {
            logThis("Value is NaN after division");
            this.value = 0f;
        }
        logThis("Value was divided");
    }

    @Override
    public float getTimeStamp() {
        return this.timeStamp;
    }

    @Override
    public void setTimeStamp(float timeStamp) {
        this.timeStamp = timeStamp;
        logThis("time stamp of value was set");
    }

    @Override
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    private void logThis() {
        if (this.logger != null) {
            this.logger.log(this);
        }
    }

    private void logThis(String info) {
        if (this.logger != null) {
            this.logger.log(info, this);
        }
    }
}
