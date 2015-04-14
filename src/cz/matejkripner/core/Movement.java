package cz.matejkripner.core;

import cz.matejkripner.Main;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public enum Movement {
    GO_AHEAD {
        @Override
        public void perform(Hardware hardware) {
            Main.currentHardware.travel(ONE_BLOCK_DISTANCE);
        }
    },
    GO_BACK {
        @Override
        public void perform(Hardware hardware) {
            Main.currentHardware.travel(-ONE_BLOCK_DISTANCE);
        }
    },
    GO_HALF_BACK {
        @Override
        public void perform(Hardware hardware) {
            Main.currentHardware.travel(-FROM_WALL_TO_CENTER_DISTANCE);
        }
    },
    TURN_LEFT {
        @Override
        public void perform(Hardware hardware) {
            Main.currentHardware.turnLeft();
        }
    },
    TURN_RIGHT {
        @Override
        public void perform(Hardware hardware) {
            Main.currentHardware.turnRight();
        }
    };

    private static final int ONE_BLOCK_DISTANCE = 280; // in millimeters
    private static final int ROBOT_LENGTH = 212;
    private static final int FROM_WALL_TO_CENTER_DISTANCE = (ONE_BLOCK_DISTANCE - ROBOT_LENGTH) / 2;

    public void perform(Hardware hardware) {
        throw new AbstractMethodError();
    }
}
