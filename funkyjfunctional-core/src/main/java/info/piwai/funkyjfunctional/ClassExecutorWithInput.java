package info.piwai.funkyjfunctional;

public interface ClassExecutorWithInput<T> {

    T createExecutedInstance(Object parameter);
    
    String getClassSimpleName();

}