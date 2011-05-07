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
package info.piwai.funkyjfunctional;

import org.fest.assertions.Condition;
import info.piwai.funkyjfunctional.Cond.ClassCondition;
import static info.piwai.funkyjfunctional.Funky.classExecutorWithInput;

/**
 * FunkyJFunctional enables Java functional programming using method local class
 * declarations and init blocks.
 *
 * <p>
 * FunkyJFunctional integrates with <a
 * href="http://fest.easytesting.org/">Fest-Assert</a> (The fluent assertions api),
 * but also provides shortcuts for some standard Java interfaces
 * such as {@link Runnable} or {@link Callable}.
 *
 * <p>
 * The {@link FunkyFestAssert} class is the main entry point to FunkyJFunctional.
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
 * <li>function instantiation, using the {@link FunkyFestAssert} class.
 * </ul>
 *
 * <h3>Function declaration</h3>
 *
 * <p>
 * Let's declare an Adult Condition, that returns true if the given integer
 * parameter is greater than 18.
 *
 * <pre>
 * // t is the input parameter, and r is the returned value.
 * class Adult extends Cond&lt;Integer&gt; {{ r = t &gt; 18; }}
 * </pre>
 *
 * <h3>Function instantiation</h3>
 *
 * <p>
 * To use this condition, we need to instantiate it as a Assertions {@link Condition}
 * that we can use in our code.
 *
 * <pre>
 * // Let's import Funky statically, as well as Fest-Assert Assertions class, so that the code looks clearer
 * import static info.piwai.funkyjfunctional.FunkyFestAssert.*;
 * import static org.fest.assertions.Assertions.*;
 *
 * // The magic all happened in the withCond method, which created a Condition:
 *
 *  assertThat(20).satisfies(withCond(Adult.class));
 *  assertThat(5).doesNotSatisfy(withCond(Adult.class));
 * </pre>
 *
 * <h2 id="2">2. List of the Funky ways</h2>
 * <p>
 *
 * <p>
 * Here is the list of the various funky ways currently provided by
 * FunkyJFunctional:
 *
 * <ul>
 * <li>For funky {@link Condition}s, see {@link Cond} and the
 * {@link #withCond(Class)} method.
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
