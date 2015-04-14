package cz.matejkripner.core;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class ClassicalHardware implements Hardware {

    private static final ClassicalHardware instance = new ClassicalHardware();

    private ClassicalHardware() {
        // exists only to defeat initialization
    }

    public static ClassicalHardware getInstance() {
        return instance;
    }

    // TODO: implement methods using standard EV3 libraries

    @Override
    public void forward() {

    }

    @Override
    public void backward() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public int sonar() {
        return 0;
    }

    @Override
    public boolean headTouch() {
        return false;
    }

    @Override
    public boolean backTouch() {
        return false;
    }

    @Override
    public int gyro() {
        return 0;
    }

    @Override
    public void turnSonarLeft() {

    }

    @Override
    public void turnSonarAhead() {

    }

    @Override
    public void turnSonarBack() {

    }

    @Override
    public Direction getSonarDirection() {
        return null;
    }
}
