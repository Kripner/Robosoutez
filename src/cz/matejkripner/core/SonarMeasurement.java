package cz.matejkripner.core;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class SonarMeasurement {

    private final boolean wallOnHead;
    private final boolean wallOnRight;

    private final boolean wallOnLeft;

    public SonarMeasurement(boolean wallOnHead, boolean wallOnRight, boolean wallOnLeft) {
        this.wallOnHead = wallOnHead;
        this.wallOnRight = wallOnRight;
        this.wallOnLeft = wallOnLeft;
    }

    public boolean isWallOnLeft() {
        return wallOnLeft;
    }

    public boolean isWallOnRight() {
        return wallOnRight;
    }

    public boolean isWallOnHead() {
        return wallOnHead;
    }
}
