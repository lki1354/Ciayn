package ciayn.logger;

/**
 * Created by lukas on 31.01.17.
 */
public abstract class LoggableAbstract implements Loggable  {
    protected Logger logger = null;
    public void setLogger(Logger logger){
        this.logger = logger;
    }
}
