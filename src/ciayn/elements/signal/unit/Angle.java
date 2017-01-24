package ciayn.elements.signal.unit;

/**
 * Created by lukas on 22.01.17.
 */
public class Angle extends Unit {
    private static Angle angle = new Angle();

    private Angle(){
        super("Angle","°");
    }
    public Unit getUnit(){
        return this.angle;
    }
}
