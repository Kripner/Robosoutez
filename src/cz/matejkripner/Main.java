package cz.matejkripner;

import cz.matejkripner.ai.AI;
import cz.matejkripner.core.ClassicalHardware;
import cz.matejkripner.core.Constants;
import cz.matejkripner.core.Hardware;
import cz.matejkripner.util.UI;

/**
 * Main class
 * @author Matěj Kripner <kripnermatej@gmail.com>
 * @author Jakub Vaněk <vanek.jakub4@seznam.cz>
 * @version 1.1
 */
public class Main {
	/**
	 * Current robot
	 */
    public static Hardware currentHardware;
	/**
	 * Current robot's constants
	 */
	public static Constants currentConstants;

	/**
	 * Program entry-point
	 * @param args Ignored
	 */
    public static void main(String[] args) {
        currentHardware = ClassicalHardware.getInstance(); // initialize classical robot
	    currentConstants = currentHardware.getConstants(); // get constants
        currentHardware.resetGyro();

        AI ai = new AI(UI.getValue()); // get program number and init corresponding AI
        ai.run(); // start that AI
    }
}
