package cz.matejkripner.core;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class Robot {
    private Memory memory;

    private Measurement<Boolean> headTouched;
    private Measurement<Boolean> backTouched;
    private Measurement<Integer> gyro;
    private Measurement<SonarMeasurement> sonar;

    public Robot(Memory memory) {
        this.memory = memory;
    }

    public int gyro() {
        if(gyro.isActual()) return gyro.getResult();
        int result = 0;
        // TODO: logic
        return (gyro = new Measurement<>(result)).getResult();

    }

    public Direction direction() {
        // TODO: implement
    }

    public boolean headTouch() {
        if(headTouched.isActual()) return headTouched.getResult();
        boolean result = false;
        // TODO: logic
        return (headTouched = new Measurement<>(result)).getResult();
        // TODO: work with memory
    }

    public boolean backTouch() {
        if(backTouched.isActual()) return backTouched.getResult();
        boolean result = false;
        // TODO: logic
        return (backTouched = new Measurement<>(result)).getResult();
        // TODO: work with memory
    }

    public SonarMeasurement sonar() {
        if(sonar.isActual()) return sonar.getResult();
        SonarMeasurement result = null;
        // TODO: logic
        return (sonar = new Measurement<>(result)).getResult();
        // TODO: work with memory
    }

    public void turnLeft() {
        Measurement.allOutdated(); // TODO: maybe not all measurement
        memory.turnLeft();
        // TODO: logic
    }

    public void turnRight() {
        Measurement.allOutdated(); // TODO: maybe not all measurement
        memory.turnRight();
        // TODO: logic
    }

    public void goAhead() {
        Measurement.allOutdated();
        memory.goAhead();
        // TODO: logic
    }

    public void goBack() {
        Measurement.allOutdated();
        memory.goBack();
        // TODO: logic
    }

    public Memory getMemory() {
        return memory;
    }

}
