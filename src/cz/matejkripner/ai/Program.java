package cz.matejkripner.ai;

import cz.matejkripner.Main;
import cz.matejkripner.core.Hardware;
import cz.matejkripner.core.Movement;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public enum Program implements Runnable {
    FIRST(new Map()), // TODO: initialize
    SECOND(new Map()),
    THIRD(new Map()),
    FOURTH(new Map()),
    UNIVERSAL(null);

    private final List<Instruction> instructions;

    private final Map map;

    Program(Map map) {
        this.map = map == null ? Map.getUniversal() : map;
        instructions = new LinkedList<>();
    }

    public Map getMap() {
        return map;
    }

    void inst(Movement ... movements) {
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
        if (instruction instanceof MovementInstruction && ((MovementInstruction) instruction).movement.cracked()) {
            // TODO: work with error (robot's cracked)
        }
    }

    interface Instruction {
        void perform(Hardware hardware);
    }
    class MovementInstruction implements Instruction {

        private final Movement movement;

        MovementInstruction(Movement movement) {
            this.movement = movement;
        }

        @Override
        public void perform(Hardware hardware) {
            movement.perform(hardware);
        }
    }

//    class BadMapException extends RuntimeException {
//        private Map betterMap;
//
//        BadMapException(Map betterMap) {
//            this.betterMap = betterMap;
//        }
//
//        BadMapException() {
//            this(null);
//        }
//    }

    public static Program randomProgram() {
        return values()[((int) (Math.random() * values().length))];
    }
}
