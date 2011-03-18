package info.piwai.funkyjfunctional;

import static org.junit.Assert.assertSame;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Ordering;

public class CTest {
	
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
		
		List<Person> persons = Arrays.asList(john, joe);
		
		class Sort extends C<Person> {{r = t1.getName().compareTo(t2.getName());}};

		Ordering<Person> ordering = Ordering.from(C.from(Sort.class));
		
		List<Person> sortedPersons = ordering.sortedCopy(persons);

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
		
		List<Person> persons = Arrays.asList(john, joe);
		
		class Sort extends C<Person> {{r = t1.getName().compareTo(t2.getName());}};

		Ordering<Person> ordering = Ordering.from(C.from(Sort.class));
		
		List<Person> sortedPersons = ordering.sortedCopy(persons);

		assertSame(joe, sortedPersons.get(0));
		assertSame(john, sortedPersons.get(1));
	}

}
