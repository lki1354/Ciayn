package ciayn.elements.signal;

/**
 * Created by lukas on 22.01.17.
 */
public interface Valuable{
    float getValue();
    void setValue(Number value);
    //void setValue(Valuable value);
    void setValue(Number value, long timeStemp);
    void addValue(Number toAdd);
    void subtractValue(Number toSubtract);
    void multiplyValue(Number toMultiply);
    void divideValue(Number toDivide);
    long getTimeStamp();
    void setTimeStamp(long timeStamp);
}
