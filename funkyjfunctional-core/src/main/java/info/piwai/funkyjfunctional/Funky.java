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
package info.piwai.funkyjfunctional;

/**
 * FunkyJFunctional enables Java functional programming using method local class
 * declarations and init blocks.
 * 
 * <p>
 * FunkyJFunctional integrates with many different frameworks. If not already
 * available, feel free to implement a new funky module for the framework you
 * love!
 * 
 * <p>
 * The {@link Funky}ain entry point to FunkyJFunctional.
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
 * <li>function instantiation, using the {@link Funky} c{@link Funky}nction declaration</h3>
 * 
 * <p>
 * Let's declare a Minor predicate, that returns true if the given integer
 * parameter is lower than 18.
 * 
 * <pre>
 * // t is the input parameter, and r is the returned value.
 * class Minor extends Pred&lt;Integer&gt; {{ r = t &lt; 18; }}
 * </pre>
 * 
 * <h3>Function instantiation</h3>
 * 
 * <p>
 * To use this predicate, we need to instantiate it as a Guava <a href=
 * "http://guava-libraries.googlecode.com/svn/trunk/javadoc/com/google/common/base/Predicate.html"
 * >Predicate</a> that we can use in our code.
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
 * Iterable&lt;Integer&gt; minors = filter(values, withPred(Minor.class));
 * 
 * // the 'minors' Iterable contains only one element: '16'.
 * assertEquals(Arrays.asList(16), minors);
 * 
 * // The magic all happened in the withPred method, which created a Predicate:
 * Predicate&lt;Integer&gt; minorPredicate = withPred(Adult.class);
 * </pre>
 * 
 * <h2 id="2">2. List of the Funky ways</h2>
 * <p>
 * To know more about what you can do with FunkyJFunctional, have a look at the
 * various Maven modules available. Currently, we created the following modules
 * :
 * <ul>
 * <li>Guava: <b>funkyjfunctional-guava</b>
 * <li>Wicket: <b>funkyjfunctional-wicket</b>
 * <li>Java: <b>funkyjfunctional-java</b>
 * <li>Swing: <b>funkyjfunctional-swing</b>
 * <li>FEST-Assert: <b>funkyjfunctional-fest-assert</b>
 * <li>Contribute to FJF and add a new framework here!
 * </ul>
 * 
 * 
 * <h2 id="3">3. Implementing your own Funky functions</h2>
 * 
 * <p>
 * You may create your own Funky implementations, using the
 * {@link #newFunction(Class, Object...)} method, or the
 * {@link #newFunctionWithInput(Class, Object...)} and
 * {@link #getInput()}) methods if you need input parameters.
 * 
 * <p>
 * To learn more about how to create custom Funky functions, please have a look
 * at the source code of one of the FJF Maven module available.
 * 
 * <h2 id="4">4. Some more info</h2>
 * <ul>
 * <li>FunkyJFunctional was inspired by a <a href=
 * "https://groups.google.com/d/msg/google-guice/Eu-cJ1N2Q_A/jtiRfGlg3G4J"
 * >message</a> on the Google Guice forum.
 * <li>Code coverage of the core module: 100% (97.4% according to <a
 * href="http://emma.sourceforge.net/">Emma</a>)
 * <li>All FunkyJFunctional modules have 100% code coverage.
 * <li>FunkyJFunctional sources are <a href="https://github.com/pyricau/FunkyJFunctional">on GitHub</a>.
 * </ul>
 * 
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 * @author Florent Rami&egrave;re
 */
public final class Funky {

    private static FunctionFactory factory = createThreadSafeFactory();

    public static <T> ClassFunction<T> newFunction(Class<T> applyingClass, Object... constructorArguments) {
        return factory.newFunction(applyingClass, constructorArguments);
    }

    public static <T> ClassFunctionWithInput<T> newFunctionWithInput(Class<T> applyingClass, Object... constructorArguments) {
        return factory.newFunctionWithInput(applyingClass, constructorArguments);
    }

    /**
     * This method is not thread safe. Should be called prior to using any FJF
     * functionnality.
     */
    public static void setThreadSafeInput(boolean threadSafe) {
        if (threadSafe) {
            factory = createThreadSafeFactory();
        } else {
            factory = createSingleThreadFactory();
        }
    }
    
    public static <T> T getInput() {
        return factory.getInputHolder().<T>get();
    }

    private static FunctionFactory createThreadSafeFactory() {
        return new FunctionFactory(ThreadSafeInputHolder.INSTANCE);
    }

    private static FunctionFactory createSingleThreadFactory() {
        return new FunctionFactory(new SingleThreadInputHolder());
    }
    
    private Funky() {
        throw new UnsupportedOperationException();
    }

}
