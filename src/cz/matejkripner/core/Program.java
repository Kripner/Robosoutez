package cz.matejkripner.core;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class Program implements Iterable<Program.Instruction>{

    private final List<Instruction> instructions;
    private boolean running;

    public Program() {
        this.instructions = new LinkedList<>();
        running = false;
    }

    @Override
    public Iterator<Instruction> iterator() {
        throw new UnsupportedOperationException("Useless to implement");
    }

    @Override
    public void forEach(Consumer<? super Instruction> action) {
        instructions.forEach(action);
    }

    interface Instruction {
        void perform(Robot robot);
    }

    class MovementInstruction implements Instruction {
        final Movement toGo;
        final SonarMeasurement sonarMeasurementExpected;

        MovementInstruction(Movement toGo, SonarMeasurement sonarMeasurementExpected) {
            this.toGo = toGo;
            this.sonarMeasurementExpected = sonarMeasurementExpected;
        }

        @Override
        public void perform(Robot robot) {

        }
    }

    class GotoInstruction implements Instruction {

        private final Predicate<Robot> condition;
        private final Program goTo;

        GotoInstruction(Predicate<Robot> condition, Program goTo) {
            this.condition = condition;
            this.goTo = goTo;
        }

        @Override
        public void perform(Robot robot) {
            if (condition.test(robot)) {
                running = false;
                goTo.run(robot);
            }
        }
    }

    public Program inst(Movement toGo, SonarMeasurement sonarMeasurementExpected) {
        instructions.add(new MovementInstruction(toGo, sonarMeasurementExpected));
        return this;
    }

    public Program goTo(Predicate<Robot> condition, Program goTo) {
        instructions.add(new GotoInstruction(condition, goTo));
        return this;
    }

    public void run(Robot robot) {
        running = true;
        for (Instruction i : instructions) {
            if(!running) return;
        }
        running = false;
    }
}
