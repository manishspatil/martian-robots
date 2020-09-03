package com.manishpatil.martian;

import com.manishpatil.martian.robots.Controller;
import com.manishpatil.martian.robots.Robot;

import java.util.List;

public class RobotController {

    public static void main(String[] args) {
        System.err.println("In Main");
        Surface surface = Surface.getInstance();
        System.err.println("In Main Got surface");
        Controller controller;

        // Ideally could have implemented to accept inputs from console in case absence of file.
        // to allow interactive interaction
        if (args.length > 0) {
            controller = new Controller(surface, args[0]);
            controller.executeCommands();

            showReport(surface);
        } else {
            System.err.println("Usage: RobotController commandFile");
        }
    }

    private static void showReport(Surface surface) {
        List<Robot> robots = surface.getPlacedRobots();
        robots.forEach(r -> System.out.println(r.getPosition()));
    }
}
