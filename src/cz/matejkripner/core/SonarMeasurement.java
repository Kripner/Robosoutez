package cz.matejkripner.core;

import java.util.Objects;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class SonarMeasurement {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SonarMeasurement that = (SonarMeasurement) o;
        return Objects.equals(wallsOnHead, that.wallsOnHead) &&
                Objects.equals(wallsOnRight, that.wallsOnRight) &&
                Objects.equals(wallsOnLeft, that.wallsOnLeft);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wallsOnHead, wallsOnRight, wallsOnLeft);
    }

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
