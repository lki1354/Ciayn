package ciayn.elements;

/**
 * Created by lukas on 13.01.17.
 */


public class Signal {
    private float value;
    private Updater updater;


    public Signal(float value){
        this.value=value;
    }
    public Signal(){
        this.value = 0;
    }

    public void setUpdate(Updater updater){
        this.updater= updater;
    }


    public void update(){
        value = updater.getValue();
    }

    public float getValue() {
        return value;
    }
    public void addValue(float toAdd){
        this.value += toAdd;
    }
    public void subtractValue(float toAdd){
        this.value += toAdd;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
