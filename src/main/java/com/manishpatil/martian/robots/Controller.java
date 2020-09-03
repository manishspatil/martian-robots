package com.manishpatil.martian.robots;

import com.manishpatil.martian.Surface;
import com.manishpatil.martian.robots.commands.Command;
import com.manishpatil.martian.robots.commands.InitializeSurfaceCommand;

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
    }

    private void executeInitializeSurfaceCommand() {
        if (commandScanner.hasNextLine()) {
            String strCommand = commandScanner.nextLine();
            Command surfaceInitCommand = new InitializeSurfaceCommand(this.surface, strCommand);
            surfaceInitCommand.execute();
        }
    }
}
