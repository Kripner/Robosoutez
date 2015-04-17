package cz.matejkripner.ai;

import cz.matejkripner.Main;
import cz.matejkripner.core.Hardware;
import cz.matejkripner.core.Movement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static cz.matejkripner.core.Movement.*;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public enum Program implements Runnable {
    FIRST(GO_AHEAD, GO_AHEAD, GO_BACK, TURN_RIGHT, GO_AHEAD, GO_AHEAD, TURN_LEFT, GO_AHEAD, GO_AHEAD, TURN_LEFT, GO_AHEAD
            ,GO_BACK, TURN_LEFT, TURN_LEFT, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_AHEAD, GO_BACK, TURN_LEFT,
            GO_AHEAD, GO_AHEAD, GO_AHEAD, GO_BACK, TURN_RIGHT, GO_AHEAD, GO_AHEAD, TURN_LEFT, GO_AHEAD, GO_BACK, GO_BACK
            ,GO_AHEAD, TURN_RIGHT, GO_AHEAD, GO_AHEAD, TURN_LEFT, GO_AHEAD, GO_BACK, TURN_RIGHT, GO_AHEAD, GO_AHEAD,
            TURN_RIGHT, GO_BACK, GO_AHEAD, TURN_LEFT, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_BACK, GO_AHEAD, GO_AHEAD, GO_AHEAD
            , GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_AHEAD, GO_AHEAD, GO_AHEAD, GO_BACK, TURN_RIGHT, GO_AHEAD, GO_AHEAD, GO_AHEAD,
            GO_BACK, TURN_LEFT, GO_BACK, GO_AHEAD, GO_AHEAD, GO_AHEAD
    ),
    SECOND(GO_AHEAD, TURN_LEFT, GO_BACK, GO_AHEAD, GO_AHEAD, GO_BACK, TURN_RIGHT, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_AHEAD
            ,GO_AHEAD, TURN_LEFT, GO_BACK, GO_AHEAD, TURN_RIGHT, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_AHEAD, GO_AHEAD, TURN_LEFT, GO_BACK, GO_AHEAD, TURN_RIGHT, GO_AHEAD
            , GO_AHEAD, GO_AHEAD, GO_BACK, TURN_RIGHT, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_AHEAD, GO_BACK, GO_BACK, TURN_LEFT, GO_AHEAD, TURN_RIGHT, GO_AHEAD, TURN_LEFT,
            GO_AHEAD, GO_AHEAD, TURN_LEFT, GO_AHEAD, TURN_RIGHT, GO_AHEAD, TURN_RIGHT, GO_AHEAD, GO_AHEAD, GO_BACK, TURN_LEFT, GO_AHEAD,
            GO_AHEAD, TURN_RIGHT, GO_BACK, GO_AHEAD, GO_AHEAD, GO_AHEAD, TURN_LEFT, GO_BACK, GO_AHEAD, TURN_RIGHT, GO_AHEAD, GO_AHEAD,
            TURN_RIGHT, GO_AHEAD, GO_AHEAD, TURN_LEFT, GO_BACK, GO_AHEAD, TURN_RIGHT, GO_AHEAD)
    ,THIRD(GO_AHEAD, GO_AHEAD, GO_BACK, TURN_RIGHT, GO_AHEAD, GO_AHEAD, GO_AHEAD, TURN_LEFT, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_BACK
            ,GO_AHEAD, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_AHEAD, GO_AHEAD, GO_AHEAD, GO_AHEAD, GO_AHEAD, GO_BACK, TURN_RIGHT    , GO_AHEAD, GO_AHEAD
            , TURN_RIGHT, GO_AHEAD, GO_BACK, GO_BACK, TURN_LEFT, GO_AHEAD, GO_AHEAD, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_AHEAD, TURN_LEFT, GO_BACK,
            GO_BACK, GO_BACK, GO_AHEAD, GO_AHEAD, GO_AHEAD, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_BACK, GO_AHEAD,
            GO_AHEAD, GO_AHEAD, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_AHEAD, GO_AHEAD, GO_AHEAD, GO_BACK, TURN_RIGHT, GO_AHEAD, GO_AHEAD, GO_AHEAD,
            GO_BACK, TURN_LEFT,GO_AHEAD
    ),
    FOURTH(GO_AHEAD, GO_AHEAD, GO_BACK, TURN_RIGHT, GO_AHEAD, GO_AHEAD, TURN_LEFT, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_BACK, GO_AHEAD, GO_AHEAD
            , GO_AHEAD, TURN_RIGHT, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_AHEAD, GO_BACK, TURN_LEFT, GO_AHEAD, GO_AHEAD, GO_AHEAD, GO_BACK, TURN_RIGHT,
            GO_AHEAD, GO_AHEAD, TURN_LEFT, GO_AHEAD, TURN_LEFT, GO_AHEAD, TURN_LEFT, GO_AHEAD, GO_AHEAD,GO_AHEAD, TURN_LEFT, GO_AHEAD,
            GO_AHEAD, GO_AHEAD, GO_AHEAD, TURN_LEFT, GO_AHEAD, GO_AHEAD, TURN_LEFT, GO_AHEAD, TURN_RIGHT, GO_AHEAD, TURN_RIGHT, GO_AHEAD, TURN_RIGHT,
            GO_AHEAD, TURN_LEFT, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_BACK, GO_AHEAD, GO_AHEAD, GO_AHEAD, GO_AHEAD, GO_AHEAD, TURN_RIGHT, GO_AHEAD
            ,GO_AHEAD, GO_AHEAD, GO_BACK, TURN_RIGHT, GO_AHEAD, GO_AHEAD, GO_AHEAD, GO_BACK, TURN_LEFT, GO_BACK, GO_AHEAD, GO_AHEAD);


    private final List<Instruction> instructions;

    Program(Movement ... movement) {
        instructions = new ArrayList<>();
        for (Movement m : movement) {
            instructions.add(new MovementInstruction(m));
        }
    }

    void inst(Movement... movements) {
        for (Movement m : movements) {
            instructions.add(new MovementInstruction(m));
        }
    }

    @Override
    public void run() {
        instructions.forEach(i -> i.perform(Main.currentHardware));
    }

    private void perform(Instruction instruction) {
        instruction.perform(Main.currentHardware);
//        if (instruction instanceof MovementInstruction && ((MovementInstruction) instruction).movement.cracked()) {
//            // TODO: work with error (robot's cracked)
//        }
    }

    static interface Instruction {
        void perform(Hardware hardware);
    }

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


    public static Program randomProgram() {
        return values()[((int) (Math.random() * values().length))];
    }
}
