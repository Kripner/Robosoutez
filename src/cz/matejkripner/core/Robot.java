package cz.matejkripner.core;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class Robot {
    private Memory memory;

    public Robot(Memory memory) {
        this.memory = memory;
    }

    protected int gyro() {

    }

    protected boolean headTouch() {

    }

    protected boolean backTouch() {

    }

    public void turnLeft() {

    }

    public void turnRight() {

    }

    public void goAhead() {

    }

    public void goBack() {

    }

    public Memory getMemory() {
        return memory;
    }

}
