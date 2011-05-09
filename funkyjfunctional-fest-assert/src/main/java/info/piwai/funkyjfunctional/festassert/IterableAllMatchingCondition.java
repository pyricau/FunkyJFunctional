package info.piwai.funkyjfunctional.festassert;

import org.fest.assertions.Condition;

class IterableAllMatchingCondition<T> extends Condition<Iterable<T>> {

    private final Condition<T> condition;

    IterableAllMatchingCondition(Condition<T> condition) {
        this.condition = condition;
    }

    @Override
    public boolean matches(Iterable<T> values) {
        long notMatching = 0;

        StringBuilder notMatchingDescription = new StringBuilder();
        long index = 0;
        for (T value : values) {
            if (!condition.matches(value)) {
                notMatching++;
                condition.description();
                notMatchingDescription //
                        .append("Element ") //
                        .append(index) //
                        .append(", [") //
                        .append(value.toString()) //
                        .append("] does not match condition: ") //
                        .append(condition.description()) //
                        .append("\n") //
                ;
            }
            index++;
        }

        if (notMatching != 0) {
            as(notMatching + " element(s) in the iterable not matching the provided condition: " + notMatchingDescription.toString());
            return false;
        } else {
            return true;
        }
    }
}
