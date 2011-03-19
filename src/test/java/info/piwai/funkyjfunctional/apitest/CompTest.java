package info.piwai.funkyjfunctional.apitest;

import static com.google.common.collect.Ordering.from;
import static info.piwai.funkyjfunctional.Funky.withComp;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import info.piwai.funkyjfunctional.Comp;

import java.lang.reflect.InvocationTargetException;
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

        class Sort extends Comp<Person> {{ r = t1.getName().compareTo(t2.getName()); }}

        List<Person> sortedPersons = from(withComp(Sort.class)).sortedCopy(asList(john, joe));

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

        class Sort extends Comp<Person> {{ r = t1.getName().compareTo(t2.getName()); }}

        List<Person> sortedPersons = from(withComp(Sort.class)).sortedCopy(asList(john, joe));

        assertSame(joe, sortedPersons.get(0));
        assertSame(john, sortedPersons.get(1));
    }
    
    @Test
    public void testThrows() {
        class Fails extends Comp<Object> {{r = 42 / 0;}}
        try {
            withComp(Fails.class).compare(null, null);
            fail();
        } catch(RuntimeException e) {
            assertTrue(e.getCause() instanceof InvocationTargetException);
            assertTrue(e.getCause().getCause() instanceof ArithmeticException);
        }
    }
}
