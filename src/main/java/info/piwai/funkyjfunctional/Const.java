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

import com.google.common.collect.Constraint;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public abstract class Const<T> {

    /**
     * <p>
     * {@link ClassConstraint} is not part of the API, which is why it has
     * package-private scope.
     */
    static class ClassConstraint<T, U extends Const<T>> implements Constraint<T> {

        private final ClassExecutorWithInput<U> executor;

        ClassConstraint(ClassExecutorWithInput<U> executor) {
            this.executor = executor;
        }

        @Override
        public T checkElement(T element) {
            U instance = executor.createExecutedInstance(element);
            return instance.r;
        }

        @Override
        public String toString() {
            return executor.getClassSimpleName() + " constraint";
        }
    }

    protected T t;

    protected T r;

    public Const() {
        t = Funky.<T> getThreadLocalParameter();
        r = t;
    }

    public void invalid(String msg) {
        throw new IllegalArgumentException(msg);
    }
}
