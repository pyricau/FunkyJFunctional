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
package info.piwai.funkyjfunctional.swing;

import static info.piwai.funkyjfunctional.Funky.newFunctionWithInput;
import info.piwai.funkyjfunctional.Funky;

import java.awt.event.ActionListener;

/**
 * FunkyJFunctional enables Java functional programming using method local class
 * declarations and init blocks.
 * 
 * Please see the {@link Funky} javadoc to learn how to use FunkyJFunctional.
 * 
 * <p>
 * This module is dedicated to FunkyJFunctional integration with the Java Swing
 * API.
 * 
 * <p>
 * Code coverage of this module: 100%
 * 
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public final class FunkySwing {

    public static <U extends ActL> ActionListener withActL(Class<U> applyingClass, Object... constructorArguments) {
        return new ClassActionListener<U>(newFunctionWithInput(applyingClass, constructorArguments));
    }

    /**
     * @see {@link Funky} constructor
     */
    FunkySwing() {
        throw new UnsupportedOperationException();
    }

}
