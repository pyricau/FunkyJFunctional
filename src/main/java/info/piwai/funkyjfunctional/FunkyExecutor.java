package info.piwai.funkyjfunctional;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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
