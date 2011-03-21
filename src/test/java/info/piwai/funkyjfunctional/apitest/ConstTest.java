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

import static info.piwai.funkyjfunctional.Funky.withConst;
import info.piwai.funkyjfunctional.Const;

import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Constraints;
import com.google.common.collect.Lists;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class ConstTest {

    @Test(expected = IllegalArgumentException.class)
    public void invalidInput() {

        // @off
        class NotFortyTwo extends Const<String> {{ if("42".equals(t)) invalid("Should not be 42!"); }}
        // @on

        List<String> list = Lists.newArrayList();

        Collection<String> strings = Constraints.constrainedCollection(list, withConst(NotFortyTwo.class));

        strings.add("42");
    }

    @Test
    public void validInput() {

        // @off
        class NotFortyTwo extends Const<String> {{ if("42".equals(t)) invalid("Should not be 42!"); }}
        // @on

        List<String> list = Lists.newArrayList();

        Collection<String> strings = Constraints.constrainedCollection(list, withConst(NotFortyTwo.class));

        strings.add("43");
    }

}
