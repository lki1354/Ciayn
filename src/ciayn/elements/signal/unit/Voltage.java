package ciayn.elements.signal.unit;

/**
 * Created by lukas on 22.01.17.
 */
public class Voltage extends Unit {
    private static Voltage instance = null;

    private Voltage() {
        super("Voltage", "V");
    }

    public static Unit getUnit() {
        if (instance == null) {
            instance = new Voltage();
        }
        return instance;
    }
}
