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
package info.piwai.funkyjfunctional.functionaljava;

import static info.piwai.funkyjfunctional.Funky.*;
import fj.F;
import fj.F2;
import info.piwai.funkyjfunctional.Funky;

/**
 * FunkyJFunctional enables Java functional programming using method local class
 * declarations and init blocks.
 * 
 * Please see the {@link Funky} javadoc to learn how to use FunkyJFunctional.
 * 
 * <p>
 * This module is dedicated to FunkyJFunctional integration with <a
 * href="http://functionaljava.org/">Functional Java</a>.
 * 
 * <h1>Content</h1>
 * <ul>
 * <li> Functional Java {@link F} with {@link FF} and {@link #withF(Class, Object...)}
 * </ul>
 * 
 * <p>
 * Code coverage of this module: 100%
 * 
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public final class FunkyFunctionalJava {

    /**
     * @see FunkyFunctionalJava FunkyFunctionalJava documentation
     */
    public static <A, B, T extends FF<A, B>> F<A, B> withF(Class<T> applyingClass, Object... constructorArguments) {
        return new ClassF<A, B, T>(newFunctionWithInput(applyingClass, constructorArguments));
    }
    
    /**
     * @see FunkyFunctionalJava FunkyFunctionalJava documentation
     */
    public static <A, B, C, T extends FF2<A, B, C>> F2<A, B, C> withF2(Class<T> applyingClass, Object... constructorArguments) {
        return new ClassF2<A, B, C, T>(newFunctionWithInput(applyingClass, constructorArguments));
    }

    private FunkyFunctionalJava() {
        throw new UnsupportedOperationException();
    }

}
