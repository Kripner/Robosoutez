package cz.matejkripner.core;

import java.util.logging.Handler;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
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

    int sonar();

    boolean headTouch();

    boolean backTouch();

    int gyro();

    void turnSonar(Direction direction);

    Direction getSonarDirection();

    enum Direction {
        AHEAD,
        LEFT,
        RIGHT;

        public Direction[] sideWith() {
            switch (this) {
                case LEFT:
                case RIGHT:
                return new Direction[] {Direction.AHEAD};
            }
            return new Direction[]{Direction.LEFT, Direction.RIGHT};
        }
    }
}
