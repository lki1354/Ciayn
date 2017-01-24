package ciayn.elements.signal;

import ciayn.elements.signal.unit.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * Created by lukas on 13.01.17.
 */

public class ReadingFloat extends ValueFloat {
    //private float value;
    //private long timeStamp;
    private Unit unit;

    public ReadingFloat(Unit unit ){
        super(0);
        this.unit = unit;
    }
    public ReadingFloat(float value, Unit unit){
        super(value);
        this.unit = unit;
    }
    public ReadingFloat(float value,float timeStemp, Unit unit){
        super(value,timeStemp);
        this.unit = unit;
    }

    /*
    @Override
    public float getValue() {
        return value;
    }
    @Override
    public void setValue(Number value) {
        this.timeStamp = plant.nanoTime();
        this.value = value.floatValue();
    }
    @Override
    public void setValue(Number value, long timeStamp) {
        this.timeStamp = timeStamp;
        this.value = value.floatValue();
    }
    @Override
    public void addValue(Number toAdd){
        this.value += toAdd.floatValue();
    }
    @Override
    public void subtractValue(Number toSubtract){
        this.value += toSubtract.floatValue();
    }
    @Override
    public void multiplyValue(Number toMultiply){
        this.value *= toMultiply.floatValue();
    }
    @Override
    public void divideValue(Number toDivide){
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
    */

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
