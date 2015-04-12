package cz.matejkripner.ai;

import cz.matejkripner.core.Robot;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class AI implements Runnable {
    private Robot robot;

    public AI(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void run() {
        robot.goAhead();
        Map.FIRST.getProgram().run(robot);
    }
}
