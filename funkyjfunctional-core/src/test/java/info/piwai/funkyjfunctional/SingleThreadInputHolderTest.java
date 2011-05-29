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

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;


public class SingleThreadInputHolderTest extends AbstractInputHolderTest{
    

    @Before
    public void setup() {
        inputHolder = new SingleThreadInputHolder();
    }
    
    @Test
    public void newInputHolderContainsNull() {
        assertNull(inputHolder.get());
    }
    
    @Test
    public void twoThreadsSeeSameValue() throws InterruptedException {
        Object input = new Object();
        inputHolder.set(input);
        
        final Object input2 = new Object();
    
        Thread thread = new Thread() {
            @Override
            public void run() {
                inputHolder.set(input2);
            }
        };
        thread.start();
        
        thread.join();
        
        assertSame(input2, inputHolder.get());
    }
    
    

}
