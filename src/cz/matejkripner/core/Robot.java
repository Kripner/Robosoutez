package cz.matejkripner.core;

import cz.matejkripner.Main;
import cz.matejkripner.Utils;

import java.util.function.Predicate;

import static cz.matejkripner.core.Movement.TURN_LEFT;

/**
 * @author Mat�j Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class Robot {

    private int expectedDirection;

    private static Robot instance = new Robot();

    private Robot() {
        expectedDirection = 0;
    }

    public static Robot getInstance() {
        return instance;
    }


    public boolean perform(Movement movement) {
        Measurement.allOutdated();
        switch(movement) {
            case TURN_LEFT:
                turnLeft();
                break;
            case TURN_RIGHT:
                turnRight();
                break;
            default:
                movement.perform(Main.currentHardware);
                break;
        }
        turnToExpectedDirection();
        return !(Boolean) test(Measurement.HEAD_TOUCH);
    }

    private void turnRight() {
        expectedDirection -= 90; // TODO: check if really minus and really 90
        Main.currentHardware.turnRight();
    }

    private void turnLeft() {
        expectedDirection += 90; // TODO: check if really plu s and really 90
        Main.currentHardware.turnLeft();
    }

    private void turnToExpectedDirection() {
        for (int i = 0; i < 3 && Main.currentHardware.gyro() != expectedDirection; i++) { // debug
            Main.currentHardware.turn(Main.currentHardware.gyro() - expectedDirection);
        }
    }

    public Object test(Measurement measurement) {
        if (!measurement.isActual()) measurement.doMeasurement(Main.currentHardware);
        return measurement.get();
    }

    public void waitFor() {
        while (Main.currentHardware.isRunning()) try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            waitFor();
        }
    }

    public void doWhile(Movement movement, Predicate<Robot> whileSatisfied) {
        while (whileSatisfied.test(this)) {
            perform(movement);
        }
    }
}
