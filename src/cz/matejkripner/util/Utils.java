package cz.matejkripner.util;

import lejos.robotics.SampleProvider;

import java.util.Random;

/**
 * Utility class
 * @author Jakub Vaněk <vanek.jakub4@seznam.cz>
 * @version 1.1
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
	public static float[] getSamples(SampleProvider provider){
		float[] floats = new float[provider.sampleSize()];
		provider.fetchSample(floats,0);
		return floats;
	}
	public static float getFirstSample(SampleProvider provider){
		return getSamples(provider)[0];
	}
}
