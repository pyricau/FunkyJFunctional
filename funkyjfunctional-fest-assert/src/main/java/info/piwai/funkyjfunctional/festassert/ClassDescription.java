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
package info.piwai.funkyjfunctional.festassert;

import info.piwai.funkyjfunctional.ClassFunction;

import org.fest.assertions.description.Description;

/**
 * <p>
 * {@link ClassDescription}
 * 
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
final class ClassDescription<T extends Desc> extends Description {

    private final ClassFunction<T> function;

    ClassDescription(ClassFunction<T> function) {
        this.function = function;
    }

    @Override
    public String value() {
        T instance = function.execute();
        return instance.out;
    }
}