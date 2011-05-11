package info.piwai.funkyjfunctional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


public class FunkyTest {
    
    @Test
    public void classExecutorReturnsFunkyExecutor() {
        // @off
        class Instantiated {}
        // @on
        ClassExecutor<Instantiated> classExecutor = Funky.classExecutor(Instantiated.class);
        
        assertTrue(classExecutor instanceof FunkyExecutor);
    }
    
    @Test
    public void classExecutorWithInputReturnsFunkyExecutorWithInput() {
        // @off
        class Instantiated {}
        // @on
        ClassExecutorWithInput<Instantiated> classExecutor = Funky.classExecutorWithInput(Instantiated.class);
        
        assertTrue(classExecutor instanceof FunkyExecutorWithInput);
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void getThreadLocalParameterDelegatesToFunkyExecutorWithInput() {
        
        ClassExecutor<Object> executor = mock(ClassExecutor.class);
        ClassExecutorWithInput<Object> executorWithInput = new FunkyExecutorWithInput<Object>(executor);
        
        final Object parameter = new Object();
        when(executor.createExecutedInstance()).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                assertSame(parameter, Funky.getThreadLocalParameter());
                return null;
            }
        });
        executorWithInput.createExecutedInstance(parameter);
    }

}
