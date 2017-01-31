package ciayn.elements.signal.unit;

/**
 * Created by lukas on 22.01.17.
 */
public abstract class Unit {
    protected String physicalQuantity = "Unit";
    protected String unit = "Unit";

    public Unit(String physicalQuantity, String unit) {
        this.physicalQuantity = physicalQuantity;
        this.unit = unit;
    }

    public String toString() {
        return this.unit;
    }

    public String getPhysicalQuantity() {
        return this.physicalQuantity;
    }

}
