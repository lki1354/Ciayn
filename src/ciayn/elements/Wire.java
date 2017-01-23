package ciayn.elements;

import ciayn.elements.signal.Signal;
import ciayn.elements.signal.Valuable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukas on 13.01.17.
 */
public class Wire implements Transferable{
    private List<Transferable>outputs = new ArrayList<>();
    private boolean isConnected = false;
    private Signal signal = null;

    public Wire(Signal signal){
        this.signal = signal;
    }

    public void addOutputConnection(Transferable connection){
        if(!connection.isInputConnected()) {
            outputs.add(connection);
            connection.addInputConnection(this);
        }
    }

    @Override
    public boolean isInputConnected() {
        return this.isConnected;
    }

    @Override
    public void addInputConnection(Transferable input) {
        this.isConnected = true;
    }

    @Override
    public void removeOutputConnection(Transferable connection) throws TransferableException {
        if (!outputs.remove(connection)) {
            throw new TransferableException("connection is not connected with this blocks");
        }

    }
    @Override
    public void loadInput(Valuable value){
        this.signal.setActualValue(value);
        for (Transferable output: outputs ) {
            output.loadInput(value);
        }
    }

    @Override
    public void draw() {
        System.out.println("       ");
        System.out.println("-------");
        for ( Transferable output: outputs) {
            output.draw();
        }
    }

}
