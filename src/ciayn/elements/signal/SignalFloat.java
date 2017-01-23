package ciayn.elements.signal;

import ciayn.elements.signal.unit.Unit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukas on 13.01.17.
 */


public class SignalFloat extends Signal {
    private List<ValueFloat> Values = new ArrayList<>();
    private Updater updater = null;
    private ReadingFloat actual;

    public SignalFloat(Unit unit){
        actual = new ReadingFloat(unit);
    }
    private void updateList(){
        this.Values.add( new ValueFloat(this.actual.getValue(),this.actual.getTimeStamp()) );
    }
    @Override
    public List getSignalValues(){
        return this.Values;
    }
    @Override
    public void setUpdater(Updater updater){
        this.updater= updater;
    }


    @Override
    public Valuable updateActualValue() {
        if( this.updater != null ) {
            this.actual = (ReadingFloat) this.updater.updateActualValue();
            updateList();
        }
        return this.actual;
    }
    @Override
    public void setActualValue(float value){
        this.actual.setValue(value);
        updateList();
    }

    @Override
    public void setActualValue(Valuable value) {
        this.setActualValue(value.getValue(),value.getTimeStamp());
    }

    @Override
    public void setActualValue(float value, long timeStemp){
        this.actual.setValue(value);
        this.actual.setTimeStamp(timeStemp);
        updateList();
    }
    @Override
    public Valuable getActualValue(){
        return this.actual;
    }

}
