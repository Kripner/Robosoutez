package cz.matejkripner.core;

import java.util.function.Consumer;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public enum Movement {
    GO_AHEAD(Robot::goAhead),
    GO_BACK(Robot::goBack),
    TURN_LEFT(Robot::turnLeft),
    TURN_RIGHT(Robot::turnRight);

    private final Consumer<Robot> perform;

    Movement(Consumer<Robot> perform) {
        this.perform = perform;
    }

    public void perform(Robot robot) {
        perform.accept(robot);
    }
}
