package ciayn.elements.signal;

import ciayn.elements.Input;
import ciayn.elements.Output;
import ciayn.elements.Updatable;
import ciayn.elements.signal.unit.Unit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukas on 13.01.17.
 */


public class SignalFloat extends Signal {
    private List<ValueFloat> Values = new ArrayList<>();
    private Updatable updater = null;
    private ReadingFloat actual;

    public SignalFloat(Unit unit) {
        this.actual = new ReadingFloat(unit);
        this.input = new Input(actual, this);
        this.output = new Output(actual, this);
    }

    private void updateList() {
        this.Values.add(new ValueFloat(this.actual.getValue().floatValue(), this.actual.getTimeStamp()));
    }

    @Override
    public List<ValueFloat> getSignalValues() {
        return this.Values;
    }

    @Override
    public void setUpdater(Updatable updater) {
        this.updater = updater;
    }

    @Override
    public Value updateActualValue() {
        if (this.updater != null) {
            this.actual = (ReadingFloat) this.updater.updateActualValue();
            updateList();
        }
        return this.actual;
    }

    @Override
    public void setActualValue(Number value) {
        this.actual.setValue(value);
        updateList();
        if (this.logger != null) {
            this.logger.log(this.actual);
        }
    }

    @Override
    public void setActualValue(Value value) {
        this.setActualValue(value.getValue(), value.getTimeStamp());
    }

    @Override
    public void setActualValue(Number value, float timeStemp) {
        this.actual.setValue(value);
        this.actual.setTimeStamp(timeStemp);
        updateList();
        if (this.logger != null) {
            this.logger.log(this.actual);
        }
    }

    @Override
    public Value getActualValue() {
        return this.actual;
    }

    @Override
    public void callbackActualValue(Value value) {
        this.setActualValue(value);
    }

    public Unit getUnit() {
        return this.actual.getUnit();
    }
}
