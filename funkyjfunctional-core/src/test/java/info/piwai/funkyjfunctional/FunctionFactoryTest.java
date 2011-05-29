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
import static org.mockito.Mockito.mock;

import org.junit.Test;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class FunctionFactoryTest {

    @Test
    public void newFunctionReturnsFunkyFunction() {

        FunctionFactory funkyFactory = new FunctionFactory(null);

        class Instantiated {}
        
        ClassFunction<Instantiated> classFunction = funkyFactory.newFunction(Instantiated.class);

        assertTrue(classFunction instanceof FunkyFunction);
    }

    @Test
    public void newFunctionWithInputReturnsFunkyFunctionWithInput() {

        FunctionFactory funkyFactory = new FunctionFactory(null);

        class Instantiated {}

        ClassFunctionWithInput<Instantiated> classFunction = funkyFactory.newFunctionWithInput(Instantiated.class);

        assertTrue(classFunction instanceof FunkyFunctionWithInput);
    }
    
    @Test
    public void getInputHolderReturnsGivenInputHolder() {
        InputHolder inputHolder = mock(InputHolder.class);
        FunctionFactory funkyFactory = new FunctionFactory(inputHolder);
        assertSame(inputHolder, funkyFactory.getInputHolder());
    }
}
