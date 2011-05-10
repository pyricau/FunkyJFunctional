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

import static info.piwai.funkyjfunctional.Funky.*;
import info.piwai.funkyjfunctional.Funky;
import info.piwai.funkyjfunctional.guava.Const.ClassConstraint;
import info.piwai.funkyjfunctional.guava.Func.ClassFunction;
import info.piwai.funkyjfunctional.guava.Pred.ClassPredicate;
import info.piwai.funkyjfunctional.guava.Supp.ClassSupplier;

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
 * Code coverage of this module: 88.3%
 * 
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public abstract class FunkyGuava {

    public static <From, To, U extends Func<From, To>> Function<From, To> withFunc(Class<U> applyingClass) {
        return new ClassFunction<From, To, U>(classExecutorWithInput(applyingClass));
    }

    public static <From, To, U extends Func<From, To>> Function<From, To> withFunc(Class<U> applyingClass, Object... constructorParameters) {
        return new ClassFunction<From, To, U>(classExecutorWithInput(applyingClass, constructorParameters));
    }

    public static <T, U extends Pred<T>> Predicate<T> withPred(Class<U> applyingClass) {
        return new ClassPredicate<T, U>(classExecutorWithInput(applyingClass));
    }

    public static <T, U extends Pred<T>> Predicate<T> withPred(Class<U> applyingClass, Object... constructorParameters) {
        return new ClassPredicate<T, U>(classExecutorWithInput(applyingClass, constructorParameters));
    }

    public static <T, U extends Supp<T>> Supplier<T> withSupp(Class<U> applyingClass) {
        return new ClassSupplier<T, U>(classExecutor(applyingClass));
    }

    public static <T, U extends Supp<T>> Supplier<T> withSupp(Class<U> applyingClass, Object... constructorParameters) {
        return new ClassSupplier<T, U>(classExecutor(applyingClass, constructorParameters));
    }

    public static <T, U extends Const<T>> Constraint<T> withConst(Class<U> applyingClass) {
        return new ClassConstraint<T, U>(classExecutorWithInput(applyingClass));
    }

    public static <T, U extends Const<T>> Constraint<T> withConst(Class<U> applyingClass, Object... constructorParameters) {
        return new ClassConstraint<T, U>(classExecutorWithInput(applyingClass, constructorParameters));
    }

    /**
     * The constructor is private to make sure this class won't be instantiated.
     * The class itself is abstract so that it even cannot be instantiated using
     * the reflection API.
     */
    private FunkyGuava() {
    }

}
