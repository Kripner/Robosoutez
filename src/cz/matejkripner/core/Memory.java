package cz.matejkripner.core;

import com.sun.istack.internal.NotNull;

import java.util.Objects;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class Memory {

    private Point robotPosition;

    private Direction robotDirection;

    private Chunk[][] board;

    public Memory(Point robotPosition, Direction robotDirection) {
        this.robotPosition = robotPosition;
        this.robotDirection = robotDirection;
    }

    private void initBoard() {
        board = new Chunk[];
    }

    public void turnLeft() {

    }

    public void turnRight() {

    }

    public void goAhead() {

    }

    public void goBack() {

    }

    public Point getRobotPosition() {
        return robotPosition;
    }

    public Direction getRobotDirection() {
        return robotDirection;
    }

    public Chunk getChunk(Point point) {
        return board[point.getY()][point.getX()];
    }

    enum Chunk {
        UNKNOWN,
        WALL,
        SPACE_DONE,
        SPACE_NOT_DONE;
    }

}
