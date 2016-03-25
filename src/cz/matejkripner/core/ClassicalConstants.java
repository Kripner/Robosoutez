package cz.matejkripner.core;

/**
 * Classical robot constants
 * @author Jakub Vaněk <vanek.jakub4@seznam.cz>
 * @version 1.1
 */
public class ClassicalConstants extends Constants {
	/**
	 * Initializes constants for ClassicalHardware
	 */
	public ClassicalConstants(){
		TILE_LENGTH=280;
		ROBOT_LENGTH=212;
		FROM_WALL_TO_CENTER_DISTANCE=(TILE_LENGTH - ROBOT_LENGTH) / 2; // between robot & wall
		PROGRAMS=4;
		PROGRAM_PROMPT = "PROGRAM:";
		PROGRAM_PROMPT_DELAY=333; // between counter changes
		WHEELDIAMETER=70;
		TRACKWIDTH=90;
		SONIC_MOVE=0; // motor rotation/sonic rotation
		LEFTMOTOR="B";
		RIGHTMOTOR="C";
		SONICMOTOR=""; // FIXME unknown
		GYROPORT="S3";
		SONICPORT=""; // FIXME unknown
		HEADTOUCH_PORT=""; // FIXME unknown
		BACKTOUCH_PORT=""; // FIXME unknown
		ACCEL=4000;
		TRAVELSPEED=180; // cm/sec
		ROTATESPEED=90; // deg/sec
	}
}
