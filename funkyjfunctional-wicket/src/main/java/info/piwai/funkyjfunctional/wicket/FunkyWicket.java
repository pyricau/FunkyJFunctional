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
package info.piwai.funkyjfunctional.wicket;

import static info.piwai.funkyjfunctional.Funky.newFunction;
import info.piwai.funkyjfunctional.Funky;
import info.piwai.funkyjfunctional.java.FunkyJava;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 * FunkyJFunctional enables Java functional programming using method local class
 * declarations and init blocks.
 * 
 * Please see the {@link Funky} javadoc to learn how to use FunkyJFunctional.
 * 
 * <p>
 * This module is dedicated to FunkyJFunctional integration with <a
 * href="http://wicket.apache.org/">Wicket</a>.
 * 
 * <h1>Content</h1>
 * <ul>
 * <li>Wicket {@link LoadableDetachableModel} with {@link LDM} and
 * {@link #withLDM(Class, Object...)}
 * <li>Wicket {@link AbstractReadOnlyModel} with {@link AROM} and
 * {@link #withAROM(Class, Object...)}
 * </ul>
 * 
 * <p>
 * Code coverage of this module: 100%
 * 
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class FunkyWicket extends FunkyJava {

	/**
	 * @see FunkyWicket FunkyWicket documentation
	 */
	public static <T, U extends LDM<T>> LoadableDetachableModel<T> withLDM(Class<U> applyingClass, Object... constructorArguments) {
		return new ClassLoadableDetachableModel<T, U>(newFunction(applyingClass, constructorArguments));
	}

	/**
	 * @see FunkyWicket FunkyWicket documentation
	 */
	public static <T, U extends AROM<T>> AbstractReadOnlyModel<T> withAROM(Class<U> applyingClass, Object... constructorArguments) {
		return new ClassAbstractReadOnlyModel<T, U>(newFunction(applyingClass, constructorArguments));
	}

	protected FunkyWicket() {
	}

}
