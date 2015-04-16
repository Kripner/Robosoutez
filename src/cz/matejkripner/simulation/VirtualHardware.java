package cz.matejkripner.simulation;

import cz.matejkripner.core.Hardware;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class VirtualHardware implements Hardware {
    @Override
    public void travel(int distance) {

    }

    @Override
    public void turnLeft() {
        turnLeft(90);
    }

    @Override
    public void turnRight() {
        turnRight(90);
    }

    @Override
    public void turnLeft(int angle) {
        turn(angle);
    }

    @Override
    public void turnRight(int angle) {
        turn(-angle);
    }

    @Override
    public void turn(int angle) {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public int sonar() {
        return 0;
    }

    @Override
    public boolean headTouch() {
        return false;
    }

    @Override
    public boolean backTouch() {
        return false;
    }

    @Override
    public int gyro() {
        return 0;
    }

    @Override
    public void turnSonarLeft() {

    }

    @Override
    public void turnSonarAhead() {

    }

    @Override
    public void turnSonarBack() {

    }

    @Override
    public Direction getSonarDirection() {
        return null;
    }
}
