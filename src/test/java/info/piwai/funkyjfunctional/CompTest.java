package info.piwai.funkyjfunctional;

import static com.google.common.collect.Ordering.from;
import static info.piwai.funkyjfunctional.Comp.with;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertSame;

import java.util.List;

import org.junit.Test;

public class CompTest {
    
    public static class Person {
        private final String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    @Test
    public void testOrdering() throws Exception {
        Person john = new Person("John");
        Person joe = new Person("Joe");

        class Sort extends Comp<Person> {{r = t1.getName().compareTo(t2.getName());}}

        List<Person> sortedPersons = from(with(Sort.class)).sortedCopy(asList(john, joe));

        assertSame(joe, sortedPersons.get(0));
        assertSame(john, sortedPersons.get(1));
    }

    @Test
    public void testStaticOrdering() {
        staticOrdering();
    }

    private static void staticOrdering() {
        Person john = new Person("John");
        Person joe = new Person("Joe");

        class Sort extends Comp<Person> {{r = t1.getName().compareTo(t2.getName());}}

        List<Person> sortedPersons = from(with(Sort.class)).sortedCopy(asList(john, joe));

        assertSame(joe, sortedPersons.get(0));
        assertSame(john, sortedPersons.get(1));
    }
}
