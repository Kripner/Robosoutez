package cz.matejkripner.core;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class SonarMeasurement {

    private final int wallOnHead;
    private final int wallOnRight;
    private final int wallOnLeft;

    public SonarMeasurement(int wallOnHead, int wallOnRight, int wallOnLeft) {
        this.wallOnHead = wallOnHead;
        this.wallOnRight = wallOnRight;
        this.wallOnLeft = wallOnLeft;
    }

    public int isWallOnLeft() {
        return wallOnLeft;
    }

    public int isWallOnRight() {
        return wallOnRight;
    }

    public int isWallOnHead() {
        return wallOnHead;
    }
}
