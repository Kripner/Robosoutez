package cz.matejkripner.core;

import java.util.function.Function;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public enum Direction {
    LEFT(Direction.DOWN, p -> p.addX(-1)),
    UP(Direction.LEFT, p -> p.addY(-1)),
    RIGHT(Direction.UP, p -> p.addX(1)),
    DOWN(Direction.RIGHT, p -> p.addY(1)),;

    private Direction left;
    private Function<Point, Point> goThisDirection;

    Direction(Direction left, Function<Point, Point> goThisDirection) {
        this.left = left;
        this.goThisDirection = goThisDirection;
    }

    public Direction turnLeft() {
        return left;
    }
    public Direction turnRight() {
        return turnLeft().turnLeft().turnLeft();
    }

    public Point goThisDirection(Point point) {
        return goThisDirection.apply(point);
    }

}
