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

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
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
