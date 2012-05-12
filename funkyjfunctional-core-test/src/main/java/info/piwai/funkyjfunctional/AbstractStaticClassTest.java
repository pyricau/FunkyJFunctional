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
package info.piwai.funkyjfunctional;

import static java.lang.reflect.Modifier.isFinal;
import static java.lang.reflect.Modifier.isProtected;
import static org.fest.assertions.api.Assertions.assertThat;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

/**
 * Checks that a class is a good static API entry point, ie is not final, it has
 * only one constructor, that is protected and has no parameters. This only one
 * to create a new static API entry point from that and benefit from exposure of
 * static methods of the superclass.
 */
public abstract class AbstractStaticClassTest {

	private final Class<?> classToTest;

	public AbstractStaticClassTest(Class<?> classToTest) {
		this.classToTest = classToTest;
	}

	@Test
	public void has_only_one_constructor() {
		assertThat(classToTest.getDeclaredConstructors()).hasSize(1);
	}

	@Test
	public void is_not_final() {
		assertThat(isFinal(classToTest.getModifiers())).isFalse();
	}

	@Test
	public void first_constructor_has_no_parameters() {
		Constructor<?> firstConstructor = classToTest.getDeclaredConstructors()[0];
		assertThat(firstConstructor.getParameterTypes()).hasSize(0);
	}

	@Test
	public void first_constructor_is_protected() {
		Constructor<?> firstConstructor = classToTest.getDeclaredConstructors()[0];
		assertThat(isProtected(firstConstructor.getModifiers())).isTrue();
	}

	@Test
	public void first_constructor_can_be_called_without_throwing() throws Throwable {
		Constructor<?> firstConstructor = classToTest.getDeclaredConstructors()[0];
		firstConstructor.setAccessible(true);
		try {
			firstConstructor.newInstance();
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}
	}

}
