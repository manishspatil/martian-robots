package com.manishpatil.martian.robots.commands;

import com.manishpatil.martian.robots.Robot;

public abstract class RobotCommand implements Command {
    protected final Robot robot;
    protected final String instruction;

    protected RobotCommand(Robot robot, String instruction) {
        this.robot = robot;
        this.instruction = instruction;
    }
}
