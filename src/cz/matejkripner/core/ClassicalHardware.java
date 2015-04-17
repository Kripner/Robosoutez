package cz.matejkripner.core;

import lejos.hardware.motor.Motor;
import lejos.robotics.navigation.DifferentialPilot;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class ClassicalHardware implements Hardware {

    private static final ClassicalHardware instance = new ClassicalHardware();
    private DifferentialPilot pilot = new DifferentialPilot(2.1f, 4f, Motor.A, Motor.C, true);

    private ClassicalHardware() {

    }

    public static ClassicalHardware getInstance() {
        return instance;
    }

    // TODO: implement methods using standard EV3 libraries


    @Override
    public void travel(int distance) {
        DifferentialPilot pilot = new DifferentialPilot(2.1f, 4.4f, Motor.A, Motor.C, true);
    }

    @Override
    public void turnLeft() {

    }

    @Override
    public void turnRight() {

    }

    @Override
    public void turnLeft(int angle) {

    }

    @Override
    public void turnRight(int angle) {

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
    public void turnSonar(Direction direction) {

    }

    @Override
    public Direction getSonarDirection() {
        return null;
    }
}
