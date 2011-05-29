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
public class FunkyFunctionWithInputTest {

    private ClassFunction<Object> function;
    private ClassFunctionWithInput<Object> functionWithInput;
    
    private InputHolder inputHolder = new SingleThreadInputHolder();

    @SuppressWarnings("unchecked")
    @Before
    public void setup() {
        function = mock(ClassFunction.class);
        functionWithInput = new FunkyFunctionWithInput<Object>(function, inputHolder);
    }

    @Test
    public void delegatesCall() {
        functionWithInput.execute(null);
        verify(function).execute();
    }

    @Test
    public void inputStoredInInputHolder() {
        final Object parameter = new Object();
        when(function.execute()).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                assertSame(parameter, inputHolder.get());
                return null;
            }
        });
        functionWithInput.execute(parameter);
    }

    @Test
    public void inputHolderCleaned() {
        assertNull(inputHolder.get());
        when(function.execute()).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                assertNotNull(inputHolder.get());
                return null;
            }
        });
        functionWithInput.execute(new Object());
        assertNull(inputHolder.get());
    }

    @Test
    public void simpleExecutorWithInputDoesNotThrow() {
        new FunkyFunctionWithInput<Object>(function, inputHolder);
    }

    @Test
    public void getClassSimpleNameDelegatesToClassExecutor() {
        String classSimpleName = "classSimpleName";
        when(function.getClassSimpleName()).thenReturn(classSimpleName);
        assertSame(classSimpleName, functionWithInput.getClassSimpleName());
        verify(function).getClassSimpleName();
    }

}
