package ciayn.elements;


public interface Transferable {

    void addOutputConnection(Transferable connection) throws TransferableException;
    boolean isInputConnected();
    void addInputConnection(Transferable input);

    void removeOutputConnection(Transferable connection) throws TransferableException;

    void loadInput(Signal signal);

    void draw();

}
