package ciayn.elements;

/**
 * Created by lukas on 13.01.17.
 */
public interface Operable {
    void run();
    void pause();
    void pause(int waitTime);
    void stop();
    void setInterval(int deltaTime);
}
