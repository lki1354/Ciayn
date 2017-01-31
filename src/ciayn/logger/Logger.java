package ciayn.logger;

import ciayn.elements.signal.Value;

/**
 * Created by lukas on 31.01.17.
 */



public abstract class Logger {
    protected String loggerName;

    public Logger(String loggerName){
        this.loggerName = loggerName;
    }

    public abstract void log(String input);
    public abstract void log(Value value);
    public abstract void log(String info,Value value);
    public abstract void log(Number value);
    public abstract void log(String info,Number value);

}
