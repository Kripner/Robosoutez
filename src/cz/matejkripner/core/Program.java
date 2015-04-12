package cz.matejkripner.core;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class Program implements Iterable<Program.Instruction> {

    private List<Instruction> instructions;

    @Override
    public Iterator<Instruction> iterator() {
        throw new UnsupportedOperationException("Useless to implement");
    }

    @Override
    public void forEach(Consumer<? super Instruction> action) {
        instructions.forEach(action);
    }

    class Instruction {
        final Direction toGo;
        final boolean headTouchExpected;
        final boolean backTouchExpected;
        final SonarMeasurement sonarMeasurementExpected;

        Instruction(Direction toGo, boolean headTouchExpected, boolean backTouchExpected, SonarMeasurement sonarMeasurementExpected) {
            this.toGo = toGo;
            this.headTouchExpected = headTouchExpected;
            this.backTouchExpected = backTouchExpected;
            this.sonarMeasurementExpected = sonarMeasurementExpected;
        }
    }
}
