package cz.matejkripner.core;

import cz.matejkripner.Main;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Matěj Kripner <kripnermatej@gmail.com>
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
    ULTRASONIC {
        @Override
        protected Object doMeasurement(Hardware hardware) {
            Map<Hardware.Direction, Integer> measurements = new HashMap<>();
            measurements.put(hardware.getSonicDirection(), hardware.sonic());
            Hardware.Direction[] process = hardware.getSonicDirection().processAll();
            hardware.turnSonic(process[0]);
            measurements.put(process[0], hardware.sonic());
            hardware.turnSonic(process[1]);
            measurements.put(process[1], hardware.sonic());
            return new SonicMeasurement(measurements);
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
        if(isActual()) return value;
        else {
            setActual(true);
            return value = doMeasurement(Main.currentHardware);
        }
    }

    public boolean isActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }

    public static void allOutdated() {
        for(Measurement m : Measurement.values()) {
            m.setActual(false);
        }
    }

    protected Object doMeasurement(Hardware hardware) {
        throw new AbstractMethodError();
    }
}
