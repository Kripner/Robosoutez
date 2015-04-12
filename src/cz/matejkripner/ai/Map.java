package cz.matejkripner.ai;

import cz.matejkripner.core.Movement;
import cz.matejkripner.core.Program;
import cz.matejkripner.core.SonarMeasurement;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public enum Map {
    FIRST(new Program()
            .inst(Movement.GO_AHEAD, new SonarMeasurement(0, 0, 0))
            .goTo(r -> r.sonar().getWallsOnHead() == 2, Map.SECOND.getProgram()) // we are on 2. map
            .goTo(r -> r.sonar().getWallsOnLeft() == 2, Map.THIRD.getProgram()) // we are on 3. map
            // we cannot determine between 1. and 4. map
            .inst(Movement.GO_BACK, new SonarMeasurement(1, 4, 4))
            .inst(Movement.TURN_RIGHT, new SonarMeasurement(4, 1, 1))
            // ...
    ),

    SECOND(new Program()),
    THIRD(new Program()),
    FOURTH(new Program());

    private final Program program;

    Map(Program program) {
        this.program = program;
    }

    public Program getProgram() {
        return program;
    }
}
