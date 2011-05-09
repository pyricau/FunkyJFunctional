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
package info.piwai.funkyjfunctional.java;

import info.piwai.funkyjfunctional.ClassExecutor;

import java.util.concurrent.Callable;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public abstract class Call<T> {

    /**
     * <p>
     * {@link ClassCallable} is not part of the API, which is why it has
     * package-private scope.
     */
    static final class ClassCallable<T, U extends Call<T>> implements Callable<T> {

        private final ClassExecutor<U> executor;
        
        ClassCallable(ClassExecutor<U> executor) {
            this.executor = executor;
        }

        @Override
        public T call() throws Exception {
            U instance = executor.createExecutedInstance();
            return instance.r;
        }

    }

    protected T r;

}
