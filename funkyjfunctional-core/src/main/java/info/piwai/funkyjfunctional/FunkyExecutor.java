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

    private static final Object[] NULL_PARAM_ARRAY = new Object[] { null };

    private final Constructor<T> constructor;

    private final Object[] constructionArguments;

    FunkyExecutor(Class<T> applyingClass, Object... constructorArguments) {
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
        checkNotAbstract(applyingClass);

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

    private void checkNotAbstract(Class<T> applyingClass) {
        if (Modifier.isAbstract(applyingClass.getModifiers())) {
            throw new IllegalArgumentException("The applyingClass parameter should not be abstract");
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
            throw new IllegalStateException("This should never happen. Due to previously checked constraints (see extractConstructor()), no exception may actually be caught here.", e);
        }
    }

    @Override
    public String getClassSimpleName() {
        return constructor.getDeclaringClass().getSimpleName();
    }
}
