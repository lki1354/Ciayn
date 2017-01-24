package ciayn.elements;

import ciayn.elements.signal.Callbackable;
import ciayn.elements.signal.Updatable;
import ciayn.elements.signal.Updater;
import ciayn.elements.signal.Value;

/**
 * Created by lukas on 24.01.17.
 */
public class Output implements Updatable {
    private Value value = null;
    private Callbackable callback = null;

    public Output(Value value, Callbackable callback){
        this.value = value;
        this.callback = callback;
    }

    public Output(Value value){
        this.value = value;
    }


    public Output(Callbackable callback){
        this.setCallback(callback);
    }

    public Input createInput(){
        return new Input(this.value, this);
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
        if (this.callback != null) {
            this.callback.callbackActualValue(this.value);
        }
    }

    public void setCallback(Callbackable callback) {
        this.callback = callback;
    }

    @Override
    public Value updateActualValue() {
        return this.value;
    }
}