package ciayn.elements;

import ciayn.elements.signal.Value;

/**
 * Created by lukas on 24.01.17.
 */
public class Output implements Updatable {
    private Value value = null;
    private Callable callback = null;

    public Output(Value value, Callable callback){
        this.value = value;
        this.callback = callback;
    }

    public Output(Value value){
        this.value = value;
    }


    public Output(Callable callback){
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

    public void setCallback(Callable callback) {
        this.callback = callback;
    }

    @Override
    public Value updateActualValue() {
        return this.value;
    }
}