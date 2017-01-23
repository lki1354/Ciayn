package ciayn.elements;


import ciayn.elements.signal.Valuable;

public interface Transferable {

    void addOutputConnection(Transferable connection) throws TransferableException;
    boolean isInputConnected();
    void addInputConnection(Transferable input);

    void removeOutputConnection(Transferable connection) throws TransferableException;

    void loadInput(Valuable value);

    void draw();

}
