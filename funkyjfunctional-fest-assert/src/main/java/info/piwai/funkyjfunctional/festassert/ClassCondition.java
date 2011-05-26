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

import info.piwai.funkyjfunctional.ClassExecutorWithInput;

import org.fest.assertions.Condition;

/**
 * <p>
 * {@link ClassCondition} is not part of the API, which is why it has
 * package-private scope.
 */
final class ClassCondition<T, U extends Cond<T>> extends Condition<T> {

    private final ClassExecutorWithInput<U> executor;

    ClassCondition(ClassExecutorWithInput<U> executor) {
        this.executor = executor;
    }

    @Override
    public boolean matches(T input) {
        U instance = executor.createExecutedInstance(input);

        if (instance.as != null) {
            as(instance.as);
        } else {
            as(executor.getClassSimpleName());
        }

        return instance.out;
    }

}