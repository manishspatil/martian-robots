package com.manishpatil.martian.robots.commands;

import com.manishpatil.martian.robots.Robot;

import java.util.List;
import java.util.stream.Collectors;

public class RobotInstructionsCommand extends RobotCommand {
    private final String INSTRUCTION_PATTERN = "[FLRflr]+";

    public RobotInstructionsCommand(Robot robot, String instructions) {
        super(robot, instructions);
    }

    @Override
    public void execute() {
        if (robot == null || !robot.isOnGrid())
            return;

        if (instruction == null || instruction.isEmpty())
            return;

        if (!instruction.matches(INSTRUCTION_PATTERN)) {
            throw new RuntimeException("Invalid instruction provided. Correct instruction are “L”, “R”, and “F” to " +
                    "move Left, Right and Forward Ex: FRRFLLFFRRFLL");
        }

        List<RobotInstruction> robotInstructions = instruction.chars().mapToObj(i -> (char) i).map(c -> InstructionFactory.create(c, robot)).collect(Collectors.toList());
        robotInstructions.forEach(RobotInstruction::execute);
    }

    private abstract static class RobotInstruction {
        protected final Robot robot;

        protected RobotInstruction(Robot robot) {
            this.robot = robot;
        }

        public abstract void execute();
    }


    private static class LeftInstruction extends RobotInstruction {

        protected LeftInstruction(Robot robot) {
            super(robot);
        }

        @Override
        public void execute() {
            if (robot != null && robot.isOnGrid()) {
                robot.turnLeft();
            }
        }
    }

    private static class RightInstruction extends RobotInstruction {

        protected RightInstruction(Robot robot) {
            super(robot);
        }

        @Override
        public void execute() {
            if (robot != null && robot.isOnGrid()) {
                robot.turnRight();
            }
        }
    }

    private static class ForwardInstruction extends RobotInstruction {

        protected ForwardInstruction(Robot robot) {
            super(robot);
        }

        @Override
        public void execute() {
            if (robot != null && robot.isOnGrid()) {
                robot.moveForward();
            }
        }
    }

    private static class NullInstruction extends RobotInstruction {

        protected NullInstruction(Robot robot) {
            super(robot);
        }

        @Override
        public void execute() {
        }
    }

    /**
     * A factory for creating <code>RobotCommand</code> objects.
     */
    public static class InstructionFactory {

        private InstructionFactory() {
        }

        // Would prefer to create a Singleton polymorphic factory enabling adding/registering more Commands from command itself.
        public static RobotInstruction create(Character command, Robot robot) {
            if (command == null) {
                return new NullInstruction(robot);
            }

            switch (command) {
                case 'L':
                case 'l':
                    return new LeftInstruction(robot);
                case 'R':
                case 'r':
                    return new RightInstruction(robot);
                case 'F':
                case 'f':
                    return new ForwardInstruction(robot);
                default:
                    throw new RuntimeException(String.format("Unable to interpret specified command %c", command));
            }
        }
    }
}
