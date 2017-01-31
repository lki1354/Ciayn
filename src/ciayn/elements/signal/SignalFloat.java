package ciayn.elements.signal;

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

    public SignalFloat(Unit unit){
        actual = new ReadingFloat(unit);
    }
    private void updateList(){
        this.Values.add( new ValueFloat(this.actual.getValue().floatValue(),this.actual.getTimeStamp()) );
    }
    @Override
    public List getSignalValues(){
        return this.Values;
    }
    @Override
    public void setUpdater(Updatable updater){
        this.updater= updater;
    }

    @Override
    public Value updateActualValue() {
        if( this.updater != null ) {
            this.actual = (ReadingFloat) this.updater.updateActualValue();
            updateList();
        }
        return this.actual;
    }
    @Override
    public void setActualValue(Number value){
        this.actual.setValue(value);
        updateList();
    }

    @Override
    public void setActualValue(Value value) {
        this.setActualValue(value.getValue(), value.getTimeStamp());
    }

    @Override
    public void setActualValue(Number value, float timeStemp){
        this.actual.setValue(value);
        this.actual.setTimeStamp(timeStemp);
        updateList();
    }
    @Override
    public Value getActualValue(){
        return this.actual;
    }

    @Override
    public void callbackActualValue(Value value) {
       this.setActualValue(value);
    }
}
