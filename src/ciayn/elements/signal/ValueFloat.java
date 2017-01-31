package ciayn.elements.signal;

/**
 * Created by lukas on 22.01.17.
 */
public class ValueFloat extends Value{
    protected float value;
    protected float timeStamp;

    public ValueFloat(){
        this.value = 0;
        this.timeStamp = System.nanoTime()/1000000000f;
    }

    public ValueFloat(float value){
        this.setValue(value);
        this.timeStamp = System.nanoTime()/1000000000f;
    }

    public ValueFloat(float value, float timeStamp){
        this.setValue(value,timeStamp);
    }

    @Override
    public Number getValue() {
        return this.value;
    }

    @Override
    public void setValue(Number value) {
       this.value = value.floatValue();
       this.timeStamp = System.nanoTime()/1000000000f;
    }

    @Override
    public void setValue(Value value) {
        this.setValue(value.getValue());
        this.setTimeStamp(value.getTimeStamp());
    }

    @Override
    public void setValue(Number value, float timeStamp){
        this.value = value.floatValue();
        this.timeStamp = timeStamp;
    }

    @Override
    public void addValue(Number toAdd) {
        this.value += toAdd.floatValue();
    }

    @Override
    public void addValue(Value toAdd) {
        this.addValue(toAdd.getValue());
    }

    @Override
    public void subtractValue(Number toSubtract) {
        this.value -= toSubtract.floatValue();
    }

    @Override
    public void subtractValue(Value toSubtract) {
        this.subtractValue(toSubtract.getValue());
    }

    @Override
    public void multiplyValue(Number toMultiply) {
        this.value *= toMultiply.floatValue();
    }

    @Override
    public void divideValue(Number toDivide) {
        this.value /= toDivide.floatValue();
        if(Float.isNaN(value)){
            this.value = 0f;
        }
    }
    @Override
    public float getTimeStamp() {
        return this.timeStamp;
    }
    @Override
    public void setTimeStamp(float timeStamp) {
        this.timeStamp = timeStamp;
    }

}
