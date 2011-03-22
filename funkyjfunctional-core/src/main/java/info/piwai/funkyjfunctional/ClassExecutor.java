package info.piwai.funkyjfunctional;

public interface ClassExecutor<T> {
    T createExecutedInstance();
    
    String getClassSimpleName();
}