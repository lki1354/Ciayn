package ciayn.controller;

import ciayn.elements.Block;
import ciayn.elements.signal.Value;

/**
 * Created by lukas on 24.01.17.
 */
public abstract class Controller extends Block{
    public abstract void initController(Value value);
}
