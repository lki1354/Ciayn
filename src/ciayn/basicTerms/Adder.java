package ciayn.basicTerms;

import ciayn.elements.Block;
import ciayn.elements.Signal;
import ciayn.elements.Transferable;
import ciayn.elements.TransferableException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukas on 17.01.17.
 */
public class Adder extends Block {
    private Signal sum = new Signal();
    private List<Transferable> inputs = new ArrayList<>();
    private int inputUpdates = 0;

    @Override
    public boolean isInputConnected() {
        return false;
    }

    @Override
    public void addInputConnection(Transferable input) {
        inputs.add(input);
    }

    public void removeInputConnection(Transferable connection) throws TransferableException {
        if (!inputs.remove(connection)) {
            throw new TransferableException("connection is not connected with this blocks");
        }

    }

    @Override
    public void loadInput(Signal signal) {
        if(this.inputUpdates == 0){
            this.sum.setValue(0);
        }
        this.sum.setValue(signal.getValue()+this.sum.getValue());
        this.inputUpdates++;
        if(this.inputUpdates >= this.inputs.size()){
            this.inputUpdates = 0;
            this.output.loadInput(this.sum);
        }
    }

}