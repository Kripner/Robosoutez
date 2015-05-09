package cz.matejkripner.core;

/**
 * @author Matěj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public interface Hardware {
    void travel(int distance);

    void turnLeft();

    void turnRight();

    void turnLeft(int angle);

    void turnRight(int angle);

    void turn(int angle);

    boolean isRunning();

    int sonic();

    boolean headTouch();

    boolean backTouch();

    int gyro();

    void resetGyro();

	Constants getConstants();
    void turnSonic(Direction direction);

    Direction getSonicDirection();

    enum Direction { // FIXME enumeration below looks a bit cyclic; you should use constants instead of enum
        AHEAD(Direction.LEFT, Direction.RIGHT),
        LEFT(Direction.AHEAD, Direction.RIGHT),
        RIGHT(Direction.AHEAD, Direction.LEFT);

        private final Direction[] processAll;

        Direction(Direction ... processAll) {
            this.processAll = processAll;
        }

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
