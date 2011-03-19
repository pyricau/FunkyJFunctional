/*
 * Copyright 2011 Pierre-Yves Ricau (py.ricau at gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package info.piwai.funkyjfunctional;

import java.util.Comparator;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public abstract class Comp<T> {

    private static class Compared<T> {

        public final T t1;
        public final T t2;

        public Compared(T t1, T t2) {
            this.t1 = t1;
            this.t2 = t2;
        }
    }

    static class ClassComparator<T, U extends Comp<T>> extends FunkyExecutorWithInput<U> implements Comparator<T> {

        public ClassComparator(Class<U> applyingClass, Object instance) {
            super(applyingClass, instance);
        }

        @Override
        public int compare(T t1, T t2) {
            U instance = createExecutedInstance(new Compared<T>(t1, t2));
            return instance.r;
        }
    }
    
    protected T t1;
    protected T t2;

    protected int r;

    public Comp() {
        Compared<T> compared = FunkyExecutorWithInput.getThreadLocalParameter();
        t1 = compared.t1;
        t2 = compared.t2;
    }
}
