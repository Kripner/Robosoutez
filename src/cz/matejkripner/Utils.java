package cz.matejkripner;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author Matìj Kripner <kripnermatej@gmail.com>
 * @version 1.0
 */
public class Utils {
    public static void whileLoop(Consumer<Void> action, Predicate<Void> condition, int limit) {
        if (condition.test(null)) {
            action.accept(null);
            whileLoop(action, condition, --limit);
        }
    }

    public static <T> boolean equals(Comparable<T> a, T b, int maxDiff) {
        return Math.abs(a.compareTo(b)) <= maxDiff;
    }
}
