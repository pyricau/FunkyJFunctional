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

import com.google.common.collect.Ordering;
import info.piwai.funkyjfunctional.ClassFunctionWithInput;

/**
 * <p>
 * {@link ClassOrdering}
 *
 * @author Nicolas Francois (nicolas.franc at gmail.com)
 */
public class ClassOrdering <T, U extends Ord<T>> extends Ordering<T> {

    private final ClassFunctionWithInput<U> function;

    ClassOrdering(ClassFunctionWithInput<U> function) {
        this.function = function;
    }

    @Override
    public int compare(T left, T right) {
        U instance = function.execute(new Ordered(left, right));
        return instance.out;
    }
}
