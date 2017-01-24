package ciayn.elements;

import ciayn.elements.signal.Callbackable;
import ciayn.elements.signal.Value;

/**
 * Created by lukas on 24.01.17.
 */
public class Output {
    private Value value = null;
    private Callbackable callback = null;

    public Output(Value value){
        this.value = value;
    }
    public Output(){
    }
    public Output(Callbackable callback){
        this.setCallback(callback);
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
        this.callback.callbackActualValue(this.value);
    }

    public void setCallback(Callbackable callback) {
        this.callback = callback;
    }
}