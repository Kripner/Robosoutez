package cz.matejkripner.ai;

import cz.matejkripner.Main;
import cz.matejkripner.core.Hardware;
import cz.matejkripner.core.Movement;
import cz.matejkripner.core.Robot;

import java.util.ArrayList;
import java.util.List;

import static cz.matejkripner.core.Movement.*;
/**
 * Program container - sequence of {@link Movement}s
 * TODO implement some form of reflection - inject for example sensing before some commands, implement command cumulation (if appropriate)
 * @author Matěj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public enum Program implements Runnable {
	/**
	 * Primary program for map n.1
	 */
    PROGRAM1(GO_AHEAD, GO_AHEAD, GO_BACK, TURN_RIGHT, GO_AHEAD, GO_AHEAD, TURN_LEFT, GO_AHEAD, GO_AHEAD, TURN_LEFT, GO_AHEAD
            ,GO_BACK, TURN_LEFT, TURN_LEFT, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_AHEAD, GO_BACK, TURN_LEFT,
            GO_AHEAD, GO_AHEAD, GO_AHEAD, GO_BACK, TURN_RIGHT, GO_AHEAD, GO_AHEAD, TURN_LEFT, GO_AHEAD, GO_BACK, GO_BACK
            ,GO_AHEAD, TURN_RIGHT, GO_AHEAD, GO_AHEAD, TURN_LEFT, GO_AHEAD, GO_BACK, TURN_RIGHT, GO_AHEAD, GO_AHEAD,
            TURN_RIGHT, GO_BACK, GO_AHEAD, TURN_LEFT, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_BACK, GO_AHEAD, GO_AHEAD, GO_AHEAD
            , GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_AHEAD, GO_AHEAD, GO_AHEAD, GO_BACK, TURN_RIGHT, GO_AHEAD, GO_AHEAD, GO_AHEAD,
            GO_BACK, TURN_LEFT, GO_BACK, GO_AHEAD, GO_AHEAD, GO_AHEAD),
	/**
	 * Primary program for map n.2
	 */
	PROGRAM2(GO_AHEAD, TURN_LEFT, GO_BACK, GO_AHEAD, GO_AHEAD, GO_BACK, TURN_RIGHT, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_AHEAD
            ,GO_AHEAD, TURN_LEFT, GO_BACK, GO_AHEAD, TURN_RIGHT, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_AHEAD, GO_AHEAD, TURN_LEFT, GO_BACK, GO_AHEAD, TURN_RIGHT, GO_AHEAD
            , GO_AHEAD, GO_AHEAD, GO_BACK, TURN_RIGHT, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_AHEAD, GO_BACK, GO_BACK, TURN_LEFT, GO_AHEAD, TURN_RIGHT, GO_AHEAD, TURN_LEFT,
            GO_AHEAD, GO_AHEAD, TURN_LEFT, GO_AHEAD, TURN_RIGHT, GO_AHEAD, TURN_RIGHT, GO_AHEAD, GO_AHEAD, GO_BACK, TURN_LEFT, GO_AHEAD,
            GO_AHEAD, TURN_RIGHT, GO_BACK, GO_AHEAD, GO_AHEAD, GO_AHEAD, TURN_LEFT, GO_BACK, GO_AHEAD, TURN_RIGHT, GO_AHEAD, GO_AHEAD,
            TURN_RIGHT, GO_AHEAD, GO_AHEAD, TURN_LEFT, GO_BACK, GO_AHEAD, TURN_RIGHT, GO_AHEAD),
	/**
	 * Primary program for map n.3
	 */
	PROGRAM3(GO_AHEAD, GO_AHEAD, GO_BACK, TURN_RIGHT, GO_AHEAD, GO_AHEAD, GO_AHEAD, TURN_LEFT, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_BACK
            ,GO_AHEAD, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_AHEAD, GO_AHEAD, GO_AHEAD, GO_AHEAD, GO_AHEAD, GO_BACK, TURN_RIGHT    , GO_AHEAD, GO_AHEAD
            , TURN_RIGHT, GO_AHEAD, GO_BACK, GO_BACK, TURN_LEFT, GO_AHEAD, GO_AHEAD, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_AHEAD, TURN_LEFT, GO_BACK,
            GO_BACK, GO_BACK, GO_AHEAD, GO_AHEAD, GO_AHEAD, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_BACK, GO_AHEAD,
            GO_AHEAD, GO_AHEAD, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_AHEAD, GO_AHEAD, GO_AHEAD, GO_BACK, TURN_RIGHT, GO_AHEAD, GO_AHEAD, GO_AHEAD,
            GO_BACK, TURN_LEFT,GO_AHEAD),
	/**
	 * Primary program for map n.4
	 */
	PROGRAM4(GO_AHEAD, GO_AHEAD, GO_BACK, TURN_RIGHT, GO_AHEAD, GO_AHEAD, TURN_LEFT, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_BACK, GO_AHEAD, GO_AHEAD
            , GO_AHEAD, TURN_RIGHT, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_AHEAD, GO_BACK, TURN_LEFT, GO_AHEAD, GO_AHEAD, GO_AHEAD, GO_BACK, TURN_RIGHT,
            GO_AHEAD, GO_AHEAD, TURN_LEFT, GO_AHEAD, TURN_LEFT, GO_AHEAD, TURN_LEFT, GO_AHEAD, GO_AHEAD,GO_AHEAD, TURN_LEFT, GO_AHEAD,
            GO_AHEAD, GO_AHEAD, GO_AHEAD, TURN_LEFT, GO_AHEAD, GO_AHEAD, TURN_LEFT, GO_AHEAD, TURN_RIGHT, GO_AHEAD, TURN_RIGHT, GO_AHEAD, TURN_RIGHT,
            GO_AHEAD, TURN_LEFT, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_BACK, GO_AHEAD, GO_AHEAD, GO_AHEAD, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_AHEAD
            ,GO_AHEAD, GO_AHEAD, GO_BACK, TURN_RIGHT, GO_AHEAD, GO_AHEAD, GO_AHEAD, GO_BACK, TURN_LEFT, GO_BACK, GO_AHEAD, GO_AHEAD);

	/**
	 * Internal representation
	 */
    private final List<Instruction> instructions;

	/**
	 * Default private constructor
	 * @param movements List of movements (which will be turned into array of instructions)
	 */
    Program(Movement ... movements) {
        instructions = new ArrayList<>();
        add(movements);
    }

	/**
	 * Convert movements into instructions and add them to end of this program
	 * @param movements List of movements
	 */
    void add(Movement... movements) {
        for (Movement m : movements) {
            instructions.add(new MovementInstruction(m));
        }
    }

	/**
	 * Run program
	 */
    @Override
    public void run() {
        for(Instruction i : instructions) {
			perform(i);
        }
    }

	/**
	 * Perform specified instruction
	 * @param i Instruction to execute
	 */
    private void perform(Instruction i) {
	    if(i instanceof MovementInstruction) {
		    Movement move = ((MovementInstruction) i).movement;
		    Robot.getInstance().perform(move);
		    Robot.getInstance().waitFor();
		    if(move.cracked()){
			    // TODO implement fail recovery
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

        private final Movement movement;

        MovementInstruction(Movement movement) {
            this.movement = movement;
        }

        @Override
        public void perform(Hardware hardware) {
            movement.perform(hardware);
        }
    }
}
