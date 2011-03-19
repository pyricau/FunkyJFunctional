/*
 * Copyright 2011 Pierre-Yves Ricau (py.ricau at gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package info.piwai.funkyjfunctional.apitest;

import static info.piwai.funkyjfunctional.Funky.withRun;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class RunTest {
    
    int counter;
    static int staticCounter;
    
    @Before
    public void setup() {
        counter = 0;
        staticCounter = 0;
    }
    
    @Test
    public void runWithInstance() {
        class RunnableIncrement {{counter++;}}
        
        Runnable runnable = withRun(RunnableIncrement.class, this);
        
        runnable.run();
        
        assertEquals(1, counter);
    }
    
    @Test
    public void runWithoutInstance() {
        class RunnableIncrement {{staticCounter++;}}
        
        Runnable runnable = withRun(RunnableIncrement.class);
        
        runnable.run();
        
        assertEquals(1, staticCounter);
    }
    
    
    @Test
    public void runFromStaticTest() {
        runFromStatic();
    }
    
    public static void runFromStatic() {
        class RunnableIncrement {{staticCounter++;}}
        
        Runnable runnable = withRun(RunnableIncrement.class);
        
        runnable.run();
        
        assertEquals(1, staticCounter);
    }

}
