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

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.junit.After;
import org.junit.Test;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class FunkyTest {

    @Test(expected = UnsupportedOperationException.class)
    public void cannotBeInstanciated() throws Throwable {
        Constructor<?> constructor = Funky.class.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        try {
            constructor.newInstance();
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
    
    @Test
    public void defaultIsThreadSafeFactory() throws Exception {
        FunctionFactory factory = getFactory();
        
        InputHolder inputHolder = factory.getInputHolder();
        
        assertTrue(inputHolder instanceof ThreadSafeInputHolder);
    }
    
    private FunctionFactory getFactory() throws Exception {
        Field factoryField = getFactoryField();
        return (FunctionFactory) factoryField.get(null);
    }

    private Field getFactoryField() throws NoSuchFieldException {
        Field factoryField = Funky.class.getDeclaredField("factory");
        factoryField.setAccessible(true);
        return factoryField;
    }
    
    @Test
    public void switchToSingleThreadFactory() throws Exception {
        Funky.setThreadSafeInput(false);
        
        FunctionFactory factory = getFactory();
        
        InputHolder inputHolder = factory.getInputHolder();
        
        assertTrue(inputHolder instanceof SingleThreadInputHolder);
    }
    
    @Test
    public void doubleSwitchToThreadSafeFactory() throws Exception {
        Funky.setThreadSafeInput(false);
        Funky.setThreadSafeInput(true);
        
        FunctionFactory factory = getFactory();
        
        InputHolder inputHolder = factory.getInputHolder();
        
        assertTrue(inputHolder instanceof ThreadSafeInputHolder);
    }
    
    @Test
    public void getInputDelegatesToFactoryInputHolder() throws Exception {
        FunctionFactory factory = getFactory();
        
        Object input = new Object();
        factory.getInputHolder().set(input);
        
        assertSame(input, Funky.getInput());
    }
    
    @Test
    public void newFunctionDelegatesToFactory() {
        class Instantiated {}
        
        ClassFunction<Instantiated> classFunction = Funky.newFunction(Instantiated.class);

        assertTrue(classFunction instanceof FunkyFunction);
    }

    @Test
    public void newFunctionWithInputReturnsFunkyFunctionWithInput() {
        class Instantiated {}

        ClassFunctionWithInput<Instantiated> classFunction = Funky.newFunctionWithInput(Instantiated.class);

        assertTrue(classFunction instanceof FunkyFunctionWithInput);
    }
    
    
    @After
    public void cleanup() {
        Funky.setThreadSafeInput(true);
    }
    

}
