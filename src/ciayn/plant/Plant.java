package ciayn.plant;

import ciayn.elements.Block;
import ciayn.elements.signal.Value;

/**
 * Created by lukas on 24.01.17.
 */
public abstract class Plant extends Block{
    public abstract void initPlant(Value value);
}
