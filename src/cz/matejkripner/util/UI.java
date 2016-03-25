package cz.matejkripner.util;

import cz.matejkripner.Main;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

/**
 * User interface
 * @author Jakub Vaněk <vanek.jakub4@seznam.cz>
 * @version 1.1
 */
public class UI {
	/**
	 * Print prompt and ask user for a value, after selected, return this value
	 * @return Selected value from user
	 */
	public static int getValue() {
		int min = 1;
		int max = Main.currentConstants.PROGRAMS;
		int value = 1;

		LCD.clear(); // clear display
		update(value); // write prompt
		Button.setKeyClickVolume(0); // turn off click beep

		while (true) { // until user presses ENTER
			if (Button.ENTER.isDown()) { // if ENTER is pressed
				Sound.playTone(500, 50); // beep
				break; // end loop
			}
			if (Button.RIGHT.isDown() && Button.LEFT.isUp()) { // if only RIGHT is pressed
				Sound.playTone(500, 50); // beep
				if (value < max) // modify value
					value++;
				else if (value == max)
					value = min;
				update(value); // write
			}
			if (Button.LEFT.isDown() && Button.RIGHT.isUp()) { // if only LEFT is pressed
				Sound.playTone(500, 50); // beep
				if (value > min) // modify value
					value--;
				else if (value == min)
					value = max;
				update(value); // write
			}
			Delay.msDelay(Main.currentConstants.PROGRAM_PROMPT_DELAY); // delay
		}
		LCD.clear(); // clear display
		return value; // return accepted value
	}

	/**
	 * Writes value and prompt to display
	 *
	 * @param value Counter value
	 */
	private static void update(int value) {
		LCD.drawString(String.format("%s%2d", Main.currentConstants.PROGRAM_PROMPT,value), 0, 1);
	}


}
