package cz.matejkripner.core;

import lejos.hardware.ev3.LocalEV3;
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
    Port port = LocalEV3.get().getPort("S3");
    private EV3GyroSensor gyro = new EV3GyroSensor(port);


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
        float [] data = new float[gyro.sampleSize()];
        gyro.getAngleMode().fetchSample(data,1);
        return Math.round(data [0]);

    }

//    @Override
//    public void turnSonar(Direction direction) {
//
//    }
//
//    @Override
//    public Direction getSonarDirection() {
//        return null;
//    }
}
