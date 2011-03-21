package info.piwai.funkyjfunctional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class FunkyExecutorWithInputTest {
    
    private ClassExecutor<Object> executor;
    private FunkyExecutorWithInput<Object> executorWithInput;

    @SuppressWarnings("unchecked")
    @Before
    public void setup() {
        executor = mock(ClassExecutor.class);
        executorWithInput = new FunkyExecutorWithInput<Object>(executor);
    }

    @Test
    public void delegatesCall() {
        executorWithInput.createExecutedInstance(null);
        verify(executor).createExecutedInstance();
    }
    
    @Test
    public void inputStoredInThreadLocal() {
        when(executor.createExecutedInstance()).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                assertNotNull(FunkyExecutorWithInput.getThreadLocalParameter());
                return null;
            }
        });
        executorWithInput.createExecutedInstance(new Object());
    }
    
    @Test
    public void threadLocalCleaned() {
        assertNull(FunkyExecutorWithInput.getThreadLocalParameter());
        when(executor.createExecutedInstance()).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                assertNotNull(FunkyExecutorWithInput.getThreadLocalParameter());
                return null;
            }
        });
        executorWithInput.createExecutedInstance(new Object());
        assertNull(FunkyExecutorWithInput.getThreadLocalParameter());
    }

}
