package ciayn.elements.signal.unit;

/**
 * Created by lukas on 22.01.17.
 */
public class Angle extends Unit {
    private static Angle instance = null;

    private Angle(){
        super("Angle","°");
    }
    public static Unit getUnit(){
        if(instance == null) {
            instance = new Angle();
        }
        return instance;
    }
}
