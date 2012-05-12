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
package info.piwai.funkyjfunctional.festassert;

import static info.piwai.funkyjfunctional.Funky.newFunction;
import static info.piwai.funkyjfunctional.Funky.newFunctionWithInput;
import info.piwai.funkyjfunctional.Funky;
import info.piwai.funkyjfunctional.festassert.api.FunkyAssertions;
import info.piwai.funkyjfunctional.festassert.api.FunkyIterableAssert;
import info.piwai.funkyjfunctional.java.FunkyJava;

import org.fest.assertions.api.AbstractAssert;
import org.fest.assertions.core.Assert;
import org.fest.assertions.core.Condition;
import org.fest.assertions.description.Description;

/**
 * FunkyJFunctional enables Java functional programming using method local class
 * declarations and init blocks.
 * 
 * Please see the {@link Funky} javadoc to learn how to use FunkyJFunctional.
 * 
 * <p>
 * This module is dedicated to FunkyJFunctional integration with <a
 * href="http://fest.easytesting.org/">FEST-Assert</a> (the fluent assertions
 * api).
 * 
 * <h1>Content</h1>
 * <ul>
 * <li>FEST-Assert {@link Condition} with {@link Cond} and
 * {@link #withCond(Class, Object...)}
 * <li>FEST-Assert {@link Description} with {@link Desc} and
 * {@link #withDesc(Class, Object...)}
 * <li> {@link FunkyIterableAssert} to apply {@link Condition}s on all elements
 * of an iterable.
 * </ul>
 * 
 * <p>
 * Code coverage of this module: 100%
 * 
 * @author Nicolas Francois (nicolas.franc at gmail.com)
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class FunkyFestAssert extends FunkyJava {

	/**
	 * @see FunkyFestAssert FunkyFestAssert documentation
	 */
	public static <T, U extends Cond<T>> Condition<T> withCond(Class<U> applyingClass, Object... constructorArguments) {
		return new ClassCondition<T, U>(newFunctionWithInput(applyingClass, constructorArguments));
	}

	/**
	 * @see FunkyFestAssert FunkyFestAssert documentation
	 */
	public static <T, U extends Desc> Description withDesc(Class<U> applyingClass, Object... constructorArguments) {
		return new ClassDescription<U>(newFunction(applyingClass, constructorArguments));
	}

	/**
	 * Helper to fluidify the use of {@link Desc} when you create your own
	 * {@link Assert}. See {@link FunkyAssertions} implementation.
	 * 
	 * @see AbstractAssert#as(Description)
	 */
	@SuppressWarnings({ "unchecked" })
	public static <S extends AbstractAssert<?, ?>> S as(S assertion, Class<? extends Desc> description, Object... constructorArguments) {
		return (S) assertion.as(withDesc(description, constructorArguments));
	}

	/**
	 * Helper to fluidify the use of {@link Cond} when you create your own
	 * {@link Assert}. See {@link FunkyAssertions} implementation.
	 * 
	 * @see AbstractAssert#is(Condition)
	 */
	@SuppressWarnings({ "unchecked" })
	public static <A, S extends AbstractAssert<?, A>> S is(S assertion, Class<? extends Cond<A>> condition, Object... constructorArguments) {
		return (S) assertion.is(withCond(condition, constructorArguments));
	}

	/**
	 * Helper to fluidify the use of {@link Cond} when you create your own
	 * {@link Assert}. See {@link FunkyAssertions} implementation.
	 * 
	 * @see AbstractAssert#isNot(Condition)
	 */
	@SuppressWarnings({ "unchecked" })
	public static <A, S extends AbstractAssert<?, A>> S isNot(S assertion, Class<? extends Cond<A>> condition, Object... constructorArguments) {
		return (S) assertion.isNot(withCond(condition, constructorArguments));
	}

	/**
	 * Helper to fluidify the use of {@link Cond} when you create your own
	 * {@link Assert}. See {@link FunkyAssertions} implementation.
	 * 
	 * @see AbstractAssert#has(Condition)
	 */
	@SuppressWarnings({ "unchecked" })
	public static <A, S extends AbstractAssert<?, A>> S has(S assertion, Class<? extends Cond<A>> condition, Object... constructorArguments) {
		return (S) assertion.has(withCond(condition, constructorArguments));
	}

	/**
	 * Helper to fluidify the use of {@link Cond} when you create your own
	 * {@link Assert}. See {@link FunkyAssertions} implementation.
	 * 
	 * @see AbstractAssert#doesNotHave(Condition)
	 */
	@SuppressWarnings({ "unchecked" })
	public static <A, S extends AbstractAssert<?, A>> S doesNotHave(S assertion, Class<? extends Cond<A>> condition, Object... constructorArguments) {
		return (S) assertion.doesNotHave(withCond(condition, constructorArguments));
	}

	protected FunkyFestAssert() {
	}

}
