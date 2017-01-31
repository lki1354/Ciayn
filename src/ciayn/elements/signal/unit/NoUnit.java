package ciayn.elements.signal.unit;

/**
 * Created by lukas on 22.01.17.
 */
public class NoUnit extends Unit {
    private static NoUnit instance = null;

    private NoUnit() {
        super("no Unit", "");
    }

    public static Unit getUnit() {
        if (instance == null) {
            instance = new NoUnit();
        }
        return instance;
    }
}
