package cz.matejkripner.ai;

import cz.matejkripner.Main;
import cz.matejkripner.core.Hardware;
import cz.matejkripner.core.Movement;
import cz.matejkripner.core.Robot;
import lejos.hardware.Sound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Program container - sequence of {@link Movement}s
 * @author Matěj Kripner <kripnermatej@gmail.com>
 * @author Jakub Vaněk <vanek.jakub4@seznam.cz>
 * @version 1.1
 */
public enum Program implements Runnable {
	/**
	 * Primary program for map n.1
	 */
    PROGRAM1(HEAD_WALL(), GO_BACK(), TURN_RIGHT(), GO_AHEAD(2), TURN_LEFT(), HEAD_WALL(), TURN_LEFT(), HEAD_WALL()
            ,BACK_WALL(), TURN_LEFT(), GO_AHEAD(2), TURN_RIGHT(), GO_AHEAD(), BACK_WALL(), TURN_LEFT(),
            HEAD_WALL(), GO_BACK(), TURN_RIGHT(), GO_AHEAD(2), TURN_LEFT(), HEAD_WALL(), GO_BACK(2)
            ,GO_AHEAD(), TURN_RIGHT(), GO_AHEAD(2),TURN_LEFT(), HEAD_WALL(), BACK_WALL(), TURN_RIGHT(), GO_AHEAD(2),
            TURN_RIGHT(), BACK_WALL(), GO_AHEAD(), TURN_LEFT(), HEAD_WALL(), TURN_RIGHT(), GO_BACK()
            , HEAD_WALL(), TURN_RIGHT(), HEAD_WALL(), GO_BACK(), TURN_RIGHT(), GO_AHEAD(3),
            GO_BACK(), TURN_LEFT(), GO_BACK(), GO_AHEAD(3), TURN_LEFT(),BACK_WALL()),
	/**
	 * Primary program for map n.2
	 */
	PROGRAM2(GO_AHEAD(), TURN_LEFT(), BACK_WALL(), HEAD_WALL(), GO_BACK(), TURN_RIGHT(), HEAD_WALL(), TURN_RIGHT(),GO_AHEAD(2)
            , TURN_LEFT(), BACK_WALL(), HEAD_WALL(), TURN_RIGHT(), HEAD_WALL(), TURN_RIGHT(), GO_AHEAD(2), TURN_LEFT(), BACK_WALL(), HEAD_WALL(), TURN_RIGHT(),
            HEAD_WALL(), GO_BACK(), TURN_RIGHT(), GO_AHEAD(2), TURN_RIGHT(), HEAD_WALL(),BACK_WALL(), TURN_LEFT(), HEAD_WALL(), TURN_RIGHT(), HEAD_WALL(), TURN_LEFT(),
            GO_AHEAD(2), TURN_LEFT(), HEAD_WALL(), TURN_RIGHT(), HEAD_WALL(), TURN_RIGHT(), HEAD_WALL(), GO_BACK(), TURN_LEFT(), HEAD_WALL(),
            TURN_RIGHT(), BACK_WALL(), GO_AHEAD(3), TURN_LEFT(), BACK_WALL(), HEAD_WALL(), TURN_RIGHT(), HEAD_WALL(),
            TURN_RIGHT(), GO_AHEAD(2), TURN_LEFT(), BACK_WALL(), HEAD_WALL(), TURN_RIGHT(), GO_AHEAD(2),TURN_LEFT(),BACK_WALL()),
	/**
	 * Primary program for map n.3
	 */
	PROGRAM3(HEAD_WALL(), GO_BACK(1), TURN_RIGHT(), HEAD_WALL(), TURN_LEFT(), HEAD_WALL(), TURN_RIGHT(), BACK_WALL()
            ,HEAD_WALL(), TURN_RIGHT(), HEAD_WALL(), GO_BACK(), TURN_RIGHT(), GO_AHEAD(2)
            , TURN_RIGHT(), GO_AHEAD(), BACK_WALL(), TURN_LEFT(), HEAD_WALL(), TURN_RIGHT(), GO_AHEAD(), TURN_LEFT(),
            GO_BACK(3), HEAD_WALL(), TURN_RIGHT(), BACK_WALL(), HEAD_WALL(),
            TURN_RIGHT(), HEAD_WALL(), GO_BACK(), TURN_RIGHT(), GO_AHEAD(3),
            GO_BACK(), TURN_LEFT(),GO_AHEAD(2),TURN_LEFT(),BACK_WALL()),
	/**
	 * Primary program for map n.4
	 */
	PROGRAM4(HEAD_WALL(), GO_BACK(), TURN_RIGHT(), GO_AHEAD(2), TURN_LEFT(), HEAD_WALL(), TURN_RIGHT(), BACK_WALL(), HEAD_WALL()
            , TURN_RIGHT(), GO_AHEAD(2), TURN_RIGHT(), GO_AHEAD(), BACK_WALL(), TURN_LEFT(), HEAD_WALL(), GO_BACK(), TURN_RIGHT(),
            HEAD_WALL(), TURN_LEFT(), HEAD_WALL(), TURN_LEFT(), HEAD_WALL(), TURN_LEFT(), GO_AHEAD(3), TURN_LEFT(), GO_AHEAD(4),
            TURN_LEFT(), GO_AHEAD(2), TURN_LEFT(), HEAD_WALL(), TURN_RIGHT(), HEAD_WALL(), TURN_RIGHT(), HEAD_WALL(), TURN_RIGHT(),
            GO_AHEAD(), TURN_LEFT(),HEAD_WALL(), TURN_RIGHT(), BACK_WALL(), HEAD_WALL(), TURN_RIGHT(), HEAD_WALL()
            ,GO_BACK(), TURN_RIGHT(), GO_AHEAD(3), GO_BACK(), TURN_LEFT(), GO_BACK(), GO_AHEAD(3),TURN_LEFT(),BACK_WALL());

	/**
	 * Generate forward instruction
	 * @param amount How many tiles to travel
	 * @return Instruction
	 */
	public static MovementInstruction GO_AHEAD(int amount){
		return new MovementInstruction(Movement.GO_AHEAD,amount);
	}

	/**
	 * Generate 1 forward instruction
	 * @return Instruction
	 */
	public static MovementInstruction GO_AHEAD(){
		return GO_AHEAD(1);
	}

	/**
	 * Generate backward instruction
	 * @param amount How many tiles to travel
	 * @return Instruction
	 */
	public static MovementInstruction GO_BACK(int amount){
		return new MovementInstruction(Movement.GO_BACK,amount);
	}

	/**
	 * Generate 1 backward instruction
	 * @return Instruction
	 */
	public static MovementInstruction GO_BACK(){
		return GO_BACK(1);
	}

	/**
	 * Generate turn left instruction
	 * @return Instruction
	 */
	public static MovementInstruction TURN_LEFT(){
		return new MovementInstruction(Movement.TURN_LEFT,1);
	}

	/**
	 * Generate turn right instruction
	 * @return Instruction
	 */
	public static MovementInstruction TURN_RIGHT(){
		return new MovementInstruction(Movement.TURN_RIGHT,1);
	}

	/**
	 * Generate back align instruction
	 * @return Instruction
	 */
	public static MovementInstruction BACK_WALL(){
		return new MovementInstruction(Movement.BACK_WALL,1);
	}

	/**
	 * Generate head align instruction
	 * @return Instruction
	 */
	public static MovementInstruction HEAD_WALL(){
		return new MovementInstruction(Movement.HEAD_WALL,1);
	}
	/**
	 * Internal representation
	 */
    private final List<Instruction> instructions;

	/**
	 * Default private constructor
	 * @param movements List of movements (which will be turned into array of instructions)
	 */
    Program(Instruction ... movements) {
        instructions = new ArrayList<>();
        add(movements);
    }

	/**
	 * Convert movements into instructions and add them to end of this program
	 * @param movements List of movements
	 */
    void add(Instruction... movements) {
        instructions.addAll(Arrays.asList(movements));
    }

	/**
	 * Run program
	 */
    @Override
    public void run() {
        for(Instruction i : instructions) {
			perform(i);
        }
	    Sound.beepSequenceUp();
    }

	/**
	 * Perform specified instruction
	 * @param i Instruction to execute
	 */
    private void perform(Instruction i) {
	    if(i instanceof MovementInstruction) {
		    MovementInstruction data = (MovementInstruction) i;
		    Robot.getInstance().perform(data.movement,data.amount);
		    Robot.getInstance().waitFor();
		    if(data.movement.cracked()){
			    // TODO implement robot localization & fail recovery
		    }
	    }
	    else i.perform(Main.currentHardware);
    }


	/**
	 * Instruction interface
	 */
    interface Instruction {
        void perform(Hardware hardware);
    }

	/**
	 * Movement instruction
	 */
    static class MovementInstruction implements Instruction {
		/**
		 * Internal representation
		 */
        private final Movement movement;
		/**
		 * How many movements
		 */
		public int amount;

		/**
		 * Internal constructor
		 * @param movement What movement
		 * @param amount How many moves
		 */
        MovementInstruction(Movement movement, int amount) {
            this.movement = movement;
	        this.amount = amount;
        }

		/**
		 * Run this instruction
		 * @param hardware Execution hardware
		 */
        @Override
        public void perform(Hardware hardware) {
	        movement.perform(hardware,amount);
        }
    }
}
