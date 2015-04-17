package cz.matejkripner.ai;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class AI implements Runnable {

    private Program program;

    public AI(String mapAsParameter) {
        if(mapAsParameter.equals("1")) mapAsParameter = "FIRST";
        if(mapAsParameter.equals("2")) mapAsParameter = "SECOND";
        if(mapAsParameter.equals("3")) mapAsParameter = "THIRD";
        if(mapAsParameter.equals("4")) mapAsParameter = "FOURTH";
        program = Program.valueOf(mapAsParameter.toUpperCase());
    }
    public void run() {
        program.run();
    }
}
