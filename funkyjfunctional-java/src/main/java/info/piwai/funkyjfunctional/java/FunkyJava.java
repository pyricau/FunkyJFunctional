/*
 * Copyright 2011 Pierre-Yves Ricau (py.ricau at gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package info.piwai.funkyjfunctional.java;

import static info.piwai.funkyjfunctional.Funky.classExecutor;
import static info.piwai.funkyjfunctional.Funky.classExecutorWithInput;
import info.piwai.funkyjfunctional.java.Call.ClassCallable;
import info.piwai.funkyjfunctional.java.Comp.ClassComparator;

import java.util.Comparator;
import java.util.concurrent.Callable;

/**
 * FunkyJFunctional enables Java functional programming using method local class
 * declarations and init blocks.
 * 
 * <p>
 * FunkyJFunctional integrates with <a
 * href="http://code.google.com/p/guava-libraries/">Guava</a> (the former google
 * collections), but also provides shortcuts for some standard Java interfaces
 * such as {@link Runnable} or {@link Callable}.
 * 
 * <p>
 * The {@link FunkyJava} class is the main entry point to FunkyJFunctional.
 * 
 * <h1>Contents</h1>
 * 
 * <ul>
 * <li><a href="#1">1. Let's learn the Funky way</a>
 * <li><a href="#2">2. List of the Funky ways</a>
 * <li><a href="#3">3. Implementing your own Funky functions</a>
 * <li><a href="#4">4. Some more info</a>
 * </ul>
 * 
 * <h2 id="1">1. Let's learn the Funky way</h2>
 * 
 * <p>
 * Using FunkyJFunctional always involves two steps:
 * 
 * <ul>
 * <li>function declaration, as a method local class,
 * <li>function instantiation, using the {@link FunkyJava} class.
 * </ul>
 * 
 * <h3>Function declaration</h3>
 * 
 * <p>
 * Let's declare an Adult predicate, that returns true if the given integer
 * parameter is greater than 18.
 * 
 * <pre>
 * // t is the input parameter, and r is the returned value.
 * class Adult extends Pred&lt;Integer&gt; {{ r = t &gt; 18; }}
 * </pre>
 * 
 * <h3>Function instantiation</h3>
 * 
 * <p>
 * To use this predicate, we need to instantiate it as a Guava {@link Predicate}
 * that we can use in our code.
 * 
 * <pre>
 * // Let's import Funky statically, as well as Guava's Iterables class, so that the code looks clearer
 * import static info.piwai.funkyjfunctional.Funky.*;
 * import static com.google.common.collect.Iterables.*;
 * 
 * // Nothing too complex here
 * List&lt;Integer&gt; values = Arrays.asList(16, 21);
 * 
 * // To use Guava's Iterables.filter() method, the withPred() method creates a Predicate from our Adult class.
 * Iterable&lt;Integer&gt; adults = filter(values, withPred(Adult.class));
 * 
 * // the 'adults' Iterable contains only one element: '21'.
 * assertEquals(Arrays.asList(21), adults);
 * 
 * // The magic all happened in the withPred method, which created a Predicate:
 * Predicate&lt;Integer&gt; adultPredicate = withPred(Adult.class);
 * </pre>
 * 
 * <h2 id="2">2. List of the Funky ways</h2>
 * <p>
 * To know more about what you can do with FunkyJFunctional, have a look at the
 * various with*() methods of the {@link FunkyJava} class.
 * 
 * <p>
 * Here is the list of the various funky ways currently provided by
 * FunkyJFunctional:
 * 
 * <ul>
 * <li>For funky {@link Function}s, see {@link Func} and the
 * {@link #withFunc(Class)} method.
 * <li>For funky {@link Predicate}s, see {@link Pred} and the
 * {@link #withPred(Class)} method.
 * <li>For funky {@link Comparator}s, see {@link Comp} and the
 * {@link #withComp(Class)} method.
 * <li>For funky {@link Runnable}s, see the {@link #withRun(Class)} method.
 * <li>For funky {@link Callable}s, see {@link Call} and the
 * {@link #withCall(Class)} method.
 * <li>For funky {@link Supplier}s, see {@link Supp} and the
 * {@link #withSupp(Class)} method.
 * <li>For funky {@link Constraint}s, see {@link Const} and the
 * {@link #withConst(Class)} method.
 * </ul>
 * <h2 id="3">3. Implementing your own Funky functions</h2>
 * 
 * <p>
 * You may create your own Funky implementations, using the
 * {@link #classExecutor(Class, Object...)} method, or the
 * {@link #classExecutorWithInput(Class, Object...)} and
 * {@link #getThreadLocalParameter()}) methods if you need input parameters.
 * 
 * <h2 id="4">4. Some more info</h2>
 * <ul>
 * <li>FunkyJFunctional was inspired by a <a href=
 * "https://groups.google.com/forum/#!msg/google-guice/Eu-cJ1N2Q_A/jtiRfGlg3G4J"
 * >message</a> on the Google Guice forum.
 * <li>FunkyJFunctional has 100% code coverage (in fact it's actually 98.3% but
 * the missing 3.1% are unreachable code).
 * </ul>
 * 
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 * @author Florent Rami&egrave;re
 */
public abstract class FunkyJava {

    /**
     * <p>
     * Creates a {@link Comparator} instance from a {@link Comp} class.
     * 
     * <p>
     * If the init block of the applyingClass references an instance member or a
     * local variable, you should use the {@link #withComp(Class, Object...)}
     * method instead.
     * 
     * @param applyingClass
     *            the local class that represents a {@link Comparator}.
     * @param <T>
     *            The type that is compared.
     * @param <U>
     *            The local class type. Must extend {@link Comp}.
     * @return A {@link Comparator} based on the applyingClass parameter.
     */
    public static <T, U extends Comp<T>> Comparator<T> withComp(Class<U> applyingClass) {
        return new ClassComparator<T, U>(classExecutorWithInput(applyingClass));
    }

    /**
     * <p>
     * See the {@link #withComp(Class)} for details.
     * 
     * <p>
     * If the init block of the applyingClass does not reference an instance
     * member or a local variable, you should use the {@link #withComp(Class)}
     * method instead.
     * 
     * @param constructorParameters
     *            The constructor parameters to give to the created instance.
     *            The first parameter will usually be 'this' or 'null' if the
     *            applyingClass has been declared in an instance method, and the
     *            other parameters will be the local variables that you use from
     *            the local class.
     * @throws IllegalArgumentException
     *             if the applyingClass does not provide a default constructor
     *             <b>or</b> if the instance parameter does not have the
     *             expected type
     * @see #withComp(Class)
     */
    public static <T, U extends Comp<T>> Comparator<T> withComp(Class<U> applyingClass, Object... constructorParameters) throws IllegalArgumentException {
        return new ClassComparator<T, U>(classExecutorWithInput(applyingClass, constructorParameters));
    }

    public static <U> Runnable withRun(Class<U> applyingClass) {
        return new ClassRunnable<U>(classExecutor(applyingClass));
    }

    public static <U> Runnable withRun(Class<U> applyingClass, Object... constructorParameters) {
        return new ClassRunnable<U>(classExecutor(applyingClass, constructorParameters));
    }

    public static <T, U extends Call<T>> Callable<T> withCall(Class<U> applyingClass) {
        return new ClassCallable<T, U>(classExecutor(applyingClass));
    }

    public static <T, U extends Call<T>> Callable<T> withCall(Class<U> applyingClass, Object... constructorParameters) {
        return new ClassCallable<T, U>(classExecutor(applyingClass, constructorParameters));
    }

    /**
     * The constructor is private to make sure this class won't be instantiated.
     * The class itself is abstract so that it even cannot be instantiated using
     * the reflection API.
     */
    private FunkyJava() {
    }

}
