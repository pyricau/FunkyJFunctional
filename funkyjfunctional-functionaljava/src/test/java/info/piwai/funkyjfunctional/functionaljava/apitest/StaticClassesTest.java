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
package info.piwai.funkyjfunctional.functionaljava.apitest;

import static java.util.Arrays.asList;
import info.piwai.funkyjfunctional.AbstractStaticClassTest;
import info.piwai.funkyjfunctional.functionaljava.FunkyFunctionalJava;

import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class StaticClassesTest extends AbstractStaticClassTest {

	@Parameters
	public static Collection<Object[]> generateTestCases() {
		Object[][] testCases = { //
		//
		{ FunkyFunctionalJava.class }, //
		};

		return asList(testCases);
	}

	public StaticClassesTest(Class<?> classToTest) {
		super(classToTest);
	}

}
