package cz.matejkripner;

import cz.matejkripner.ai.AI;
import cz.matejkripner.core.ClassicalHardware;
import cz.matejkripner.core.Hardware;

public class Main {

    public static final int BOARD_HEIGHT = 6;
    public static final int BOARD_WIDTH = 9;
    public static Hardware currentHardware;

    public static void main(String[] args) {
        currentHardware = ClassicalHardware.getInstance();
        AI ai = new AI ();
        ai.run();
    }
}
