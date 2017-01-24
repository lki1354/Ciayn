package ciayn.elements;


import ciayn.elements.signal.Updater;
import ciayn.elements.signal.Value;

/**
 * Created by lukas on 13.01.17.
 */
public class Wire extends Updater implements Transferable{
    private Input wireOutput = null;
    private Output wireInput = null;

    public Wire(Value initValue){
        this.wireOutput = new Input(initValue);
    }

    @Override
    public void addOutputConnection(Input input){
        this.wireOutput= input;
    }

    @Override
    public void addInputConnection(Output output) {
        this.wireInput = output;
    }

    @Override
    public void loadInput(Value value){
        this.wireOutput.setValue(value);
     //   for (Transferable output: outputs ) {
     //       output.loadInput(value);
     //   }
    }

    public Output getWireInput() {
        return wireInput;
    }
    public Input getWireOutput() {
        return wireOutput;
    }

    @Override
    public Value updateActualValue() {
        return this.wireOutput.getValue();
    }
}
