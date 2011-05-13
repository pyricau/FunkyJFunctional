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

import static info.piwai.funkyjfunctional.festassert.FunkyIterableAssert.assertThat;
import static org.junit.Assert.*;

import info.piwai.funkyjfunctional.festassert.Cond;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class IterableAssertTest {

    @Test
    public void eachSatisfies() {
        // @off
       class Adult extends Cond<Integer> {{ r = t >= 18; }}
       // @on

        assertThat(Arrays.asList(19, 21, 69)).eachSatisfies(Adult.class);
    }
    
    @Test
    public void eachIs() {
        // @off
       class Adult extends Cond<Integer> {{ r = t >= 18; }}
       // @on

        assertThat(Arrays.asList(19, 21, 69)).eachIs(Adult.class);
    }

    @Test
    public void eachSatisfiesThrowsWhenOneDoesNot() {
        // @off
       class Adult extends Cond<Integer> {{ r = t >= 18; }}
       // @on

        try {
            assertThat(Arrays.asList(17, 21, 69)).eachSatisfies(Adult.class);
        } catch (AssertionError e) {
            return;
        }
        fail("Assertion should have thrown an AssertionError");
    }

    @Test
    public void noneSatisfies() {
        // @off
       class Adult extends Cond<Integer> {{ r = t >= 18; }}
       // @on

        assertThat(Arrays.asList(14, -2, 17)).noneSatisfies(Adult.class);
    }
    
    @Test
    public void noneIs() {
        // @off
       class Adult extends Cond<Integer> {{ r = t >= 18; }}
       // @on

        assertThat(Arrays.asList(14, -2, 17)).noneIs(Adult.class);
    }

    @Test
    public void noneSatisfiesThrowsWhenOneDoes() {
        // @off
       class Adult extends Cond<Integer> {{ r = t >= 18; }}
       // @on

        try {
            assertThat(Arrays.asList(14, -2, 18)).noneSatisfies(Adult.class);
        } catch (AssertionError e) {
            return;
        }
        fail("Assertion should have thrown an AssertionError");
    }

}
