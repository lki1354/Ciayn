package ciayn.elements.signal;

import ciayn.elements.signal.unit.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * Created by lukas on 13.01.17.
 */

public class ReadingFloat extends ValueFloat {
    //private float value;
    //private long timeStamp;
    private Unit unit;

    public ReadingFloat(Unit unit) {
        super(0);
        this.unit = unit;
    }

    public ReadingFloat(float value, Unit unit) {
        super(value);
        this.unit = unit;
    }

    public ReadingFloat(float value, float timeStemp, Unit unit) {
        super(value, timeStemp);
        this.unit = unit;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
