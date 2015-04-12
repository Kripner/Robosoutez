package cz.matejkripner.core;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class SonarMeasurement {

    private final int wallsOnHead;
    private final int wallsOnRight;
    private final int wallsOnLeft;

    public SonarMeasurement(int wallsOnHead, int wallsOnRight, int wallsOnLeft) {
        this.wallsOnHead = wallsOnHead;
        this.wallsOnRight = wallsOnRight;
        this.wallsOnLeft = wallsOnLeft;
    }

    public int getWallsOnLeft() {
        return wallsOnLeft;
    }

    public int getWallsOnRight() {
        return wallsOnRight;
    }

    public int getWallsOnHead() {
        return wallsOnHead;
    }
}
