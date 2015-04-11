package cz.matejkripner.core;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point addX(int x) {
        return new Point(this.x + x, y);
    }

    public Point addY(int y) {
        return new Point(x, this.y + y);
    }
}
