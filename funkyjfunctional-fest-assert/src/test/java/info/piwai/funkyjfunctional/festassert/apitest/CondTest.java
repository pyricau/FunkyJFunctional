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
package info.piwai.funkyjfunctional.festassert.apitest;

import static info.piwai.funkyjfunctional.festassert.FunkyFestAssert.withCond;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import info.piwai.funkyjfunctional.festassert.Cond;

import org.fest.assertions.Condition;
import org.junit.Test;

/**
 * @author Nicolas Francois (nicolas.franc at gmail.com)
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class CondTest {

    @Test
    public void simplePositiveCondition() {

        // @off
        class Positive extends Cond<Integer> {{ r = t > 0; }}
        // @on

        Condition<Integer> condition = withCond(Positive.class);

        assertTrue(condition.matches(1));
        assertFalse(condition.matches(-1));
    }
    
    @Test
    public void noDescriptionBeforeMatches() {
        // @off
        class AlwaysFalse extends Cond<Object> {{ r = false; }};
        // @on

        Condition<Object> condition = withCond(AlwaysFalse.class);
        
        assertNull(condition.description());
    }

    @Test
    public void defaultDescriptionAfterMatches() {
        // @off
        class AlwaysFalse extends Cond<Object> {{ r = false; }};
        // @on

        Condition<Object> condition = withCond(AlwaysFalse.class);
        
        condition.matches(null);

        assertEquals("AlwaysFalse", condition.description());
    }

    @Test
    public void customDescriptionAfterMatches() {

        // @off
        class AlwaysFalse extends Cond<Object> {{ d="I'm always false!"; r = false; }};
        // @on

        Condition<Object> condition = withCond(AlwaysFalse.class);
        
        condition.matches(null);

        assertEquals("I'm always false!", condition.description());
    }
    
    @Test
    public void overrideDefaultDescription() {
        // @off
        class AlwaysFalse extends Cond<Object> {{ r = false; }};
        // @on

        Condition<Object> condition = withCond(AlwaysFalse.class);
        
        condition.matches(null);
        
        condition.as("Overrides");

        assertEquals("Overrides", condition.description());
    }

    @Test
    public void overrideCustomDescription() {
        // @off
        class AlwaysFalse extends Cond<Object> {{ d="I'm always false!"; r = false; }};
        // @on

        Condition<Object> condition = withCond(AlwaysFalse.class);
        
        condition.matches(null);
        
        condition.as("Overrides");

        assertEquals("Overrides", condition.description());
    }
    
}
