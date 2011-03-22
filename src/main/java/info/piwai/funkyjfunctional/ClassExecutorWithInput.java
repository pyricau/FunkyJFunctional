package info.piwai.funkyjfunctional;

interface ClassExecutorWithInput<T> {

    T createExecutedInstance(Object parameter);
    
    String getClassSimpleName();

}