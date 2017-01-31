package ciayn.elements.signal;

import ciayn.elements.Input;

import java.util.Iterator;

/**
 * Created by lukas on 22.01.17.
 */
public class ValueInt extends Value {
    protected int value;
    protected float timeStamp;

    public ValueInt(){
        this.value = 0;
        this.timeStamp = (int)System.nanoTime()/1000000;
    }

    public ValueInt(int value){
        this.setValue(value);
        this.timeStamp = (int)System.nanoTime()/1000000;
    }

    public ValueInt(int value, float timeStamp){
        this.setValue(value,timeStamp);
    }

    @Override
    public Number getValue() {
        return this.value;
    }

    @Override
    public void setValue(Number value) {
       this.value = value.intValue();
    }

    @Override
    public void setValue(Value value) {
        this.value = value.getValue().intValue();
        this.timeStamp = value.getTimeStamp() ;
    }

    @Override
    public void setValue(Number value, float timeStamp){
        this.value = value.intValue();
        this.timeStamp = timeStamp;
    }

    @Override
    public void addValue(Number toAdd) {
        this.value += toAdd.intValue();
    }

    @Override
    public void addValue(Value toAdd) {
        this.addValue(toAdd.getValue());
    }

    @Override
    public void subtractValue(Number toSubtract) {
        this.value -= toSubtract.intValue();
    }

    @Override
    public void subtractValue(Value toSubtract) {
        this.subtractValue(toSubtract.getValue());
    }

    @Override
    public void multiplyValue(Number toMultiply) {
        this.value *= toMultiply.intValue();
    }

    @Override
    public void divideValue(Number toDivide) {
        this.value /= toDivide.intValue();
        if(Float.isNaN(value)){
            this.value = 0;
        }
    }
    @Override
    public float getTimeStamp() {
        return timeStamp;
    }
    @Override
    public void setTimeStamp(float timeStamp) {
        this.timeStamp = timeStamp;
    }

}
