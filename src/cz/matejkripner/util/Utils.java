package cz.matejkripner.util;

import java.util.Random;

/**
 * Utility class
 * @author Jakub Vaněk <vanek.jakub4@seznam.cz>
 */
public class Utils {
	public static Random rand = new Random();
	/**
	 * Get random number
	 * @param min Inclusive minimum
	 * @param max Inclusive maximum
	 * @return Random number
	 */
	public static int randInt(int min, int max) {
		return rand.nextInt((max - min) + 1) + min;
	}
}
