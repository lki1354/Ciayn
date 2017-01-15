package ciayn.elements;

/**
 * Created by lukas on 13.01.17.
 */


public class Signal<T> {
    private T value;
    private int i = 0;
    private Updater<T> updater;

    public Signal(T value){
        this.value=value;
    }
    public void setUpdate(Updater updater){
        this.updater= updater;
    }


    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
    public void update(){
        value = updater.getValue();
    }
}
