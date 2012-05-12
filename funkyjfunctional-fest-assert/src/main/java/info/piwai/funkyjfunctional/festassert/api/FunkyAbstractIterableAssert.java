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
package info.piwai.funkyjfunctional.festassert.api;

import info.piwai.funkyjfunctional.festassert.Cond;
import info.piwai.funkyjfunctional.festassert.FunkyFestAssert;

import org.fest.assertions.api.AbstractIterableAssert;
import org.fest.assertions.core.Condition;

/**
 * @see AbstractIterableAssert
 */
public abstract class FunkyAbstractIterableAssert<S, A extends Iterable<?>> extends AbstractIterableAssert<S, A> {

	protected FunkyAbstractIterableAssert(A actual, Class<S> selfType) {
		super(actual, selfType);
	}

	/**
	 * @see #are(Condition)
	 */
	public <E> S are(Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return are(FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #areNot(Condition)
	 */
	public <E> S areNot(Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return areNot(FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #have(Condition)
	 */
	public <E> S have(Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return have(FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #doNotHave(Condition)
	 */
	public <E> S doNotHave(Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return doNotHave(FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #areAtLeast(int, Condition)
	 */
	public <E> S areAtLeast(int times, Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return areAtLeast(times, FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #areNotAtLeast(int, Condition)
	 */
	public <E> S areNotAtLeast(int times, Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return areNotAtLeast(times, FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #areAtMost(int, Condition)
	 */
	public <E> S areAtMost(int times, Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return areAtMost(times, FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #areNotAtMost(int, Condition)
	 */
	public <E> S areNotAtMost(int times, Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return areNotAtMost(times, FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #areExactly(int, Condition)
	 */
	public <E> S areExactly(int times, Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return areExactly(times, FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #areExactly(int, Condition)
	 */
	public <E> S areNotExactly(int times, Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return areExactly(times, FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #haveAtLeast(int, Condition)
	 */
	public <E> S haveAtLeast(int times, Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return haveAtLeast(times, FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #doNotHaveAtLeast(int, Condition)
	 */
	public <E> S doNotHaveAtLeast(int times, Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return doNotHaveAtLeast(times, FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #haveAtMost(int, Condition)
	 */
	public <E> S haveAtMost(int times, Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return haveAtMost(times, FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #doNotHaveAtMost(int, Condition)
	 */
	public <E> S doNotHaveAtMost(int times, Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return doNotHaveAtMost(times, FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #haveExactly(int, Condition)
	 */
	public <E> S haveExactly(int times, Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return haveExactly(times, FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #doNotHaveExactly(int, Condition)
	 */
	public <E> S doNotHaveExactly(int times, Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return doNotHaveExactly(times, FunkyFestAssert.withCond(condition, constructorArguments));
	}
}
