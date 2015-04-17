package cz.matejkripner.ai;

/**
<<<<<<< HEAD
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class AI implements Runnable {

    private Program program;

    public AI(String mapAsParameter) {
        program = Program.valueOf(mapAsParameter.toUpperCase());
    }
    public void run() {
        program.run();
    }
}
