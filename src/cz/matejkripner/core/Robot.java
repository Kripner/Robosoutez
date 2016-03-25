package cz.matejkripner.core;

import cz.matejkripner.Main;


/**
 * Proxy for movement instructions
 * @author Matěj Kripner <kripnermatej@gmail.com>
 * @author Jakub Vaněk <vanek.jakub4@seznam.cz>
 * @version 1.1
 */
public class Robot {
	/**
	 * Expected gyroscope value
	 */
    private int expectedDirection;
	/**
	 * Static instance
	 */
    private static Robot instance = new Robot();

	/**
	 * Default constructor
	 */
    private Robot() {
        expectedDirection = 0;
    }

	/**
	 * Get static robot instance
	 * @return Instance of {@link Robot}
	 */
    public static Robot getInstance() {
        return instance;
    }

	/**
	 * Run specified amount of specified movement
	 * @param movement Movement to execute
	 * @param amount Amount of movement
	 * @return Head/back touch sensor status
	 */
    public boolean perform(Movement movement, int amount) {
        Measurement.allOutdated();
        switch(movement) {
            case TURN_LEFT:
                turnLeft();
                break;
            case TURN_RIGHT:
                turnRight();
                break;
            default:
                movement.perform(Main.currentHardware, amount);
                break;
        }
        turnToExpectedDirection();
	    if(movement==Movement.GO_BACK)
		    return !(Boolean) Measurement.BACK_TOUCH.get();
        return !(Boolean) Measurement.HEAD_TOUCH.get();
    }

	/**
	 * Turn 90° to right, write expected gyro change
	 */
    private void turnRight() {
        expectedDirection -= 90; // TODO: check if really minus
        Main.currentHardware.turnRight();
    }

	/**
	 * Turn 90° to left, write expected gyro change
	 */
    private void turnLeft() {
        expectedDirection += 90; // TODO: check if really plus
        Main.currentHardware.turnLeft();
    }

	/**
	 * Turn to correct direction
	 */
    private void turnToExpectedDirection() {
        for (int i = 0; i < 3 && Main.currentHardware.gyro() != expectedDirection; i++) { // debug
            Main.currentHardware.turn(Main.currentHardware.gyro() - expectedDirection);
        }
    }

	/**
	 * Wait for end of robot's work
	 */
    public void waitFor() {
        while (Main.currentHardware.isRunning()) try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            waitFor();
        }
    }
}
