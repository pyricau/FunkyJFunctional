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

import org.fest.assertions.api.CharArrayAssert;

/**
 * @see CharArrayAssert
 */
public class FunkyCharArrayAssert extends CharArrayAssert {

	protected FunkyCharArrayAssert(char[] actual) {
		super(actual);
	}

	public final FunkyCharArrayAssert as(Class<? extends Desc> description, Object... constructorArguments) {
		return FunkyFestAssert.as(this, description);
	}

	public final FunkyCharArrayAssert is(Class<? extends Cond<char[]>> condition, Object... constructorArguments) {
		return FunkyFestAssert.is(this, condition);
	}

	public final FunkyCharArrayAssert isNot(Class<? extends Cond<char[]>> condition, Object... constructorArguments) {
		return FunkyFestAssert.isNot(this, condition);
	}

	public final FunkyCharArrayAssert has(Class<? extends Cond<char[]>> condition, Object... constructorArguments) {
		return FunkyFestAssert.has(this, condition);
	}

	public final FunkyCharArrayAssert doesNotHave(Class<? extends Cond<char[]>> condition, Object... constructorArguments) {
		return FunkyFestAssert.doesNotHave(this, condition);
	}
}
