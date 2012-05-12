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
import info.piwai.funkyjfunctional.festassert.Desc;
import info.piwai.funkyjfunctional.festassert.FunkyFestAssert;

import java.util.List;

import org.fest.assertions.api.ListAssert;
import org.fest.assertions.core.Condition;

/**
 * @see ListAssert
 */
public class FunkyListAssert extends ListAssert {

	protected FunkyListAssert(List<?> actual) {
		super(actual);
	}

	public final FunkyListAssert as(Class<? extends Desc> description, Object... constructorArguments) {
		return FunkyFestAssert.as(this, description);
	}

	public final FunkyListAssert is(Class<? extends Cond<List<?>>> condition, Object... constructorArguments) {
		return FunkyFestAssert.is(this, condition);
	}

	public final FunkyListAssert isNot(Class<? extends Cond<List<?>>> condition, Object... constructorArguments) {
		return FunkyFestAssert.isNot(this, condition);
	}

	public final FunkyListAssert has(Class<? extends Cond<List<?>>> condition, Object... constructorArguments) {
		return FunkyFestAssert.has(this, condition);
	}

	public final FunkyListAssert doesNotHave(Class<? extends Cond<List<?>>> condition, Object... constructorArguments) {
		return FunkyFestAssert.doesNotHave(this, condition);
	}

	/**
	 * @see #are(Condition)
	 */
	public <E> FunkyListAssert are(Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return (FunkyListAssert) are(FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #areNot(Condition)
	 */
	public <E> FunkyListAssert areNot(Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return (FunkyListAssert) areNot(FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #have(Condition)
	 */
	public <E> FunkyListAssert have(Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return (FunkyListAssert) have(FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #doNotHave(Condition)
	 */
	public <E> FunkyListAssert doNotHave(Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return (FunkyListAssert) doNotHave(FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #areAtLeast(int, Condition)
	 */
	public <E> FunkyListAssert areAtLeast(int times, Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return (FunkyListAssert) areAtLeast(times, FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #areNotAtLeast(int, Condition)
	 */
	public <E> FunkyListAssert areNotAtLeast(int times, Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return (FunkyListAssert) areNotAtLeast(times, FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #areAtMost(int, Condition)
	 */
	public <E> FunkyListAssert areAtMost(int times, Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return (FunkyListAssert) areAtMost(times, FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #areNotAtMost(int, Condition)
	 */
	public <E> FunkyListAssert areNotAtMost(int times, Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return (FunkyListAssert) areNotAtMost(times, FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #areExactly(int, Condition)
	 */
	public <E> FunkyListAssert areExactly(int times, Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return (FunkyListAssert) areExactly(times, FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #areExactly(int, Condition)
	 */
	public <E> FunkyListAssert areNotExactly(int times, Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return (FunkyListAssert) areExactly(times, FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #haveAtLeast(int, Condition)
	 */
	public <E> FunkyListAssert haveAtLeast(int times, Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return (FunkyListAssert) haveAtLeast(times, FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #doNotHaveAtLeast(int, Condition)
	 */
	public <E> FunkyListAssert doNotHaveAtLeast(int times, Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return (FunkyListAssert) doNotHaveAtLeast(times, FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #haveAtMost(int, Condition)
	 */
	public <E> FunkyListAssert haveAtMost(int times, Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return (FunkyListAssert) haveAtMost(times, FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #doNotHaveAtMost(int, Condition)
	 */
	public <E> FunkyListAssert doNotHaveAtMost(int times, Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return (FunkyListAssert) doNotHaveAtMost(times, FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #haveExactly(int, Condition)
	 */
	public <E> FunkyListAssert haveExactly(int times, Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return (FunkyListAssert) haveExactly(times, FunkyFestAssert.withCond(condition, constructorArguments));
	}

	/**
	 * @see #doNotHaveExactly(int, Condition)
	 */
	public <E> FunkyListAssert doNotHaveExactly(int times, Class<? extends Cond<E>> condition, Object... constructorArguments) {
		return (FunkyListAssert) doNotHaveExactly(times, FunkyFestAssert.withCond(condition, constructorArguments));
	}
}
