package ciayn.logger;

import ciayn.elements.signal.Value;

/**
 * Created by lukas on 31.01.17.
 */
public class ConsoleLogger extends Logger {
    public ConsoleLogger(String loggerName) {
        super(loggerName);
    }

    @Override
    public void log(String input) {
        System.out.println(this.loggerName+": "+input);
    }

    @Override
    public void log(Value value) {
        System.out.println(this.loggerName+": "+value.toString()+": value="+value.getValue().toString()+"; time stamp="+value.getTimeStamp());
    }

    @Override
    public void log(String info, Value value) {
        System.out.println(this.loggerName+": "+value.toString()+":"+info +"value="+value.getValue().toString()+"; time stamp="+value.getTimeStamp());

    }

    @Override
    public void log(Number value) {
        System.out.println(this.loggerName+": value="+value);

    }

    @Override
    public void log(String info, Number value) {
        System.out.println(this.loggerName+": "+info +" : value="+value);

    }

}
