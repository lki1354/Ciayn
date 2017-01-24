package ciayn;

import ciayn.controller.Controller;
import ciayn.controller.PID;
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
    public static EnvController createEnvPID(Class c, Number k, Number Ti,Number Td,  Number dt ,Input setpoit, Input feedback,Output output) throws Exception {
        EnvController env =  new EnvController(c, new PID(c,k,Ti,Td,dt), output );
        env.setSetpoint(setpoit);
        env.setFeedback(feedback);
        return env;
    }

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
