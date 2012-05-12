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

import org.fest.assertions.api.IntegerAssert;

/**
 * @see IntegerAssert
 */
public class FunkyIntegerAssert extends IntegerAssert {

	protected FunkyIntegerAssert(Integer actual) {
		super(actual);
	}

	public final FunkyIntegerAssert as(Class<? extends Desc> description, Object... constructorArguments) {
		return FunkyFestAssert.as(this, description);
	}

	public final FunkyIntegerAssert is(Class<? extends Cond<Integer>> condition, Object... constructorArguments) {
		return FunkyFestAssert.is(this, condition);
	}

	public final FunkyIntegerAssert isNot(Class<? extends Cond<Integer>> condition, Object... constructorArguments) {
		return FunkyFestAssert.isNot(this, condition);
	}

	public final FunkyIntegerAssert has(Class<? extends Cond<Integer>> condition, Object... constructorArguments) {
		return FunkyFestAssert.has(this, condition);
	}

	public final FunkyIntegerAssert doesNotHave(Class<? extends Cond<Integer>> condition, Object... constructorArguments) {
		return FunkyFestAssert.doesNotHave(this, condition);
	}
}