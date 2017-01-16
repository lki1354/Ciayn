package ciayn.elements;


public interface Transferable {

    void addOutputConnection(Transferable connection) throws TransferableException;

    void removeOutputConnection(Transferable connection) throws TransferableException;

    void loadInput(Signal signal);

}
