package cz.matejkripner.core;

/**
 * Constants base class
 * @author Jakub Vaněk <vanek.jakub4@seznam.cz>
 * @version 1.1
 */
public abstract class Constants {
	/**
	 * Tile square length
	 */
	public int TILE_LENGTH;
	/**
	 * Robot length
	 */
	public int ROBOT_LENGTH;
	/**
	 * Space between robot and wall
	 */
	public int FROM_WALL_TO_CENTER_DISTANCE;
	/**
	 * Number of programs
	 */
	public int PROGRAMS;
	/**
	 * Prompt for program number
	 */
	public String PROGRAM_PROMPT;
	/**
	 * Delay between value changes
	 */
	public int PROGRAM_PROMPT_DELAY;
	/**
	 * SonicMotor move/SonicSensor move
	 */
	public int SONIC_MOVE;
	/**
	 * Diameter of wheels
	 */
	public float WHEELDIAMETER;
	/**
	 * Track width
	 */
	public float TRACKWIDTH;
	/**
	 * Left motor port
	 */
	public String LEFTMOTOR;
	/**
	 * Right motor port
	 */
	public String RIGHTMOTOR;
	/**
	 * Sonic motor port
	 */
	public String SONICMOTOR;
	/**
	 * Gyroscope port
	 */
	public String GYROPORT;
	/**
	 * Ultrasonic sensor port
	 */
	public String SONICPORT; // FIXME unknown
	/**
	 * Head touch sensor port
	 */
	public String HEADTOUCH_PORT;
	/**
	 * Back touch sensor port
	 */
	public String BACKTOUCH_PORT;
	/**
	 * Acceleration in degrees/second^2
	 */
	public int ACCEL;
	/**
	 * Travel speed
	 */
	public float TRAVELSPEED;
	/**
	 * Turning speed
	 */
	public float ROTATESPEED;
}
