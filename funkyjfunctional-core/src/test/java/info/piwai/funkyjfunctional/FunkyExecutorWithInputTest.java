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

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class FunkyExecutorWithInputTest {

    private ClassExecutor<Object> executor;
    private ClassExecutorWithInput<Object> executorWithInput;

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
        final Object parameter = new Object();
        when(executor.createExecutedInstance()).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                assertSame(parameter, FunkyExecutorWithInput.getThreadLocalParameter());
                return null;
            }
        });
        executorWithInput.createExecutedInstance(parameter);
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

    @Test
    public void simpleExecutorWithInputDoesNotThrow() {
        new FunkyExecutorWithInput<Object>(executor);
    }

    @Test
    public void getClassSimpleNameDelegatesToClassExecutor() {
        String classSimpleName = "classSimpleName";
        when(executor.getClassSimpleName()).thenReturn(classSimpleName);
        assertSame(classSimpleName, executorWithInput.getClassSimpleName());
        verify(executor).getClassSimpleName();
    }

}
