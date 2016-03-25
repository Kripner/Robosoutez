package cz.matejkripner.core;

/**
 * Hardware base class
 * @author Matěj Kripner <kripnermatej@gmail.com>
 * @author Jakub Vaněk <vanek.jakub4@seznam.cz>
 * @version 1.1
 */
public interface Hardware {
	/**
	 * Travel specified distance
	 * @param distance Distance in cm
	 */
    void travel(int distance);

	/**
	 * Turn 90° to left
	 */
    void turnLeft();

	/**
	 * Turn 90° to right
	 */
    void turnRight();

	/**
	 * Turn specified angle to left
	 * @param angle Angle to turn by
	 */
    void turnLeft(int angle);

	/**
	 * Turn specified angle to right
	 * @param angle Angle to turn by
	 */
    void turnRight(int angle);

	/**
	 * Turn specified angle, direction is specified by sign
	 * @param angle Angle to turn by
	 */
    void turn(int angle);

	/**
	 * Align to wall behind robot
	 */
	void backAlign();

	/**
	 * Align to wall in front of robot
	 */
	void headAlign();

	/**
	 * Check whether robot is actually moving.
	 * @return True if it's moving
	 */
    boolean isRunning();



	/**
	 * Check whether is head touch sensor pressed.
	 * @return True if pressed.
	 */
    boolean headTouch();

	/**
	 * Check whether is back touch sensor pressed.
	 * @return True if pressed.
	 */
    boolean backTouch();

	/**
	 * Return gyroscope value from angle mode
	 * @return Angle from last reset
	 */
    int gyro();

	/**
	 * Reset gyroscope angle
	 */
    void resetGyro();

	/**
	 * Return distance from wall as measured by ultrasonic sensor
	 * @return Distance in cm
	 */
	float sonic();
	/**
	 * Check if is specified value buggy
	 * @param dist Distance from {@link #sonic()}
	 * @return True if buggy
	 */
	boolean sonicBug(float dist);
	/**
	 * Turn sonic to specified direction
	 * @param direction Direction
	 */
	void turnSonic(Direction direction);

	/**
	 * Get actual sonic direction
	 * @return Actual sonic direction
	 */
	Direction getSonicDirection();

	/**
	 * Get hardware constants
	 * @return Initialized Constants class
	 */
	Constants getConstants();

	/**
	 * Direction of ultrasonic sensor
	 */
    enum Direction { // FIXME enumeration below looks a bit cyclic; you should use constants instead of enum
	    /**
	     * Parallel with robot
	     */
        AHEAD(Direction.LEFT, Direction.RIGHT),
	    /**
	     * To the left
	     */
        LEFT(Direction.AHEAD, Direction.RIGHT),
	    /**
	     * To the right
	     */
        RIGHT(Direction.AHEAD, Direction.LEFT);
	    /**
	     * Rest of directions
	     */
        private final Direction[] processAll;

	    /**
	     * Default constructor
	     * @param processAll Rest of directions
	     */
        Direction(Direction ... processAll) {
            this.processAll = processAll;
        }

	    /**
	     * Return rest of directions
	     * @return Directions
	     */
        public Direction[] processAll() {
            return processAll;
        }

	    /**
	     * Compare to a different direction
	     * @param n Direction to compare to
	     * @return - if it's to left; 0 if it's equal; + if it's to right
	     */
	    public int compare(Direction n) {
		    switch(n){
			    case AHEAD:
				    switch(this){

					    case AHEAD:
						    return 0;
					    case LEFT:
						    return -1;
					    case RIGHT:
						    return 1;
				    }
				    break;
			    case LEFT:
				    switch(this){

					    case AHEAD:
						    return 1;
					    case LEFT:
						    return 0;
					    case RIGHT:
						    return 2;
				    }
				    break;
			    case RIGHT:
				    switch(this){
					    case AHEAD:
						    return -1;
					    case LEFT:
						    return -2;
					    case RIGHT:
						    return 0;
				    }
				    break;
		    }
		    return 0;
	    }
    }
}
