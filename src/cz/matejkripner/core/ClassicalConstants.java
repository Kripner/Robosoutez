package cz.matejkripner.core;

/**
 * Classical robot constants
 * @author Jakub Vaněk <vanek.jakub4@seznam.cz>
 */
public class ClassicalConstants extends Constants {
	public ClassicalConstants(){
		TILE_LENGTH=280;
		ROBOT_LENGTH=212;
		FROM_WALL_TO_CENTER_DISTANCE=(TILE_LENGTH - ROBOT_LENGTH) / 2;
		PROGRAMS=4;
		PROGRAM_PROMPT = "PROGRAM:";
		PROGRAM_PROMPT_DELAY=333;
		WHEELDIAMETER=70;
		TRACKWIDTH=90;
		LEFTMOTOR="B";
		RIGHTMOTOR="C";
		GYROPORT="S3";
		SONICPORT="S1"; // FIXME unknown
		ACCEL=4000;
		TRAVELSPEED=180; // cm/sec
		ROTATESPEED=90; // deg/sec
	}
}
