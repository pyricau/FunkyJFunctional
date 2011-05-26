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
package info.piwai.funkyjfunctional.java.apitest;

import static info.piwai.funkyjfunctional.java.FunkyJava.*;
import static org.junit.Assert.*;

import java.util.Comparator;

import info.piwai.funkyjfunctional.java.Comp;

import org.junit.Test;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class CompTest {

    @Test
    public void ordering() {
        
        // @off
        class IntCompt extends Comp<Integer> {{ r = t1.compareTo(t2); }}
        // @on
        
        Comparator<Integer> comp = withComp(IntCompt.class);
        
        assertEquals(Integer.valueOf(1).compareTo(2), comp.compare(1, 2));
        assertEquals(Integer.valueOf(2).compareTo(1), comp.compare(2, 1));
        assertEquals(Integer.valueOf(1).compareTo(1), comp.compare(1, 1));
    }

}
