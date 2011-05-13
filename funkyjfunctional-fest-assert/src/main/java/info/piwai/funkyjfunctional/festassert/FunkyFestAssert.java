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

import info.piwai.funkyjfunctional.Funky;
import info.piwai.funkyjfunctional.festassert.Cond.ClassCondition;
import info.piwai.funkyjfunctional.festassert.Desc.ClassDescription;
import org.fest.assertions.Condition;
import org.fest.assertions.Description;

import static info.piwai.funkyjfunctional.Funky.classExecutor;
import static info.piwai.funkyjfunctional.Funky.classExecutorWithInput;

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
 * <p>
 * Code coverage of this module: 100%
 * 
 * @author Nicolas Francois (nicolas.franc at gmail.com)
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public final class FunkyFestAssert {

    public static <T, U extends Cond<T>> Condition<T> withCond(Class<U> applyingClass, Object... constructorArguments) {
        return new ClassCondition<T, U>(classExecutorWithInput(applyingClass, constructorArguments));
    }

    public static <T, U extends Desc> Description withDesc(Class<U> applyingClass, Object... constructorArguments) {
        return new ClassDescription<U>(classExecutor(applyingClass, constructorArguments));
    }

    /**
     * @see {@link Funky} constructor
     */
    FunkyFestAssert() {
        throw new UnsupportedOperationException();
    }

}
