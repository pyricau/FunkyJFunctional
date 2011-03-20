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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 * This class holds FunkyJFunctional's core behavior. It creates instances of
 * the given class each time its init block code needs to be executed.
 * 
 * <p>
 * {@link FunkyExecutor} is not part of the API, which is why it has package-private scope.
 * 
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
abstract class FunkyExecutor<T> {

    private static final Object[] EMPTY_OBJECT_ARRAY = new Object[] {};

    private Constructor<? extends T> constructor;

    private Object[] constructorParameters;

    FunkyExecutor(Class<? extends T> applyingClass) {
        this(applyingClass, null);
    }

    FunkyExecutor(Class<? extends T> applyingClass, Object instance) {
        constructor = extractConstructor(applyingClass);
        constructorParameters = createConstructorParameters(constructor, instance);
    }

    @SuppressWarnings("unchecked")
    private Constructor<? extends T> extractConstructor(Class<? extends T> applyingClass) {
        Constructor<?> constructor = applyingClass.getDeclaredConstructors()[0];
        if (!constructor.isAccessible()) {
            constructor.setAccessible(true);
        }
        return (Constructor<? extends T>) constructor;
    }

    private Object[] createConstructorParameters(Constructor<?> constructor, Object instance) {
        if (constructor.getParameterTypes().length == 0) {
            return EMPTY_OBJECT_ARRAY;
        } else {
            return new Object[] { instance };
        }
    }

    T createExecutedInstance() {
        try {
            return createExecutedInstanceThrowing();
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();

            if (cause instanceof Error) {
                throw ((Error) cause);
            } else if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else {
                throw new RuntimeException(e);
            }
        }
    }

    private T createExecutedInstanceThrowing() throws InvocationTargetException {
        try {
            return constructor.newInstance(constructorParameters);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
