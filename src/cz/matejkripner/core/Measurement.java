package cz.matejkripner.core;

import cz.matejkripner.Main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
//    SONAR {
//        @Override
////        protected Object doMeasurement(Hardware hardware) {
////            Map<Hardware.Direction, Integer> measurements = new HashMap<>();
////            measurements.put(hardware.getSonarDirection(), hardware.sonar());
////            Hardware.Direction[] process = hardware.getSonarDirection().processAll();
////            hardware.turnSonar(process[0]);
////            measurements.put(process[0], hardware.sonar());
////            hardware.turnSonar(process[1]);
////            measurements.put(process[1], hardware.sonar());
////            return new SonarMeasurement(measurements);
////        }
//    },
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
        for(Measurement m : Measurement.values()) {
            m.setActual(false);
        }
    }

    protected Object doMeasurement(Hardware hardware) {
        throw new AbstractMethodError();
    }
}
