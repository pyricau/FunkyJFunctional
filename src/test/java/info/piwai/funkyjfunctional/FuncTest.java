package info.piwai.funkyjfunctional;

import static com.google.common.collect.Lists.transform;
import static info.piwai.funkyjfunctional.Func.with;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class FuncTest {
	
	@Test
	public void testTransform() throws Exception {

		List<Integer> values = asList(42, 69);

		class Price extends Func<Integer, String> {{ t = f+"$"; }};
		
		List<String> prices = transform(values, with(Price.class));

		assertEquals("42$", prices.get(0));
		assertEquals("69$", prices.get(1));
	}
	
	@Test
	public void testStaticTransform() {
		staticTransform();
	}
	
	private static void staticTransform() {
		List<Integer> values = asList(42, 69);

		class Price extends Func<Integer, String> {{ t = f+"$"; }};
		
		List<String> prices = transform(values, with(Price.class));

		assertEquals("42$", prices.get(0));
		assertEquals("69$", prices.get(1));
	}

}
