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
import fj.F2;
import info.piwai.funkyjfunctional.functionaljava.FF2;

import org.junit.Test;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class FF2Test {

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
        class ReturnsResult extends FF2<Object, Object, Integer> {{ out = 42; }}
        // @on

        F2<Object, Object, Integer> function = withF2(ReturnsResult.class);

        assertEquals((Integer) 42, function.f(null, null));
    }

    public void returnsInput() {
        // @off
        class IdentityA extends FF2<Object, Object, Object> {{ out = inA; }}
        class IdentityB extends FF2<Object, Object, Object> {{ out = inB; }}
        // @on

        F2<Object, Object, Object> identityA = withF2(IdentityA.class);
        F2<Object, Object, Object> identityB = withF2(IdentityB.class);

        Object input = new Object();

        assertSame(input, identityA.f(input, null));
        assertSame(input, identityB.f(null, input));
    }
}
