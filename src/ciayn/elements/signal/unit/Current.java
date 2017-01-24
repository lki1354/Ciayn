package ciayn.elements.signal.unit;

/**
 * Created by lukas on 22.01.17.
 */
public class Current extends Unit {
    private static Current instance = null;

    private Current(){
        super("Current","A");
    }
    public static Unit getUnit(){
        if(instance == null) {
            instance = new Current();
        }
        return instance;
    }
}
