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
package info.piwai.funkyjfunctional.festassert.apitest;

import static org.fest.assertions.api.Assertions.assertThat;
import info.piwai.funkyjfunctional.festassert.Cond;
import info.piwai.funkyjfunctional.festassert.Desc;
import info.piwai.funkyjfunctional.festassert.api.FunkyAssertions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.fest.assertions.api.AbstractAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
@RunWith(Parameterized.class)
public class FunkyAssertionsTest {

	private final Class<?> parameterType;
	private final Object parameterValue;

	@Parameters
	public static Collection<Object[]> generateTestCases() {

		List<Object[]> testCases = new ArrayList<Object[]>();

		for (Method method : FunkyAssertions.class.getMethods()) {
			if (isAssertThatMethod(method)) {
				Class<?> parameterType = method.getParameterTypes()[0];

				Object[] testCaseParams = { parameterType, valueForType(parameterType) };

				testCases.add(testCaseParams);
			}
		}

		return testCases;
	}

	private static boolean isAssertThatMethod(Method method) {
		return method.getName().equals("assertThat") && method.getParameterTypes().length == 1;
	}

	private static Object valueForType(Class<?> type) {
		if (type.isPrimitive() && !type.isArray()) {
			if (type.equals(boolean.class)) {
				return true;
			} else if (type.equals(byte.class)) {
				return (byte) 0;
			} else if (type.equals(short.class)) {
				return (short) 0;
			} else if (type.equals(char.class)) {
				return (char) 0;
			} else {
				return 0;
			}
		} else {
			return null;
		}
	}

	// @off
    class AlwaysTrue extends Cond<Void> {{ out = true; }};
    // @on

	// @off
    class AlwaysFalse extends Cond<Void> {{ out = false; }};
    // @on

	public FunkyAssertionsTest(Class<?> parameterType, Object parameterValue) {
		this.parameterType = parameterType;
		this.parameterValue = parameterValue;
	}

	@Test
	public void is_with_true_condition_doesnt_fail() throws Throwable {
		invokeMethod("is", AlwaysTrue.class);
	}

	@Test(expected = AssertionError.class)
	public void is_with_false_condition_fails() throws Throwable {
		invokeMethod("is", AlwaysFalse.class);
	}

	@Test
	public void has_with_true_condition_doesnt_fail() throws Throwable {
		invokeMethod("has", AlwaysTrue.class);
	}

	@Test(expected = AssertionError.class)
	public void has_with_false_condition_fails() throws Throwable {
		invokeMethod("has", AlwaysFalse.class);
	}

	@Test
	public void isNot_with_false_condition_doesnt_fail() throws Throwable {
		invokeMethod("isNot", AlwaysFalse.class);
	}

	@Test(expected = AssertionError.class)
	public void isNot_with_true_condition_fails() throws Throwable {
		invokeMethod("isNot", AlwaysTrue.class);
	}

	@Test
	public void doesNotHave_with_false_condition_doesnt_fail() throws Throwable {
		invokeMethod("doesNotHave", AlwaysFalse.class);
	}

	@Test(expected = AssertionError.class)
	public void doesNotHave_with_true_condition_fails() throws Throwable {
		invokeMethod("doesNotHave", AlwaysTrue.class);
	}

	@Test
	public void as_sets_description() throws Throwable {
		// @off
        class SomeDesc extends Desc {{ out = "Some description";}};
        // @on

		AbstractAssert<?, ?> funkyAssert = invokeMethod("as", SomeDesc.class);

		assertThat(funkyAssert.descriptionText()).isEqualTo("Some description");
	}

	private AbstractAssert<?, ?> invokeMethod(String methodName, Class<?> funkyClass) throws Throwable {
		Method assertThat = FunkyAssertions.class.getMethod("assertThat", parameterType);

		Object funkyAssert;
		try {
			funkyAssert = assertThat.invoke(null, parameterValue);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(String.format("Cannot give a parameter of type %s, expects a %s", parameterValue.getClass(), parameterType), e);
		}

		Method conditionMethod = funkyAssert.getClass().getMethod(methodName, Class.class, Object[].class);
		Object[] emptyConstructorParams = {};

		try {
			conditionMethod.invoke(funkyAssert, funkyClass, emptyConstructorParams);
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}
		return (AbstractAssert<?, ?>) funkyAssert;
	}

}
