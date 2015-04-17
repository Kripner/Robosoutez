package cz.matejkripner.core;

import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.navigation.DifferentialPilot;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class ClassicalHardware implements Hardware {

    private static final ClassicalHardware instance = new ClassicalHardware();
    private DifferentialPilot pilot = new DifferentialPilot(2.1f, 4f, Motor.A, Motor.C, true);
    private EV3GyroSensor gyro = new EV3GyroSensor(Port 3);


    private ClassicalHardware() {

    }

    public static ClassicalHardware getInstance() {
        return instance;
    }

    // TODO: implement methods using standard EV3 libraries


    @Override
    public void travel(int distance) {
        DifferentialPilot pilot = new DifferentialPilot(2.71f, 4.4f, Motor.A, Motor.C, true);
    }

    @Override
    public void turnLeft() {
        pilot.rotate(-90);
    }

    @Override
    public void turnRight() {
        pilot.rotate(90);
    }

    @Override
    public void turnLeft(int angle) {
        pilot.rotate(angle);
    }

    @Override
    public void turnRight(int angle) {
        pilot.rotate(-angle);
    }

    @Override
    public void turn(int angle) {
        pilot.rotate(angle);

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public int sonar() {
        return gyro.getAngleAndRateMode();
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

    }

    @Override
    public void turnSonar(Direction direction) {

    }

    @Override
    public Direction getSonarDirection() {
        return null;
    }
}
