package ciayn.elements;

import ciayn.elements.signal.Value;

import java.util.List;

/**
 * Created by lukas on 14.01.17.
 */
public abstract class Block implements Transferable{
    private Input blockOutput = null;
    private List<Output> blockInput = null;
    protected boolean isConnected = false;

    public Block(){

    }

    @Override
    public void addOutputConnection(Input input){
        this.blockOutput = input;

    }

    @Override
    public void addInputConnection(Output output) {
        this.blockInput.add(output);
    }
    public void addInputConnection(int index,Output output) {
        this.blockInput.add(index,output);
    }

    @Override
    public void loadInput(Value value) {
        value = this.runAlgorithm(value);
        this.blockOutput.setValue(value);
    }
    public void loadInput(int index, Value value) {
        value = this.runAlgorithm(value);
        this.blockOutput.setValue(value);
    }

    public abstract Value runAlgorithm(Value input);

}
