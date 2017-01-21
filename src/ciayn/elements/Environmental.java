package ciayn.elements;

import ciayn.basicTerms.PIDController;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Environmental<T> extends Block implements Operable{
    private long interval = 1000;
    private Timer timekeeper = new Timer();
    private Signal inputSignal;
    private List<Block> blocks = new ArrayList<>();
    private List<Wire>  connections = new ArrayList<>();
    private Transferable input = null;

    public void setInput(Transferable input){
        this.input = input;
    }
    public void setOutput(Transferable output){
        this.output = output;
    }

    @Override
    public void run() {
        TimerTask runProcess = new TimerTask() {
            @Override
            public void run() {
                process();
            }
        };
        timekeeper.scheduleAtFixedRate(runProcess,0,interval);
    }
    private void process(){
        inputSignal.update();
        doOneIteration();
    }
    private void doOneIteration(){
        System.out.println("... do one iteration with input signal: "+inputSignal.getValue());
        input.loadInput(inputSignal);
    }

    @Override
    public void pause() {
        timekeeper.cancel();
    }

    @Override
    public void pause(int waitTime) {

    }

    @Override
    public void stop() {
        timekeeper.cancel();
    }

    @Override
    public void setInterval(int deltaTime) {
        interval = deltaTime;
    }

    @Override
    public void loadInput(Signal signal) {
        this.inputSignal = signal;
        this.doOneIteration();
    }

    public void addBlock(Block block) throws EnvironmentalException {
        if(this.blocks.contains(block)){
            throw new EnvironmentalException("Environmental contains this block!");
        }else {
            this.blocks.add(block);
        }
    }
    public void addWire(Wire wire) throws EnvironmentalException {
        if(this.connections.contains(wire)){
            throw new EnvironmentalException("Environmental contains this wire!");
        }else {
            this.connections.add(wire);
        }
    }
    public void connect(Wire fromWire, Block toBlock){
        if(!this.connections.contains(fromWire)){
            this.connections.add(fromWire);
        }
        if(!this.blocks.contains(toBlock)){
            this.blocks.add(toBlock);
        }
        fromWire.addOutputConnection(toBlock);
    }
    public void connect(Block fromBlock, Wire toWire) throws TransferableException {
        if(!this.blocks.contains(fromBlock)){
            this.blocks.add(fromBlock);
        }
        if(!this.connections.contains(toWire)){
            this.connections.add(toWire);
        }
        fromBlock.addOutputConnection(toWire);
    }
    public void connect(Block fromBlock, Block toBlock) throws TransferableException {
        if(!this.blocks.contains(fromBlock)){
            this.blocks.add(fromBlock);
        }
        if(!this.blocks.contains(toBlock)){
            this.blocks.add(toBlock);
        }
        fromBlock.addOutputConnection(toBlock);

    }
    public void connect(Wire fromWire, Wire toWire){
        if(!this.connections.contains(fromWire)){
            this.connections.add(fromWire);
        }
        if(!this.connections.contains(toWire)){
            this.connections.add(toWire);
        }
        fromWire.addOutputConnection(toWire);

    }
    public Block createPIDController(float kp, float ki, float kd, float dt, Signal target, Signal actual, Signal output) throws EnvironmentalException, TransferableException {
        if (this.blocks.size() >=1 ){
            throw new EnvironmentalException("only one PIDControlxler is allowed in a environmental!");
        }
        Block pid = new PIDController(kp,ki ,kd, dt);
        Wire pidOut = new Wire();
        this.blocks.add(0,pid);
        this.addInputConnection(pid);
        this.addOutputConnection(pid);
        this.setInterval((int)dt*1000);

        return this.blocks.get(0);
    }
}
