package ciayn.elements;


import ciayn.elements.signal.Value;

public interface Transferable {

    void addOutputConnection(Input input) throws TransferableException;
    //boolean isInputConnected();
    void addInputConnection(Output output);

    //void removeOutputConnection(Transferable connection) throws TransferableException;

    void loadInput(Value value);

}
