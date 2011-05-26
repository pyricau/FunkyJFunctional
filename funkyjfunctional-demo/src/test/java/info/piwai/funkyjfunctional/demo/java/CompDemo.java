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
package info.piwai.funkyjfunctional.demo.java;

import static info.piwai.funkyjfunctional.java.FunkyJava.withComp;
import static java.util.Arrays.asList;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;
import info.piwai.funkyjfunctional.java.Comp;

import java.util.List;

import org.junit.Test;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class CompDemo {

    @Test
    public void ordering() {
        Person john = new Person("John");
        Person joe = new Person("Joe");
        
        List<Person> persons = asList(john, joe);

        // @off
        class Sort extends Comp<Person> {{ out = in1.getName().compareTo(in2.getName()); }}
        // @on
        
        sort(persons, withComp(Sort.class));

        assertEquals(asList(joe, john), persons);
    }

}
