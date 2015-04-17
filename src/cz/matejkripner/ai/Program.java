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
    FIRST(new Map(),
            new InstructionsBuilder()
            .add(GO_AHEAD)
            ), // TODO: initialize
    SECOND(new Map()),
    THIRD(new Map()),
    FOURTH(new Map()),
    UNIVERSAL(null);

    private final List<Instruction> instructions;

    private final Map map;

    Program() {
        this(null);
    }

    Program(Map map) {
        this.map = map == null ? Map.getUniversal() : map;
        instructions = new LinkedList<>();
    }

    Program(Map map, InstructionsBuilder instructions) {
        this.map = map;
        this.instructions = instructions.instructions;
    }

    public Map getMap() {
        return map;
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
        if (instruction instanceof MovementInstruction && ((MovementInstruction) instruction).movement.cracked()) {
            // TODO: work with error (robot's cracked)
        }
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

    private static class InstructionsBuilder {
        private ArrayList<Instruction> instructions;

        public InstructionsBuilder add(Movement ... instruction) {
            instructions.addAll(Arrays.asList(Arrays.stream(instruction).map(MovementInstruction::new).collect(Collectors.toCollection(ArrayList::new))));
            return this;
        }
    }
}
