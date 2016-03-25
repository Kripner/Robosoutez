package cz.matejkripner.core;

import java.util.Map;

/**
 * Sonic sensor measurement container
 * @author Matěj Kripner <kripnermatej@gmail.com>
 * @author Jakub Vaněk <vanek.jakub4@seznam.cz>
 * @version 1.1
 */
public class SonicMeasurement {
	/**
	 * Space to left
	 */
    private final float left;
	/**
	 * Space in front of robot
	 */
    private final float ahead;
	/**
	 * Space to right
	 */
    private final float right;

	/**
	 * Default constructor
	 * @param left Space to left
	 * @param ahead Space in front of robot
	 * @param right Space to right
	 */
    public SonicMeasurement(float left, float ahead, float right) {
        this.left = left;
        this.ahead = ahead;
        this.right = right;
    }

	/**
	 * Unpacking constructor
	 * @param directions Packed measurements
	 */
    public SonicMeasurement(Map<Hardware.Direction, Float> directions) {
        this(directions.get(Hardware.Direction.LEFT), directions.get(Hardware.Direction.AHEAD), directions.get(Hardware.Direction.RIGHT));
    }

	/**
	 * Return space to right
	 * @return Space to right
	 */
    public float getRight() {
        return right;
    }

	/**
	 * Return space in front of robot
	 * @return Space in front of robot
	 */
    public float getAhead() {
        return ahead;
    }

	/**
	 * Return space to left
	 * @return Space to left
	 */
    public float getLeft() {
        return left;
    }
}
