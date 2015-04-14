package cz.matejkripner.core;

import cz.matejkripner.Main;

import java.util.Arrays;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public enum Measurement {

    BACK_TOUCH {
        @Override
        protected Object doMeasurement(Hardware hardware) {
            return hardware.backTouch();
        }
    },
    HEAD_TOUCH {
        @Override
        protected Object doMeasurement(Hardware hardware) {
            return hardware.headTouch();
        }
    },
    SONAR {
        @Override
        protected Object doMeasurement(Hardware hardware) {
            // TODO: implement
        }
    },
    GYRO {
        @Override
        protected Object doMeasurement(Hardware hardware) {
            return hardware.gyro();
        }
    };

    private boolean actual;
    private Object value;

    Measurement() {
        actual = true;
    }

    public Object get() {
        return (isActual()) ? value : (value = doMeasurement(Main.currentHardware));
    }

    public boolean isActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }

    public static void allOutdated() {
        Arrays.stream(Measurement.values()).forEach(m -> m.setActual(false));
    }

    protected Object doMeasurement(Hardware hardware) {
        throw new AbstractMethodError();
    }
}
