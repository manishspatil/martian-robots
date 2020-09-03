package com.manishpatil.martian.robots.commands;

import com.manishpatil.martian.Coordinates;
import com.manishpatil.martian.Direction;
import com.manishpatil.martian.Surface;
import com.manishpatil.martian.robots.Robot;

public class InitializeRobotCommand extends RobotCommand {
    private final String INSTRUCTION_PATTERN = "[0-9]{1,2} [0-9]{1,2} [NESWnesw]";

    private Surface surface;

    public InitializeRobotCommand(Surface surface, Robot robot, String instruction) {
        super(robot, instruction);
        this.surface = surface;
    }

    public void execute() {
        if (instruction == null || instruction.isEmpty())
            return;

        if (!instruction.matches(INSTRUCTION_PATTERN)) {
            throw new RuntimeException(String.format("Invalid instruction %s provided for a New Robot. Correct " +
                            "instruction are the initial Coordinates of the robot and an Orientation (N, S, E, W). Ex: 3 5 N",
                    instruction));
        }

        String[] coordinates = instruction.split(" ");
        int xCoordinate = Integer.parseInt(coordinates[0]);
        int yCoordinate = Integer.parseInt(coordinates[1]);

        Direction direction = Direction.getDirection(coordinates[2]);

        robot.init(surface, new Coordinates(xCoordinate, yCoordinate), direction);
    }
}
