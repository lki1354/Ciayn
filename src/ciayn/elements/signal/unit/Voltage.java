package ciayn.elements.signal.unit;

/**
 * Created by lukas on 22.01.17.
 */
public class Voltage extends Unit {
    private static Voltage voltage = new Voltage();

    private Voltage(){
        super("Voltage","V");
    }
    @Override
    public Unit getUnit(){
        return this.voltage;
    }
}
