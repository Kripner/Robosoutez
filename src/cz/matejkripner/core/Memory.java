package cz.matejkripner.core;

import com.sun.istack.internal.NotNull;
import cz.matejkripner.Main;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class Memory {

    private static final Point START = new Point(4, 3);
    private static final Direction STARTING_DIRECTION = Direction.UP;

    private Point robotPosition;

    private Direction robotDirection;

    private Chunk[][] board;

    public Memory(Point robotPosition, Direction robotDirection) {
        this.robotPosition = START;
        this.robotDirection = STARTING_DIRECTION;
    }

    private void initBoard() {
        board = new Chunk[Main.BOARD_HEIGHT][];
        for (int i = 0; i < Main.BOARD_HEIGHT; i++) {
            board[i] = new Chunk[Main.BOARD_WIDTH];
            Arrays.fill(board, Chunk.UNKNOWN);
        }
    }

    public void turnLeft() {
        // TODO: implement
    }

    public void turnRight() {
        // TODO: implement
    }

    public void goAhead() {
        // TODO: implement
    }

    public void goBack() {
        // TODO: implement
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
    public void setChunk(Point point, Chunk newValue) {
        board[point.getY()][point.getX()] = newValue;
    }

    enum Chunk {
        UNKNOWN,
        WALL,
        SPACE_DONE,
        SPACE_NOT_DONE;
    }

}
