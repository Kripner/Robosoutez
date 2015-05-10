package cz.matejkripner.core;

import cz.matejkripner.Main;

import java.util.HashMap;
import java.util.Map;

/**
 * Measurement base
 * @author Matěj Kripner <kripnermatej@gmail.com>
 * @author Jakub Vaněk <vanek.jakub4@seznam.cz>
 * @version 1.1
 */
public enum Measurement {
	/**
	 * Back-crash measurement
	 */
    BACK_TOUCH {
        @Override
        protected Object doMeasurement(Hardware hardware) {
            return hardware.backTouch();
        }
    },
	/**
	 * Head-crash measurement
	 */
    HEAD_TOUCH {
        @Override
        protected Object doMeasurement(Hardware hardware) {
            return hardware.headTouch();
        }
    },
	/**
	 * Ultrasonic sensor measurement
	 */
    ULTRASONIC {
        @Override
        protected Object doMeasurement(Hardware hardware) {
            Map<Hardware.Direction, Float> measurements = new HashMap<>();
            measurements.put(hardware.getSonicDirection(), hardware.sonic());
            Hardware.Direction[] process = hardware.getSonicDirection().processAll();
            hardware.turnSonic(process[0]);
            measurements.put(process[0], hardware.sonic());
            hardware.turnSonic(process[1]);
            measurements.put(process[1], hardware.sonic());
            return new SonicMeasurement(measurements);
        }
    },
	/**
	 * Gyroscope measurement
	 */
    GYRO {
        @Override
        protected Object doMeasurement(Hardware hardware) {
            return hardware.gyro();
        }
    };
	/**
	 * Whether is this measurement actual or not
	 */
    private boolean actual;
	/**
	 * Cache of this measurement
	 */
    private Object value;

	/**
	 * Default constructor
	 */
    Measurement() {
        actual = true;
    }

	/**
	 * Get measurement
	 * @return Cached (or updated) measurement
	 */
    public Object get() {
        if(isActual()) return value;
        else {
            setActual(true);
            return value = doMeasurement(Main.currentHardware);
        }
    }

	/**
	 * Check whether this measurement is actual
	 * @return True if actual
	 */
    public boolean isActual() {
        return actual;
    }

	/**
	 * Set the actuality status of this measurement
	 * @param actual Status
	 */
    public void setActual(boolean actual) {
        this.actual = actual;
    }

	/**
	 * Mark all measurements outdated (not actual)
	 */
    public static void allOutdated() {
        for(Measurement m : Measurement.values()) {
            m.setActual(false);
        }
    }

	/**
	 * Measure this physical quantity
	 * @param hardware Robot's hardware
	 * @return Measurement
	 */
    protected Object doMeasurement(Hardware hardware) {
        throw new AbstractMethodError();
    }
}
