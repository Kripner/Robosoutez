package cz.matejkripner.core;

import java.util.Map;

/**
 * @author Matěj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class SonicMeasurement {

    private final int left;
    private final int ahead;
    private final int right;

    public SonicMeasurement(int left, int ahead, int right) {
        this.left = left;
        this.ahead = ahead;
        this.right = right;
    }

    public SonicMeasurement(Map<Hardware.Direction, Integer> directions) {
        this(directions.get(Hardware.Direction.LEFT), directions.get(Hardware.Direction.AHEAD), directions.get(Hardware.Direction.RIGHT));
    }

    public int getRight() {
        return right;
    }

    public int getAhead() {
        return ahead;
    }

    public int getLeft() {
        return left;
    }
}
