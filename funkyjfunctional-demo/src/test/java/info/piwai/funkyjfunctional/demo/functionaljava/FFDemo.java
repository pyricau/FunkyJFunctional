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
package info.piwai.funkyjfunctional.demo.functionaljava;

import static com.google.common.collect.Lists.newArrayList;
import static fj.Function.compose;
import static fj.data.Array.array;
import static fj.data.List.fromString;
import static fj.data.List.range;
import static info.piwai.funkyjfunctional.functionaljava.FunkyFunctionalJava.withF;
import static java.lang.Character.isLowerCase;
import static java.util.Arrays.asList;
import static org.fest.assertions.api.Assertions.assertThat;
import fj.F;
import fj.data.Array;
import fj.data.List;
import fj.function.Integers;
import info.piwai.funkyjfunctional.functionaljava.FF;

import org.junit.Test;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class FFDemo {

	@Test
	public void lowercaseCompositionDemo() {

		// @off
        class IsLowerCase extends FF<Character, Boolean> {{ out = isLowerCase(in); }};
        // @on

		// @off
        class IsStringLowerCase extends FF<String, Boolean> {{ out = fromString(in).forall(withF(IsLowerCase.class)); }};
        // @on

		/*
		 * "what" is the only lowercase entry, try removing it
		 */

		final Array<String> a = array("Hello", "There", "what", "DAY", "iS", "iT");

		boolean arrayContainsALowerCaseString = a.exists(withF(IsStringLowerCase.class));

		assertThat(arrayContainsALowerCaseString).isTrue();
	}

	@Test
	public void numbers() {

		F<Integer, Integer> plusOne = Integers.add.f(1);

		// @off
        class TimesTwo extends FF<Integer, Integer> {{ out = in * 2; }};
        // @on

		F<Integer, Integer> timesTwoPlusOne = compose(plusOne, withF(TimesTwo.class));

		assertThat(timesTwoPlusOne.f(2)).isEqualTo(5);

	}

	@Test
	public void testFactors() {
		assertThat(newArrayList(factors(12).toCollection())).isEqualTo(asList(1, 2, 3, 4, 6, 12));
	}

	public static List<Integer> factors(final int number) {
		// @off
		class Factor extends FF<Integer, Boolean> {{ out = number % in == 0; }}
		// on
		return range(1, number + 1).filter(withF(Factor.class, number));
	}

}
