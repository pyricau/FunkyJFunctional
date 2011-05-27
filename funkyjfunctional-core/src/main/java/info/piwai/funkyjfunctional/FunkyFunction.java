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
package info.piwai.funkyjfunctional;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * {@link ClassFunction} Funky implementation
 * 
 * @see ClassFunction
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
final class FunkyFunction<T> implements ClassFunction<T> {

    private static final long serialVersionUID = 1L;

    private static final Object[] NO_PARAMETER_ARRAY = new Object[] {};

    private static final Object[] NULL_PARAM_ARRAY = new Object[] { null };

    private static final Map<Class<?>, Class<?>> wrapperByPrimitiveClasses = buildWrapperByPrimitiveClass();

    private static Map<Class<?>, Class<?>> buildWrapperByPrimitiveClass() {
        Map<Class<?>, Class<?>> wrapperByPrimitiveClasses = new HashMap<Class<?>, Class<?>>();
        wrapperByPrimitiveClasses.put(Integer.TYPE, Integer.class);
        wrapperByPrimitiveClasses.put(Boolean.TYPE, Boolean.class);
        wrapperByPrimitiveClasses.put(Float.TYPE, Float.class);
        wrapperByPrimitiveClasses.put(Double.TYPE, Double.class);
        wrapperByPrimitiveClasses.put(Character.TYPE, Character.class);
        wrapperByPrimitiveClasses.put(Byte.TYPE, Byte.class);
        wrapperByPrimitiveClasses.put(Short.TYPE, Short.class);
        wrapperByPrimitiveClasses.put(Long.TYPE, Long.class);
        return wrapperByPrimitiveClasses;
    }

    private transient Constructor<T> constructor;

    private final Object[] constructionArguments;

    private final Class<T> applyingClass;

    /**
     * @param constructorArguments
     *            if you want the {@link ClassFunction} to be serialized, all
     *            constructorArguments elements should be {@link Serializable}.
     */
    FunkyFunction(Class<T> applyingClass, Object... constructorArguments) {
        this.applyingClass = applyingClass;
        constructor = extractConstructor(applyingClass);
        constructionArguments = extractConstructionArguments(constructor, constructorArguments);
    }

    private void checkNotNull(String paramName, Object param) {
        if (param == null) {
            throw new IllegalArgumentException("The " + paramName + " parameter should not be null");
        }
    }

    private Constructor<T> extractConstructor(Class<T> applyingClass) {
        checkNotNull("applyingClass", applyingClass);
        checkIsInstanciable(applyingClass);

        Constructor<T>[] declaredConstructors = getDeclaredConstructors(applyingClass);

        if (declaredConstructors.length > 1) {
            throw new IllegalArgumentException("The applyingClass should not have more than one constructor");
        }

        Constructor<T> constructor = declaredConstructors[0];

        if (constructor.getExceptionTypes().length > 0) {
            throw new IllegalArgumentException("The applyingClass constructor should not declare throwing any exception");
        }

        if (!constructor.isAccessible()) {
            constructor.setAccessible(true);
        }
        return constructor;
    }

    private void checkIsInstanciable(Class<T> applyingClass) {
        if (Modifier.isAbstract(applyingClass.getModifiers())) {
            throw new IllegalArgumentException("The applyingClass parameter should not be an abstract class, nor an interface");
        }
        if (applyingClass.isEnum()) {
            throw new IllegalArgumentException("The applyingClass parameter should not be an enum");
        }
    }

    @SuppressWarnings("unchecked")
    private Constructor<T>[] getDeclaredConstructors(Class<T> applyingClass) {
        return (Constructor<T>[]) applyingClass.getDeclaredConstructors();
    }

    private Object[] extractConstructionArguments(Constructor<T> constructor, Object[] constructorArguments) {
        checkNotNull("constructorArguments", constructorArguments);

        Class<?>[] parameterTypes = constructor.getParameterTypes();

        if (parameterTypes.length == 0) {
            return NO_PARAMETER_ARRAY;
        } else if (parameterTypes.length == 1 && constructorArguments.length == 0) {
            // Check is inner class
            return NULL_PARAM_ARRAY;
        } else {
            checkNumberOfArguments(constructorArguments, parameterTypes);

            checkArgumentsType(constructorArguments, parameterTypes);

            return constructorArguments;
        }
    }

    private void checkNumberOfArguments(Object[] constructorArguments, Class<?>[] parameterTypes) {
        if (constructorArguments.length != parameterTypes.length) {
            String neededArguments = Arrays.toString(parameterTypes);
            throw new IllegalArgumentException("The constructor arguments array should have " + parameterTypes.length + " arguments, not " + constructorArguments.length + ". Please provide the following argument(s): " + neededArguments);
        }
    }

    private void checkArgumentsType(Object[] constructorArguments, Class<?>[] parameterTypes) {
        int i = 0;
        for (Class<?> parameterType : parameterTypes) {
            Object constructorArgument = constructorArguments[i];

            if (parameterType.isPrimitive()) {
                if (constructorArgument == null) {
                    String neededArguments = Arrays.toString(parameterTypes);
                    throw new IllegalArgumentException("The constructor parameter " + i + " is a " + parameterType.getName() + ", which is a primitive and should not be null. Please provide the following argument(s): " + neededArguments);
                }
                parameterType = wrapperByPrimitiveClasses.get(parameterType);
            }

            if (constructorArgument != null && !parameterType.isInstance(constructorArgument)) {
                String neededArguments = Arrays.toString(parameterTypes);
                throw new IllegalArgumentException("The constructor argument " + i + " should be a " + parameterType.getName() + ", not a " + constructorArgument.getClass().getName() + ". Please provide the following argument(s): " + neededArguments);
            }

            i++;
        }
    }

    public T execute() {
        try {
            return constructor.newInstance(constructionArguments);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            /*
             * Due to constraints on the constructor, cause can only be an
             * unchecked exception.
             */
            if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw ((RuntimeException) cause);
            }
        } catch (Exception e) {
            throw new IllegalStateException("This should never happen. Due to previously checked constraints (see extractConstructor()), no exception may actually be caught here.", e);
        }
    }

    @Override
    public String getClassSimpleName() {
        return constructor.getDeclaringClass().getSimpleName();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        constructor = extractConstructor(applyingClass);
    }

}
