package ciayn.elements;

/**
 * Created by lukas on 14.01.17.
 */
public abstract class Block implements Transferable{
    protected Transferable output = null;

    @Override
    public void addOutputConnection(Transferable connection) throws TransferableException {
        if (output == null){
            output = connection;
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
    public void loadInput(Signal signal) {
        output.loadInput(signal);
    }
}
