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
import info.piwai.funkyjfunctional.java.FunkyJava;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Supplier;
import com.google.common.collect.Constraint;
import com.google.common.collect.Ordering;

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
 * <h1>Content</h1>
 * <ul>
 * <li>Guava {@link Function} with {@link Func} and
 * {@link #withFunc(Class, Object...)}
 * <li>Guava {@link Predicate} with {@link Pred} and
 * {@link #withPred(Class, Object...)}
 * <li>Guava {@link Supplier} with {@link Supp} and
 * {@link #withSupp(Class, Object...)}
 * <li>Guava {@link Constraint} with {@link Const} and
 * {@link #withConst(Class, Object...)}
 * <li>Guava {@link Ordering} with {@link Ord} and
 * {@link #withConst(Class, Object...)}
 * </ul>
 * 
 * <p>
 * Code coverage of this module: 100%
 * 
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class FunkyGuava extends FunkyJava {

	/**
	 * @see FunkyGuava FunkyGuava documentation
	 */
	public static <From, To, U extends Func<From, To>> Function<From, To> withFunc(Class<U> applyingClass, Object... constructorArguments) {
		return new ClassFunction<From, To, U>(newFunctionWithInput(applyingClass, constructorArguments));
	}

	/**
	 * @see FunkyGuava FunkyGuava documentation
	 */
	public static <T, U extends Pred<T>> Predicate<T> withPred(Class<U> applyingClass, Object... constructorArguments) {
		return new ClassPredicate<T, U>(newFunctionWithInput(applyingClass, constructorArguments));
	}

	/**
	 * @see FunkyGuava FunkyGuava documentation
	 */
	public static <T, U extends Supp<T>> Supplier<T> withSupp(Class<U> applyingClass, Object... constructorArguments) {
		return new ClassSupplier<T, U>(newFunction(applyingClass, constructorArguments));
	}

	/**
	 * @see FunkyGuava FunkyGuava documentation
	 */
	public static <T, U extends Const<T>> Constraint<T> withConst(Class<U> applyingClass, Object... constructorArguments) {
		return new ClassConstraint<T, U>(newFunctionWithInput(applyingClass, constructorArguments));
	}

	/**
	 * @see FunkyGuava FunkyGuava documentation
	 */
	public static <T, U extends Ord<T>> Ordering<T> withOrd(Class<U> applyingClass, Object... constructorArguments) {
		return new ClassOrdering<T, U>(newFunctionWithInput(applyingClass, constructorArguments));
	}

	protected FunkyGuava() {
	}

}
