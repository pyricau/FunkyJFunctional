/*
 * Copyright 2011 Pierre-Yves Ricau (py.ricau at gmail.com)
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

import com.google.common.base.Function;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public abstract class Func<From, To> {

    /**
     * <p>
     * {@link ClassFunction} is not part of the API, which is why it has
     * package-private scope.
     */
    static class ClassFunction<From, To, U extends Func<From, To>> implements Function<From, To> {

        private final ClassExecutorWithInput<U> executor;

        ClassFunction(ClassExecutorWithInput<U> executor) {
            this.executor = executor;
        }

        @Override
        public To apply(From input) {
            U instance = executor.createExecutedInstance(input);
            return instance.r;
        }
    }

    protected From t;

    protected To r;

    public Func() {
        t = FunkyExecutorWithInput.<From> getThreadLocalParameter();
    }
}
