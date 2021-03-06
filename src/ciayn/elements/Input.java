package ciayn.elements;

import ciayn.elements.signal.Value;
import ciayn.logger.LoggableAbstract;

/**
 * Created by lukas on 24.01.17.
 */
public class Input extends LoggableAbstract implements Callable {
    private Value value = null;
    private Updatable updater = null;

    public Input(Value value) {
        this.value = value;
    }

    public Input(Value value, Updatable updater) {
        this.updater = updater;
        this.value = value;
    }

    public Output createOutput() {
        return new Output(this.value, this);
    }

    public Value getValue() {
        if (this.updater != null) {
            this.value = this.updater.updateActualValue();
        }
        return this.value;
    }

    public void setValue(Value value) {
        this.value = value;
        if (this.logger != null) {
            this.logger.log(this.value);
        }
    }

    public void setUpdater(Updatable updater) {
        this.updater = updater;
    }

    @Override
    public void callbackActualValue(Value value) {
        this.value.setValue(value);
    }

}
