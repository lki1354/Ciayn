package ciayn.elements;

import ciayn.elements.signal.Updatable;
import ciayn.elements.signal.Updater;
import ciayn.elements.signal.Value;

/**
 * Created by lukas on 24.01.17.
 */
public class Input extends Updater{
    private Value value = null;

    public Input(Value value){
        this.value = value;
    }
    public Input(Value value, Updatable updater){
        this.updater = updater;
        this.value = value;
        //this.value = this.updater.updateActualValue();
    }

    @Override
    public Value updateActualValue() {
        if (updater != null) {
            this.value = this.updater.updateActualValue();
        }
        return this.value;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
