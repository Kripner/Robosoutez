package cz.matejkripner.core;

/**
 * @author Mat�j Kripner <kripnermatej@gmail.com>
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
        AHEAD(Direction.LEFT, Direction.RIGHT),
        LEFT(Direction.AHEAD, Direction.RIGHT),
        RIGHT(Direction.AHEAD, Direction.LEFT);

        private final Direction[] processAll;

        Direction(Direction ... processAll) {
            this.processAll = processAll;
        }

        public Direction[] processAll() {
            return processAll;
        }
    }
}
