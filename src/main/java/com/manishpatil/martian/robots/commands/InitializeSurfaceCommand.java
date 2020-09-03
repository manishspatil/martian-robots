package com.manishpatil.martian.robots.commands;

import com.manishpatil.martian.Surface;

public class InitializeSurfaceCommand implements Command {
    private Surface surface;
    private String instruction;

    private final String INSTRUCTION_PATTERN = "[0-9]{1,2} [0-9]{1,2}";

    public InitializeSurfaceCommand(Surface surface, String instruction) {
        this.surface = surface;
        this.instruction = instruction;
    }

    public void execute() {
        if (instruction == null || instruction.isEmpty())
            return;

        if (!instruction.matches(INSTRUCTION_PATTERN)) {
            throw new RuntimeException("Invalid instruction provided for Initializing Surface. Correct instruction is" +
                    " the upper-right coordinates of the Surface.");
        }

        String[] coordinates = instruction.split(" ");

        int upperRightXCoordinate = Integer.parseInt(coordinates[0]);
        int upperRightYCoordinate = Integer.parseInt(coordinates[1]);

        surface.init(upperRightXCoordinate, upperRightYCoordinate);
    }
}
