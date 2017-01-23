package ciayn.elements.signal.unit;

/**
 * Created by lukas on 22.01.17.
 */
public class Current extends Unit {
    private static Current current = new Current();

    private Current(){
        super("Current","A");
    }
    @Override
    public Unit getUnit(){
        return this.current;
    }
}
