package ciayn.elements;


/**
 * Created by lukas on 13.01.17.
 */

public interface Transferable {

    void addOutputConnection(Transferable connection);

    void loadInput(Signal signal);

}
