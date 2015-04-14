package cz.matejkripner.core;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class SonarMeasurement {

    private final int left;
    private final int ahead;
    private final int right;

    public SonarMeasurement(int left, int ahead, int right) {
        this.left = left;
        this.ahead = ahead;
        this.right = right;
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
