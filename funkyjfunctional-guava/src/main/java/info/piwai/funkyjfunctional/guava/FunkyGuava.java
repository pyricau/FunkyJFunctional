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
package info.piwai.funkyjfunctional.guava;

import static info.piwai.funkyjfunctional.Funky.newFunction;
import static info.piwai.funkyjfunctional.Funky.newFunctionWithInput;
import info.piwai.funkyjfunctional.Funky;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Supplier;
import com.google.common.collect.Constraint;

/**
 * FunkyJFunctional enables Java functional programming using method local class
 * declarations and init blocks.
 * 
 * Please see the {@link Funky} javadoc to learn how to use FunkyJFunctional.
 * 
 * <p>
 * This module is dedicated to FunkyJFunctional integration with <a
 * href="http://code.google.com/p/guava-libraries/">Guava</a> (the former google
 * collections).
 * 
 * <p>
 * Code coverage of this module: 100%
 * 
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public final class FunkyGuava {

    public static <From, To, U extends Func<From, To>> Function<From, To> withFunc(Class<U> applyingClass, Object... constructorArguments) {
        return new ClassFunction<From, To, U>(newFunctionWithInput(applyingClass, constructorArguments));
    }

    public static <T, U extends Pred<T>> Predicate<T> withPred(Class<U> applyingClass, Object... constructorArguments) {
        return new ClassPredicate<T, U>(newFunctionWithInput(applyingClass, constructorArguments));
    }

    public static <T, U extends Supp<T>> Supplier<T> withSupp(Class<U> applyingClass, Object... constructorArguments) {
        return new ClassSupplier<T, U>(newFunction(applyingClass, constructorArguments));
    }

    public static <T, U extends Const<T>> Constraint<T> withConst(Class<U> applyingClass, Object... constructorArguments) {
        return new ClassConstraint<T, U>(newFunctionWithInput(applyingClass, constructorArguments));
    }

    private FunkyGuava() {
        throw new UnsupportedOperationException();
    }

}
