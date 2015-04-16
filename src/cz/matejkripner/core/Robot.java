package cz.matejkripner.core;

import cz.matejkripner.Main;
import cz.matejkripner.Utils;

import java.util.function.Predicate;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class Robot {

    private int expectedDirection;

    public boolean perform(Movement movement) {
        Measurement.allOutdated();
        movement.perform(Main.currentHardware);
        return !(Boolean) test(Measurement.HEAD_TOUCH);
    }

    private void turnRight() {
        expectedDirection -= 90; // TODO: check if really minus and really 90
        Main.currentHardware.turnRight();
        turnToExpectedDirection();
    }

    private void turnLeft() {
        expectedDirection += 90; // TODO: check if really plus and really 90
        Main.currentHardware.turnLeft();
        turnToExpectedDirection();
    }

    private void turnToExpectedDirection() {
        Utils.whileLoop(v -> Main.currentHardware.turn(Main.currentHardware.gyro() - expectedDirection), // TODO: debug
                v -> Main.currentHardware.gyro() != expectedDirection, 3);
    }

    public Object test(Measurement measurement) {
        if (!measurement.isActual()) measurement.doMeasurement(Main.currentHardware);
        return measurement.get();
    }

    public void waitFor() throws InterruptedException {
        while(Main.currentHardware.isRunning()) Thread.sleep(1);
    }

    public void doWhile(Movement movement, Predicate<Robot> whileSatisfied) {
        while (whileSatisfied.test(this)) {
            perform(movement);
        }
    }
}
