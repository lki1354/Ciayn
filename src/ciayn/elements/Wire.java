package ciayn.elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukas on 13.01.17.
 */
public class Wire implements Transferable{
    private List<Transferable>outputs = new ArrayList<>();

    public void addOutputConnection(Transferable connection){
        outputs.add(connection);
    }

    @Override
    public void removeOutputConnection(Transferable connection) throws TransferableException {
        if (!outputs.remove(connection)) {
            throw new TransferableException("connection is not connected with this blocks");
        }

    }

    public void loadInput(Signal signal){
        for (Transferable output: outputs ) {
            output.loadInput(signal);
        }
    }

}
