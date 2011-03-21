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
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * <p>
 * This class holds FunkyJFunctional's core behavior. It creates instances of
 * the given class each time its init block code needs to be executed.
 * 
 * <p>
 * {@link FunkyExecutor} is not part of the API, which is why it has
 * package-private scope.
 * 
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
final class FunkyExecutor<T> implements ClassExecutor<T> {

    private static final Object[] NO_PARAMETER_ARRAY = new Object[] {};

    private final Constructor<T> constructor;

    private final Object[] constructionArguments;

    FunkyExecutor(Class<T> applyingClass, Object... constructorArguments) {
        checkNotAbstract(applyingClass);
        constructor = extractConstructor(applyingClass);
        constructionArguments = extractConstructionParameters(constructor, constructorArguments);
    }

    private void checkNotAbstract(Class<T> applyingClass) {
        if (Modifier.isAbstract(applyingClass.getModifiers())) {
            throw new IllegalArgumentException("The applyingClass should not be abstract");
        }
    }

    @SuppressWarnings("unchecked")
    private Constructor<T> extractConstructor(Class<T> applyingClass) {
        Constructor<T>[] declaredConstructors = (Constructor<T>[]) applyingClass.getDeclaredConstructors();

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

    private Object[] extractConstructionParameters(Constructor<T> constructor, Object[] constructorArguments) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();

        if (parameterTypes.length == 0) {
            return NO_PARAMETER_ARRAY;
        } else {
            checkNullArgumentArray(constructorArguments, parameterTypes);

            checkNumberOfArguments(constructorArguments, parameterTypes);

            checkArgumentsType(constructorArguments, parameterTypes);

            return constructorArguments;
        }
    }

    private void checkNullArgumentArray(Object[] constructorArguments, Class<?>[] parameterTypes) {
        if (constructorArguments == null) {
            String neededArguments = Arrays.toString(parameterTypes);
            throw new IllegalArgumentException("The constructor arguments array should not be null. Please provide the following argument(s): " + neededArguments);
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
            if (constructorArgument != null && !parameterType.isInstance(constructorArgument)) {
                String neededArguments = Arrays.toString(parameterTypes);
                throw new IllegalArgumentException("The constructor argument " + i + " should be a " + parameterType.getName() + ". Please provide the following argument(s): " + neededArguments);
            }
            i++;
        }
    }

    public T createExecutedInstance() {
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
            /*
             * SHOULD NEVER HAPPEN.
             * 
             * Due to previously checked constraints, no exception may actually
             * be caught here.
             */
            throw new RuntimeException(e);
        }
    }
}
