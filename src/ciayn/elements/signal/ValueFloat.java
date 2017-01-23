package ciayn.elements.signal;

/**
 * Created by lukas on 22.01.17.
 */
public class ValueFloat implements Valuable {
    protected float value;
    protected long timeStamp;


    public ValueFloat(float value){
        this.setValue(value);
    }

    public ValueFloat(float value, long timeStamp){
        this.setValue(value,timeStamp);
    }

    @Override
    public float getValue() {
        return this.value;
    }

    @Override
    public void setValue(Number value) {
       this.value = value.floatValue();
    }

    @Override
    public void setValue(Number value, long timeStamp){
        this.value = value.floatValue();
        this.timeStamp = timeStamp;
    }

    @Override
    public void addValue(Number toAdd) {
        this.value += toAdd.floatValue();
    }

    @Override
    public void subtractValue(Number toSubtract) {
        this.value -= toSubtract.floatValue();
    }

    @Override
    public void multiplyValue(Number toMultiply) {
        this.value *= toMultiply.floatValue();
    }

    @Override
    public void divideValue(Number toDivide) {
        this.value /= toDivide.floatValue();
    }
    @Override
    public long getTimeStamp() {
        return timeStamp;
    }
    @Override
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
