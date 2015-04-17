package cz.matejkripner;

import cz.matejkripner.ai.AI;
import cz.matejkripner.ai.Program;
import cz.matejkripner.core.ClassicalHardware;
import cz.matejkripner.core.Hardware;

public class Main {

    public static final int BOARD_HEIGHT = 6; // tills
    public static final int BOARD_WIDTH = 9;
    public static final int ONE_TILL_DISTANCE = 280; // in millimeters
    public static final int ROBOT_LENGTH = 212;
    public static final int FROM_WALL_TO_CENTER_DISTANCE = (ONE_TILL_DISTANCE - ROBOT_LENGTH) / 2;

    public static Hardware currentHardware;

    public static void main(String[] args) {
        currentHardware = ClassicalHardware.getInstance();

        AI ai = new AI("FIRST");
        ai.run();
    }
}
