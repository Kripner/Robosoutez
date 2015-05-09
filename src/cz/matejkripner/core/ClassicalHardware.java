package cz.matejkripner.core;

import lejos.hardware.BrickFinder;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.utility.PilotProps;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Router for robot's hardware
 * @author Matěj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class ClassicalHardware implements Hardware {
    private static final ClassicalHardware instance = new ClassicalHardware();
	private Direction sonicDirection = Direction.LEFT; // TODO ensure that sonic is really in that direction
    private DifferentialPilot hardware;
    private EV3GyroSensor gyro;
	private EV3UltrasonicSensor sonic;
    static RegulatedMotor leftMotor;
    static RegulatedMotor rightMotor;
	private static final Constants CONSTANTS = new ClassicalConstants();


    private ClassicalHardware() {
        float wheelDiameter = CONSTANTS.WHEELDIAMETER;
        float trackWidth = CONSTANTS.TRACKWIDTH;
        leftMotor = PilotProps.getMotor(CONSTANTS.LEFTMOTOR);
        rightMotor = PilotProps.getMotor(CONSTANTS.RIGHTMOTOR);
        hardware = new DifferentialPilot(wheelDiameter, trackWidth, leftMotor, rightMotor, true);
        hardware.setAcceleration(CONSTANTS.ACCEL);
        hardware.setTravelSpeed(CONSTANTS.TRAVELSPEED);
        hardware.setRotateSpeed(CONSTANTS.ROTATESPEED);
	    gyro = new EV3GyroSensor(BrickFinder.getLocal().getPort(CONSTANTS.GYROPORT)); // TODO WARNING - CALIBRATION
	    sonic = new EV3UltrasonicSensor(BrickFinder.getLocal().getPort(CONSTANTS.SONICPORT));
    }
    public static ClassicalHardware getInstance() {
        return instance;
    }

    // TODO: implement methods using standard EV3 libraries
	// TODO: how? i think it's already implemented, only different way i see is to extend DifferentialPilot - Jakub Vaněk

	/**
	 * Travel specified distance, synchronous
	 * @param distance Distance in centimeters
	 */
    @Override
    public void travel(int distance) {
        hardware.travel(distance);
        waitFor();
    }

	/**
	 * Turn 90° to left
	 */
    @Override
    public void turnLeft() {
        turn(-90);
    }

	/**
	 * Turn 90° to right
	 */
    @Override
    public void turnRight() {
        turn(90);
    }

	/**
	 * Turn {@link Math#abs(int angle)} degrees to left
	 * @param angle Angle to left
	 */
    @Override
    public void turnLeft(int angle) {
        turn(-Math.abs(angle));
    }

	/**
	 * Turn {@link Math#abs(int angle)} degrees to right
	 * @param angle Angle to left
	 */
    @Override
    public void turnRight(int angle) {
        turn(Math.abs(angle));
    }

	/**
	 * Turn specified angle
	 * @param angle Angle to rotate by
	 */
    @Override
    public void turn(int angle) {
        hardware.rotate(angle);
        waitFor();
    }

	/**
	 * Check if hardware is moving.
	 * @return Boolean
	 */
    @Override
    public boolean isRunning() {
        return hardware.isMoving();
    }

	/**
	 * Wait for finish of move.
	 */
    private void waitFor() {
        while (hardware.isMoving()) Thread.yield();
    }

	/**
	 * Return value from ultrasonic sensor.
	 * @return
	 */
    @Override
    public int sonic() {
        return 0;
    }

	/**
	 * Check if front button is pressed or not.
	 * @return true if pressed
	 */
    @Override
    public boolean headTouch() {
        return false;
    }

	/**
	 * Check if rear button is pressed or not.
	 * @return true if pressed
	 */
    @Override
    public boolean backTouch() {
        return false;
    }

	/**
	 * Return gyro value (from Angle Mode)
	 * @return Degrees from last reset
	 */
    @Override
    public int gyro() {
        float[] data = new float[gyro.sampleSize()];
        gyro.getAngleMode().fetchSample(data, 0);
        return Math.round(data[0]);

    }

	/**
	 * Reset gyroscope
	 */
    @Override
    public void resetGyro() {
        gyro.reset();
    }

	/**
	 * Return robot's constants
	 * @return Robot's constants
	 */
    public Constants getConstants(){
        return CONSTANTS;
    }

	/**
	 * Turn sonic to specified direction
	 * @param direction
	 */
	@Override
	public void turnSonic(Direction direction) {
		switch (sonicDirection.compare(direction)) {
			case -2:
				throw new NotImplementedException();
			case -1:
				throw new NotImplementedException();
			case 0:
				throw new NotImplementedException();
			case 1:
				throw new NotImplementedException();
			case 2:
				throw new NotImplementedException();
		}
		sonicDirection = direction;
	}

	/**
	 * Get actual sonic direction
	 * @return Sonic direction
	 */
	@Override
	public Direction getSonicDirection() {
		return sonicDirection;
	}
}
