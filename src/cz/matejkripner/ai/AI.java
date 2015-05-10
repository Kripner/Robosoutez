package cz.matejkripner.ai;

/**
 * AI module
 * @author Matěj Kripner <kripnermatej@gmail.com>
 * @author Jakub Vaněk <vanek.jakub4@seznam.cz>
 * @version 1.1
 */
public class AI implements Runnable {
	/**
	 * Selected program
	 */
    private Program program;

	/**
	 * Initialize AI with selected map's program
	 * @see cz.matejkripner.ai.Program
	 * @param prgNum Program number
	 */
    public AI(int prgNum) {
	    program = Program.valueOf(getProgramName(prgNum));
    }

	/**
	 * Execute program
	 */
    public void run() {
        program.run();
    }

	/**
	 * Change program number to enum member name
	 * @param number program number
	 * @return enum member name
	 */
	private static String getProgramName(int number){
		return String.format("PROGRAM%d",number);
	}
}
