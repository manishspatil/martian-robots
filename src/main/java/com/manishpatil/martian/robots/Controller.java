package com.manishpatil.martian.robots;

import com.manishpatil.martian.Surface;
import com.manishpatil.martian.robots.commands.Command;
import com.manishpatil.martian.robots.commands.InitializeRobotCommand;
import com.manishpatil.martian.robots.commands.InitializeSurfaceCommand;
import com.manishpatil.martian.robots.commands.RobotInstructionsCommand;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Controller {

    private Surface surface;

    private Scanner commandScanner;

    public Controller(Surface surface, String filePath) {
        this.surface = surface;

        try {
            if (filePath != null && !filePath.isEmpty()) {
                commandScanner = new Scanner(new File(filePath));
            }
        } catch (FileNotFoundException exp) {
            System.err.println(String.format("Unable to find the commandFile %s. Error: %s", filePath, exp.getMessage()));
        }
    }

    public void executeCommands() {
        executeInitializeSurfaceCommand();

        while (commandScanner.hasNextLine()) {
            executeRobotCommands();
        }
    }

    private void executeInitializeSurfaceCommand() {
        if (commandScanner.hasNextLine()) {
            String strCommand = commandScanner.nextLine();
            Command surfaceInitCommand = new InitializeSurfaceCommand(this.surface, strCommand);
            surfaceInitCommand.execute();
        }
    }

    // Need more decoupling of various concerns. like ordering of commands initialization of Robot object(s) and
    // instruction(s).
    private void executeRobotCommands() {
        if (commandScanner.hasNextLine()) {
            String strCommand;

            //noinspection StatementWithEmptyBody
            while ((strCommand = commandScanner.nextLine().trim()).isEmpty()) {
            }

            Robot robot = new Robot();
            Command robotInitCommand = new InitializeRobotCommand(surface, robot, strCommand);
            robotInitCommand.execute();

            strCommand = commandScanner.nextLine();

            if (strCommand.length() > 100) {
                System.err.println("Instructions must be less than 100 characters in length.");
                return;
            }

            Command robotInstructionsCommand = new RobotInstructionsCommand(robot, strCommand);
            robotInstructionsCommand.execute();
        }
    }
}
