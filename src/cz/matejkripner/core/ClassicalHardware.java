package cz.matejkripner.core;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.utility.PilotProps;

import java.io.IOException;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class ClassicalHardware implements Hardware {

    private static final ClassicalHardware instance = new ClassicalHardware();
    private DifferentialPilot robot;
    Port port = LocalEV3.get().getPort("S3");
    private EV3GyroSensor gyro = new EV3GyroSensor(port);

    static RegulatedMotor leftMotor;
    static RegulatedMotor rightMotor;



    private ClassicalHardware() {
        PilotProps pp = new PilotProps();
        try {
            pp.loadPersistentValues();
        } catch (IOException e) {
            // do nothing
        }
        float wheelDiameter = Float.parseFloat(pp.getProperty(PilotProps.KEY_WHEELDIAMETER, "70"));
        float trackWidth = Float.parseFloat(pp.getProperty(PilotProps.KEY_TRACKWIDTH, "90"));
        leftMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_LEFTMOTOR, "B"));
        rightMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_RIGHTMOTOR, "C"));
        boolean reverse = Boolean.parseBoolean(pp.getProperty(PilotProps.KEY_REVERSE, "true"));

        robot = new DifferentialPilot(wheelDiameter,trackWidth,leftMotor,rightMotor,reverse);
        robot.setAcceleration(4000);
        robot.setTravelSpeed(180); // cm/sec
        robot.setRotateSpeed(90); // deg/sec

    }

    public static ClassicalHardware getInstance() {
        return instance;
    }

    // TODO: implement methods using standard EV3 libraries


    @Override
    public void travel(int distance) {
        robot.travel(distance);
        waitFor();
    }

    @Override
    public void turnLeft() {
        turn(-90);
    }

    @Override
    public void turnRight() {
        turn(90);
    }

    @Override
    public void turnLeft(int angle) {
        turn(angle);
    }

    @Override
    public void turnRight(int angle) {
        turn(angle);
    }

    @Override
    public void turn(int angle) {
        robot.rotate(angle);
        waitFor();

    }

    @Override
    public boolean isRunning() {
        return robot.isMoving();
    }

    private void waitFor() {
        while(robot.isMoving())Thread.yield();
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
