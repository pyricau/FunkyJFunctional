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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.google.common.base.Function;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public abstract class F<From, To> {
    private static final Object[] EMPTY_OBJECT_ARRAY = new Object[] {};

    private static ThreadLocal<Object> holder = new ThreadLocal<Object>();

    private static class ClassFunction<From, To> implements Function<From, To> {

        private final Object[] ConstructorParameters;
        private final Constructor<F<From, To>> Constructor;

        public ClassFunction(Constructor<F<From, To>> Constructor, Object[] ConstructorParameters) {
            this.Constructor = Constructor;
            this.ConstructorParameters = ConstructorParameters;
        }

        @Override
        public To apply(From input) {
            holder.set(input);
            try {
                return ((F<From, To>) Constructor.newInstance(ConstructorParameters)).t;
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } finally {
                holder.set(null);
            }
        }
    }

    public static <From, To> Function<From, To> withFunction(Class<? extends F<From, To>> applyingClass) {
        return withFunction(applyingClass, null);
    }

    public static <From, To> Function<From, To> withFunction(Class<? extends F<From, To>> applyingClass, Object instance) {

        Constructor<F<From, To>> Constructor = extractConstructor(applyingClass);
        Object[] ConstructorParameters = createConstructorParameters(Constructor, instance);

        return new ClassFunction<From, To>(Constructor, ConstructorParameters);
    }

    private static Object[] createConstructorParameters(Constructor<?> Constructor, Object instance) {
        if (Constructor.getParameterTypes().length == 0) {
            return EMPTY_OBJECT_ARRAY;
        } else {
            return new Object[] { instance };
        }
    }

    @SuppressWarnings("unchecked")
    private static <From, To> Constructor<F<From, To>> extractConstructor(Class<? extends F<From, To>> applyingClass) {
        Constructor<?> Constructor = applyingClass.getDeclaredConstructors()[0];
        if (!Constructor.isAccessible()) {
            Constructor.setAccessible(true);
        }
        return (Constructor<F<From, To>>) Constructor;
    }

    protected From f;

    protected To t;

    @SuppressWarnings("unchecked")
    public F() {
        f = (From) holder.get();
    }
}
