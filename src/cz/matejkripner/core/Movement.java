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
            Main.currentHardware.travel(Main.ONE_TILL_DISTANCE);
        }
    },
    GO_BACK {
        @Override
        public void perform(Hardware hardware) {
            Main.currentHardware.travel(-Main.ONE_TILL_DISTANCE);
        }
    },
    GO_HALF_BACK {
        @Override
        public void perform(Hardware hardware) {
            Main.currentHardware.travel(-Main.FROM_WALL_TO_CENTER_DISTANCE);
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

    public void perform(Hardware hardware) {
        throw new AbstractMethodError();
    }

    public boolean cracked() {
        if (this == GO_BACK || this == GO_HALF_BACK) return Main.currentHardware.backTouch();
        else return Main.currentHardware.backTouch();
    }
}
