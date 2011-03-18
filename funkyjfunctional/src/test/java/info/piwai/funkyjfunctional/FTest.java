package info.piwai.funkyjfunctional;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class FTest {
	
	@Test
	public void testTransform() throws Exception {

		List<Integer> values = Arrays.asList(42, 69);

		class Price extends F<Integer, String> {{t = f+"€";}};
		
		List<String> prices = Lists.transform(values, F.from(Price.class));

		assertEquals("42€", prices.get(0));
		assertEquals("69€", prices.get(1));
	}
	
	@Test
	public void testStaticTransform() {
		staticTransform();
	}
	
	private static void staticTransform() {
		List<Integer> values = Arrays.asList(42, 69);

		class Price extends F<Integer, String> {{t = f+"€";}};
		
		List<String> prices = Lists.transform(values, F.from(Price.class));

		assertEquals("42€", prices.get(0));
		assertEquals("69€", prices.get(1));
	}

}
