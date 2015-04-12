package cz.matejkripner.core;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class Measurement<R> {
    private final R result;

    private boolean actual;
    public Measurement(R result) {
        this.result = result;
        actual = true;
        measurements.add(new WeakReference<>(this));
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }

    public R getResult() {
        return result;
    }

    public boolean isActual() {
        return actual;
    }

    private static final List<WeakReference<Measurement<?>>> measurements = new ArrayList<>();

    public static void allInactual() {
        measurements.forEach(r -> {
            Measurement<?> m;
            if ((m = r.get()) == null) measurements.remove(r); // removed by GC
            else m.setActual(false);
        });
    }
}
