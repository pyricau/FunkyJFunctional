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

import info.piwai.funkyjfunctional.ClassFunctionWithInput;
import fj.F2;

/**
 * <p>
 * {@link F2}
 * 
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
final class ClassF2<A, B, C, T extends FF2<A, B, C>> implements F2<A, B, C> {

    private final ClassFunctionWithInput<T> function;

    ClassF2(ClassFunctionWithInput<T> function) {
        this.function = function;
    }

    @Override
    public C f(A a, B b) {
        T instance = function.execute(new FF2Input<A, B>(a, b));
        return instance.out;
    }
}