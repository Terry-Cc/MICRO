package com.micro.basecase.javamodel.creationtype.builder;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  电脑组装器
 * </p>
 * @since 2023/6/30 19:29
 */
public class ComputerBuilder {

    private Computer computer;

    public ComputerBuilder init () {
        this.computer = new Computer();
        return this;
    }

    public ComputerBuilder addMotherBoard(String motherBoard) {
        computer.setMotherboard(motherBoard);
        return this;
    }
    public ComputerBuilder addCpu(String cpu) {
        computer.setCpu(cpu);
        return this;
    }
    public ComputerBuilder addMemory(String memory) {
        computer.setMemory(memory);
        return this;
    }
    public ComputerBuilder addDisk(String disk) {
        computer.setDisk(disk);
        return this;
    }
    public ComputerBuilder addGpu(String gpu) {
        computer.setGpu(gpu);
        return this;
    }
    public ComputerBuilder addPower(String power) {
        computer.setPower(power);
        return this;
    }
    public ComputerBuilder addHeatSink(String heatSink) {
        computer.setHeatSink(heatSink);
        return this;
    }
    public ComputerBuilder addChassis(String chassis) {
        computer.setChassis(chassis);
        return this;
    }
    public Computer build(){
        return computer;
    }
}
