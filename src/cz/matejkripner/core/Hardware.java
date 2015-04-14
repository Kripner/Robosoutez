package cz.matejkripner.core;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public interface Hardware {
    void travel(int distance);

    void turnLeft();

    void turnRight();

    boolean isRunning();

    int sonar();

    boolean headTouch();

    boolean backTouch();

    int gyro();

    void turnSonarLeft();

    void turnSonarAhead();

    void turnSonarBack();

    Direction getSonarDirection();

    enum Direction {
        AHEAD,
        LEFT,
        RIGHT
    }
}
