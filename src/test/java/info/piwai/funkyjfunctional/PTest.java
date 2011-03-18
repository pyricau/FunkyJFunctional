package info.piwai.funkyjfunctional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class PTest {

	@Test
	public void testFilter() throws Exception {
		List<Integer> values = Arrays.asList(16, 21);

		class Adult extends P<Integer> {{r = t > 18;}};
		
		Iterable<Integer> adults = Iterables.filter(values, P.from(Adult.class));

		List<Integer> newArrayList = Lists.newArrayList(adults);

		assertFalse(newArrayList.contains(16));
		assertTrue(newArrayList.contains(21));
	}
	
	@Test
	public void testStaticFilter() {
		staticFilter();
	}

	private static void staticFilter() {
		List<Integer> values = Arrays.asList(16, 21);

		class Adult extends P<Integer> {{r = t > 18;}};
		
		Iterable<Integer> adults = Iterables.filter(values, P.from(Adult.class));

		List<Integer> newArrayList = Lists.newArrayList(adults);

		assertFalse(newArrayList.contains(16));
		assertTrue(newArrayList.contains(21));
	}

}
