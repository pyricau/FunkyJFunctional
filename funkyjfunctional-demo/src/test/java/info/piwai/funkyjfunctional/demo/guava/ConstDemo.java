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
package info.piwai.funkyjfunctional.demo.guava;

import static com.google.common.collect.Constraints.constrainedList;
import static info.piwai.funkyjfunctional.guava.FunkyGuava.withConst;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import info.piwai.funkyjfunctional.guava.Const;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Constraint;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class ConstDemo {

    @Test(expected = IllegalArgumentException.class)
    public void invalidInputThrows() {
        // @off
        class NotFortyTwo extends Const<String> {{ if ("42".equals(in)) invalid("Should not be 42!"); }}
        // @on

        List<String> no42List = constrainedList(new ArrayList<String>(), withConst(NotFortyTwo.class));

        no42List.add("42");
    }

    @Test
    public void validInputAccepted() {
        // @off
        class NotFortyTwo extends Const<String> {{ if ("42".equals(in)) invalid("Should not be 42!"); }}
        // @on

        List<String> no42List = constrainedList(new ArrayList<String>(), withConst(NotFortyTwo.class));

        no42List.add("43");

        assertTrue(no42List.contains("43"));
    }

    @Test
    public void defaultReturnsSame() {
        // @off
        class Void extends Const<String> {{}}
        // @on

        String input = "43";
        String returned = withConst(Void.class).checkElement(input);
        assertSame(input, returned);
    }

    @Test
    public void changesInput() {
        // @off
        class AddSlash extends Const<String> {{ out = in.startsWith("/") ? in : "/" + in; }}
        // @on

        Constraint<String> euroConstraint = withConst(AddSlash.class);

        assertEquals("/path", euroConstraint.checkElement("path"));

    }

    @Test
    public void constraintIsNamed() {
        // @off
        class SomeName extends Const<String> {{}}
        // @on

        String toStringValue = withConst(SomeName.class).toString();

        assertEquals("SomeName constraint", toStringValue);
    }

}
