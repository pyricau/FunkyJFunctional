package info.piwai.funkyjfunctional;

abstract class FunkyExecutorWithInput<T> extends FunkyExecutor<T> {

    private static ThreadLocal<Object> holder = new ThreadLocal<Object>();

    @SuppressWarnings("unchecked")
    static <T> T getThreadLocalParameter() {
        return (T) holder.get();
    }

    FunkyExecutorWithInput(Class<? extends T> applyingClass) {
        this(applyingClass, null);
    }

    FunkyExecutorWithInput(Class<? extends T> applyingClass, Object instance) {
        super(applyingClass, instance);
    }

    T createExecutedInstance(Object parameter) {
        holder.set(parameter);
        try {
            return createExecutedInstance();
        } finally {
            holder.set(null);
        }
    }
}
