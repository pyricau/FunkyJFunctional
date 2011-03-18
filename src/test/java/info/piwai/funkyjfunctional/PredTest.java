package info.piwai.funkyjfunctional;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Lists.newArrayList;
import static info.piwai.funkyjfunctional.Pred.with;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.junit.Test;

public class PredTest {

	@Test
	public void testFilter() throws Exception {
		List<Integer> values = asList(16, 21);

		class Adult extends Pred<Integer> {{r = t > 18;}};

		List<Integer> adults = newArrayList(filter(values, with(Adult.class)));

		assertFalse(adults.contains(16));
		assertTrue(adults.contains(21));
	}
	
	@Test
	public void testStaticFilter() {
		staticFilter();
	}

	private static void staticFilter() {
		List<Integer> values = asList(16, 21);

		class Adult extends Pred<Integer> {{r = t > 18;}};
		
		List<Integer> adults = newArrayList(filter(values, with(Adult.class)));

		assertFalse(adults.contains(16));
		assertTrue(adults.contains(21));
	}
	
    @Test
    public void testThrows() {
        class Fails extends Pred<Object> {{ r = 42 / 0 > 69;}};
        try {
            with(Fails.class).apply(null);
            fail();
        } catch(RuntimeException e) {
            assertTrue(e.getCause() instanceof InvocationTargetException);
            assertTrue(e.getCause().getCause() instanceof ArithmeticException);
        }
    }
}
