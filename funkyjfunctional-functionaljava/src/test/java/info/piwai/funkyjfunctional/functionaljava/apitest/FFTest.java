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
package info.piwai.funkyjfunctional.functionaljava.apitest;

import static info.piwai.funkyjfunctional.functionaljava.FunkyFunctionalJava.*;
import static org.junit.Assert.*;
import fj.F;
import info.piwai.funkyjfunctional.functionaljava.FF;

import org.junit.Test;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class FFTest {

    static class Person {
        final String firstName;
        final String lastName;

        Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    @Test
    public void returnsResult() {
        // @off
        class ReturnsResult extends FF<Object, Integer> {{ out = 42; }}
        // @on

        F<Object, Integer> function = withF(ReturnsResult.class);

        assertEquals((Integer) 42, function.f(null));
    }

    public void returnsInput() {
        // @off
        class Identity extends FF<Object, Object> {{ out = in; }}
        // @on

        F<Object, Object> function = withF(Identity.class);

        Object input = new Object();

        assertSame(input, function.f(input));
    }
}
