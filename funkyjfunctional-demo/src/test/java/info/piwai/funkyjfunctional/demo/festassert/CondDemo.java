/**
 * Copyright (C) 2011 Pierre-Yves Ricau (py.ricau at gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed To in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package info.piwai.funkyjfunctional.demo.festassert;

import static info.piwai.funkyjfunctional.festassert.FunkyFestAssert.withCond;
import static info.piwai.funkyjfunctional.festassert.api.FunkyAssertions.assertThat;
import static java.util.Arrays.asList;
import static org.fest.assertions.api.Fail.fail;
import static org.junit.Assert.assertTrue;
import info.piwai.funkyjfunctional.festassert.Cond;

import java.util.List;

import org.fest.assertions.core.Condition;
import org.junit.Test;

/**
 * @author Nicolas Francois (nicolas.franc at gmail.com)
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class CondDemo {

	// @off
    class Major extends Cond<Integer> {{ out = in >= 18; }}
    // @on

	@Test
	public void majorAssertion() {
		assertThat(20).is(Major.class);

		assertThat(5).isNot(Major.class);
	}

	@Test
	public void allMajorAssertion() {
		assertThat(asList(18, 30, 50)).are(Major.class);

		assertThat(asList(1, 5, 17)).areNot(Major.class);

		assertThat(asList(1, 5, 19)).areAtLeast(1, Major.class);
	}

	@Test
	public void evenListAssertion() {

		List<Integer> evenList = asList(16, 21);
		List<Integer> oddList = asList(16, 21, 38);

		// @off
        class EvenSize extends Cond<List<?>> {{ out = in != null && in.size() %2 == 0; }};
        // @on

		Condition<List<?>> even = withCond(EvenSize.class);

		assertThat(evenList).is(even);

		assertThat(oddList).isNot(even);
	}

	@Test
	public void defaultDescription() {
		// @off
        class AlwaysFalse extends Cond<Object> {{ out = false; }};
        // @on

		try {
			assertThat(new Object()).is(AlwaysFalse.class);
		} catch (AssertionError e) {
			String message = e.getMessage();
			assertTrue(message.contains("<AlwaysFalse>"));
			return;
		}
		fail("Assertion should have throw an AssertionError");
	}

	@Test
	public void customDescription() {

		// @off
        class AlwaysFalse extends Cond<Object> {{ as="I'm always false!"; out = false; }};
        // @on

		try {
			assertThat(new Object()).is(AlwaysFalse.class);
		} catch (AssertionError e) {
			String message = e.getMessage();
			assertTrue(message.contains("<I'm always false!>"));
			return;
		}
		fail("Assertion should have throw an AssertionError");
	}

}
