package cz.matejkripner.simulation;

import cz.matejkripner.ai.Map;
import cz.matejkripner.ai.Program;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class Virtual {

    private static final Virtual instance = new Virtual();

    private Virtual() {
        // exists only to defeat initialization
    }

    private Map virtualMap;

    public TestResult run(int iterations) {

    }
    private TestResult runIteration() {
        virtualMap = randomMap();
    }

    private Map randomMap() {
        return Program.randomProgram().getMap();
    }

    public Map getVirtualMap() {
        return virtualMap;
    }

    public static Virtual getInstance() {
        return instance;
    }
}
