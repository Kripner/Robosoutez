package cz.matejkripner.core;

import cz.matejkripner.util.Utils;
import lejos.hardware.BrickFinder;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;

/**
 * Router for robot's hardware
 * @author Matěj Kripner <kripnermatej@gmail.com>
 * @author Jakub Vaněk <vanek.jakub4@seznam.cz>
 * @version 1.1
 */
public class ClassicalHardware implements Hardware {
	/**
	 * Static instance
	 */
    private static final ClassicalHardware instance = new ClassicalHardware();
	/**
	 * Actual sonic direction
	 */
	private Direction sonicDirection = Direction.LEFT; // TODO ensure that sonic is really in that direction
	/**
	 * Physical robot
	 */
    private DifferentialPilot physics;
	/**
	 * Gyroscope
	 */
    private EV3GyroSensor gyro;
	/**
	 * Ultrasonic sensor
	 */
	private EV3UltrasonicSensor sonic;
	/**
	 * Head touch sensor
	 */
    private EV3TouchSensor headTouch;
	/**
	 * Back touch sensor
	 */
	private EV3TouchSensor backTouch;
	/**
	 * Left motor
	 */
    private EV3LargeRegulatedMotor leftMotor;
	/**
	 * Right motor
	 */
	private EV3LargeRegulatedMotor rightMotor;
	/**
	 * Ultrasonic sensor motor
	 */
	private EV3MediumRegulatedMotor sonicMotor;
	/**
	 * Constants for this hardware
	 */
	private static final Constants CONSTANTS = new ClassicalConstants();

	/**
	 * Default constructor
	 */
    private ClassicalHardware() {
        float wheelDiameter = CONSTANTS.WHEELDIAMETER;
        float trackWidth = CONSTANTS.TRACKWIDTH;
        leftMotor = new EV3LargeRegulatedMotor(getPort(CONSTANTS.LEFTMOTOR));
	    rightMotor = new EV3LargeRegulatedMotor(getPort(CONSTANTS.RIGHTMOTOR));
	    sonicMotor = new EV3MediumRegulatedMotor(getPort(CONSTANTS.SONICMOTOR));

        physics = new DifferentialPilot(wheelDiameter, trackWidth, leftMotor, rightMotor, true);
        physics.setAcceleration(CONSTANTS.ACCEL);
        physics.setTravelSpeed(CONSTANTS.TRAVELSPEED);
        physics.setRotateSpeed(CONSTANTS.ROTATESPEED);

	    gyro = new EV3GyroSensor(BrickFinder.getLocal().getPort(CONSTANTS.GYROPORT)); // TODO WARNING - CALIBRATION
	    sonic = new EV3UltrasonicSensor(BrickFinder.getLocal().getPort(CONSTANTS.SONICPORT));
	    headTouch = new EV3TouchSensor(getPort(CONSTANTS.HEADTOUCH_PORT));
	    backTouch = new EV3TouchSensor(getPort(CONSTANTS.BACKTOUCH_PORT));
    }
    public static ClassicalHardware getInstance() {
        return instance;
    }

    // TODO: implement methods using standard EV3 libraries
	// TODO: how? i think it's already implemented - Jakub Vaněk

	/**
	 * Travel specified distance, synchronous
	 * @param distance Distance in centimeters
	 */
    @Override
    public void travel(int distance) {
        physics.travel(distance, false);
    }

	/**
	 * Back-crash to wall and travel back
	 */
	@Override
	public void backAlign(){
		physics.backward();
		while(!backTouch()) Thread.yield();
		physics.travel(CONSTANTS.FROM_WALL_TO_CENTER_DISTANCE, false);
	}

	/**
	 * Head-crash to wall and travel back
	 */
	@Override
	public void headAlign(){
		physics.forward();
		while(!headTouch()) Thread.yield();
		physics.travel(-CONSTANTS.FROM_WALL_TO_CENTER_DISTANCE, false);
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
        physics.rotate(angle, false);
    }

	/**
	 * Check if robot is moving.
	 * @return Boolean
	 */
    @Override
    public boolean isRunning() {
        return physics.isMoving();
    }

	/**
	 * Return value from ultrasonic sensor.
	 * @return Distance.
	 */
    @Override
    public float sonic() {
	    float distance = Utils.getFirstSample(sonic.getDistanceMode());
        return Math.round(distance);
    }

	/**
	 * Check whether value from ultrasonic sensor is buggy.
	 * @param dist Value obtained from {@link #sonic()}
	 * @return dist == {@link Float#POSITIVE_INFINITY}
	 */
	@Override
	public boolean sonicBug(float dist){
		return dist==Float.POSITIVE_INFINITY;
	}

	/**
	 * Check if front button is pressed or not.
	 * @return true if pressed
	 */
    @Override
    public boolean headTouch() {
        return Utils.getFirstSample(headTouch.getTouchMode()) == 1.0f;
    }

	/**
	 * Check if rear button is pressed or not.
	 * @return true if pressed
	 */
    @Override
    public boolean backTouch() {
	    return Utils.getFirstSample(backTouch.getTouchMode()) == 1.0f;
    }

	/**
	 * Return gyro value (from Angle Mode)
	 * @return Degrees from last reset
	 */
    @Override
    public int gyro() {
	    float value = Utils.getFirstSample(gyro.getAngleMode());
	    return Math.round(value);
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
	@Override
    public Constants getConstants(){
        return CONSTANTS;
    }

	/**
	 * Turn sonic to specified direction
	 * @param direction Ultrasonic sensor direction
	 */
	@Override
	public void turnSonic(Direction direction) {
		int rotate = sonicDirection.compare(direction);
		sonicMotor.rotate(rotate*CONSTANTS.SONIC_MOVE);
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

	/**
	 * Get port from port name
	 * @param name Port name
	 * @return Port on local EV3
	 */
	public static Port getPort(String name){
		return BrickFinder.getLocal().getPort(name);
	}
}
