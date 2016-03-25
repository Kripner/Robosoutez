package cz.matejkripner.core;

import cz.matejkripner.Main;

/**
 * @author Matěj Kripner <kripnermatej@gmail.com>
 * @author Jakub Vaněk <vanek.jakub4@seznam.cz>
 * @version 1.1
 */
public enum Movement {
	/**
	 * Go forward
	 */
    GO_AHEAD {
        @Override
        public void perform(Hardware hardware, int amount) {
            hardware.travel(Main.currentConstants.TILE_LENGTH*amount);
        }
    },
	/**
	 * Go backward
	 */
    GO_BACK {
        @Override
        public void perform(Hardware hardware, int amount) {
	        hardware.travel(-Main.currentConstants.TILE_LENGTH*amount);
        }
    },
	/**
	 * Turn left
	 */
    TURN_LEFT {
        @Override
        public void perform(Hardware hardware, int amount) {
	        hardware.turnLeft();
        }
    },
	/**
	 * Turn right
	 */
    TURN_RIGHT {
        @Override
        public void perform(Hardware hardware, int amount) {
	        hardware.turnRight();
        }
    },
	/**
	 * Align to wall behind robot
	 */
    BACK_WALL {
		@Override
        public void perform(Hardware hardware, int amount){
			hardware.backAlign();
		}
	},
	/**
	 * Align to wall in front of robot
	 */
	HEAD_WALL {
		@Override
		public void perform(Hardware hardware, int amount){
			hardware.headAlign();
		}
	};

	/**
	 * Perform this movement
	 * @param hardware Execution hardware
	 * @param amount How many moves to move
	 */
    public void perform(Hardware hardware, int amount) {
        throw new AbstractMethodError();
    }

	/**
	 * Check whether is this movement cracked
	 * @return Status
	 */
    public boolean cracked() {
        if (this == GO_BACK) return Main.currentHardware.backTouch();
        else return Main.currentHardware.headTouch();
    }
}
