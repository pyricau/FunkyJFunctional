/*
 * Copyright 2011 Pierre-Yves Ricau (py.ricau at gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package info.piwai.funkyjfunctional.apitest;

import static com.google.common.collect.Lists.transform;
import static info.piwai.funkyjfunctional.Funky.withFunc;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import info.piwai.funkyjfunctional.Func;

import java.util.List;

import org.junit.Test;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class FuncTest {
	
	@Test
	public void testTransform() throws Exception {

		List<Integer> values = asList(42, 69);

		class Price extends Func<Integer, String> {{ t = f+"$"; }}
		
		List<String> prices = transform(values, withFunc(Price.class));

		assertEquals("42$", prices.get(0));
		assertEquals("69$", prices.get(1));
	}
	
	@Test
	public void testStaticTransform() {
		staticTransform();
	}
	
	private static void staticTransform() {
		List<Integer> values = asList(42, 69);

		class Price extends Func<Integer, String> {{ t = f+"$"; }}
		
		List<String> prices = transform(values, withFunc(Price.class));

		assertEquals("42$", prices.get(0));
		assertEquals("69$", prices.get(1));
	}
	
    @Test
    public void testThrows() {
        class Fails extends Func<Object, Integer> {{ t = 42 / 0; }}
        try {
            withFunc(Fails.class).apply(null);
            fail();
        } catch(RuntimeException e) {
            assertTrue(e instanceof ArithmeticException);
        }
    }

}
