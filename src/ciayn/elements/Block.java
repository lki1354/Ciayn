package ciayn.elements;

import ciayn.elements.signal.Valuable;

/**
 * Created by lukas on 14.01.17.
 */
public abstract class Block implements Transferable{
    protected Transferable output = null;
    protected boolean isConnected = false;

    @Override
    public boolean isInputConnected() {
        return this.isConnected;
    }

    @Override
    public void addInputConnection(Transferable input) {
        this.isConnected = true;
    }

    @Override
    public void addOutputConnection(Transferable connection) throws TransferableException {
        if (output == null){
            if(!connection.isInputConnected()){
                output = connection;
                output.addInputConnection(this);
            }
        }else {
            throw new TransferableException("a abstract block class can have only one output connection");
        }
    }
    @Override
    public void removeOutputConnection(Transferable connection) throws TransferableException {
        if (connection == output){
            output = null;
        }else {
            throw new TransferableException("connection is not connected with this blocks");
        }
    }
    @Override
    public void loadInput(Valuable value) {
        value = this.runAlgorithm(value);
        this.output.loadInput(value);
    }

    @Override
    public void draw(){
        System.out.println("-------");
        System.out.println("|     |");
        System.out.println("-------");
        output.draw();
    }
    public abstract Valuable runAlgorithm(Valuable input);
}
