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

import static info.piwai.funkyjfunctional.Funky.*;
import info.piwai.funkyjfunctional.festassert.Cond.ClassCondition;

import org.fest.assertions.Condition;

/**
 * FunkyJFunctional enables Java functional programming using method local class
 * declarations and init blocks.
 * 
 * <p>
 * FunkyJFunctional integrates with <a
 * href="http://fest.easytesting.org/">FEST-Assert</a> (The fluent assertions
 * api).
 * 
 * @author Nicolas Francois (nicolas.franc at gmail.com)
 */
public abstract class FunkyFestAssert {

    public static <T, U extends Cond<T>> Condition<T> withCond(Class<U> applyingClass) {
        return new ClassCondition<T, U>(classExecutorWithInput(applyingClass));
    }

    public static <T, U extends Cond<T>> Condition<T> withCond(Class<U> applyingClass, Object... constructorParameters) {
        return new ClassCondition<T, U>(classExecutorWithInput(applyingClass, constructorParameters));
    }

    /**
     * The constructor is private to make sure this class won't be instantiated.
     * The class itself is abstract so that it even cannot be instantiated using
     * the reflection API.
     */
    private FunkyFestAssert() {
    }

}
