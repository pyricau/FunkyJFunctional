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
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class Run implements Runnable {
    private static final Object[] EMPTY_OBJECT_ARRAY = new Object[] {};

    private final Constructor<?> constructor;
    private final Object[] constructorParameters;

    private Run(Constructor<?> constructor, Object[] constructorParameters) {
        this.constructorParameters = constructorParameters;
        this.constructor = constructor;
    }
    
    public static Runnable with(Class<?> applyingClass) {
        return with(applyingClass, null);
    }

    public static Runnable with(Class<?> applyingClass, Object instance) {
        Constructor<?> constructor = extractConstructor(applyingClass);
        Object[] constructorParameters = createConstructorParameters(constructor, instance);
        return new Run(constructor, constructorParameters);
    }

    @Override
    public void run() {
        try {
             constructor.newInstance(constructorParameters);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    
    private static Object[] createConstructorParameters(Constructor<?> constructor, Object instance) {
        if (constructor.getParameterTypes().length == 0) {
            return EMPTY_OBJECT_ARRAY;
        } else {
            return new Object[] { instance };
        }
    }

    private static Constructor<?> extractConstructor(Class<?> applyingClass) {
        Constructor<?> constructor = applyingClass.getDeclaredConstructors()[0];
        if (!constructor.isAccessible()) {
            constructor.setAccessible(true);
        }
        return (Constructor<?>) constructor;
    }


}
