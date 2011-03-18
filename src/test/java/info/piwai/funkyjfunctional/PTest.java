package info.piwai.funkyjfunctional;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Lists.newArrayList;
import static info.piwai.funkyjfunctional.P.withPredicate;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class PTest {

	@Test
	public void testFilter() throws Exception {
		List<Integer> values = asList(16, 21);

		class Adult extends P<Integer> {{r = t > 18;}};
		

		List<Integer> adults = newArrayList(filter(values, withPredicate(Adult.class)));

		assertFalse(adults.contains(16));
		assertTrue(adults.contains(21));
	}
	
	@Test
	public void testStaticFilter() {
		staticFilter();
	}

	private static void staticFilter() {
		List<Integer> values = asList(16, 21);

		class Adult extends P<Integer> {{r = t > 18;}};
		
		List<Integer> adults = newArrayList(filter(values, withPredicate(Adult.class)));

		assertFalse(adults.contains(16));
		assertTrue(adults.contains(21));
	}

}
