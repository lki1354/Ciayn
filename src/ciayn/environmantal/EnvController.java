package ciayn.environmantal;

import ciayn.controller.Controller;
import ciayn.controller.*;
import ciayn.elements.Input;
import ciayn.elements.Output;
import ciayn.elements.signal.Value;


public class EnvController extends Env {
    // Signals 0 is setpoint
    // Signals 1 is feedback
    private Controller controller = null;
    private Value e = null;
    private Value u = null;

    private void initValues(Class c) throws IllegalAccessException, InstantiationException {
        this.e = (Value) c.newInstance();
        this.u = (Value) c.newInstance();
    }

    public EnvController(Class c,Controller controller,Output output) throws InstantiationException, IllegalAccessException {
        super(output);
        this.setController(controller);
        initValues(c);
    }
    public EnvController(Class c,Controller controller,Input setpoint, Input feedback,Output output) throws InstantiationException, IllegalAccessException {
        super(output);
        this.inputSignals.add(0,setpoint);
        this.inputSignals.add(1,feedback);
        this.setController(controller);
        initValues(c);
    }
    public static EnvController createEnvPID(Class c, Number k, Number Ti,Number Td,  Number dt ,Input setpoit, Input feedback,Output output) throws Exception {
        EnvController env =  new EnvController(c, new PID(c,k,Ti,Td,dt), output );
        env.setSetpoint(setpoit);
        env.setFeedback(feedback);
        return env;
    }
    public static EnvController createEnvPI(Class c, Number k, Number Ti,  Number dt ,Input setpoint, Input feedback,Output output) throws Exception {
        EnvController env =  new EnvController(c, new PI(c,k,Ti,dt), output );
        env.setSetpoint(setpoint);
        env.setFeedback(feedback);
        return env;
    }
    public static EnvController createEnvPD(Class c, Number k, Number Td,  Number dt ,Input setpoint, Input feedback,Output output) throws Exception {
        EnvController env =  new EnvController(c, new PD(c,k,Td,dt), output );
        env.setSetpoint(setpoint);
        env.setFeedback(feedback);
        return env;
    }
    public static EnvController createEnvController(Class c, Number k, Number Ti,Number Td,  Number dt ,Input setpoint, Input feedback,Output output) throws Exception {
        return createEnvPID(c,k,Ti,Td,dt,setpoint,feedback,output);
    }
    public static EnvController createEnvController(Class c, Number k, Number Ti,  Number dt ,Input setpoint, Input feedback,Output output) throws Exception {
        return createEnvPI(c,k,Ti,dt,setpoint,feedback,output);
    }
   // public static EnvController createEnvController(Class c, Number k, Number Td,  Number dt ,Input setpoint, Input feedback,Output output) throws Exception {
   //     return createEnvPD(c,k,Td,dt,setpoint,feedback,output);
   // }

    public void setController(Controller controller){
       this.controller = controller;
    }
    public Controller getController() {
        return controller;
    }

    @Override
    protected void doOneIteration(){
        this.e.setValue(this.inputSignals.get(0).getValue());
        this.e.subtractValue(this.inputSignals.get(1).getValue());
        this.e.setTimeStamp(this.inputSignals.get(1).getValue().getTimeStamp());
        this.u.setValue(this.controller.runAlgorithm(this.e) );
        this.outputSignal.setValue(this.u);
    }

    public Value getFeedback() {
        return this.inputSignals.get(1).getValue();
    }

    public void setFeedback(Input feedback) throws Exception {
        if (feedback == null){
            throw new Exception("Feedback must not null!");
        }
        this.inputSignals.add(1,feedback);
    }

    public Value getSetpoint() {
        return this.inputSignals.get(0).getValue();
    }

    public void setSetpoint(Input setpoint) {
        this.inputSignals.add(0,setpoint);
    }
}
