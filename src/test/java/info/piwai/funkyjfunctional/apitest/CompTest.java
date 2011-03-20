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

import static com.google.common.collect.Ordering.from;
import static info.piwai.funkyjfunctional.Funky.withComp;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import info.piwai.funkyjfunctional.Comp;

import java.util.List;

import org.junit.Test;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class CompTest {
    
    public static class Person {
        private final String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    @Test
    public void testOrdering() throws Exception {
        Person john = new Person("John");
        Person joe = new Person("Joe");

        class Sort extends Comp<Person> {{ r = t1.getName().compareTo(t2.getName()); }}

        List<Person> sortedPersons = from(withComp(Sort.class)).sortedCopy(asList(john, joe));

        assertEquals(asList(joe, john), sortedPersons);
    }

    @Test
    public void testStaticOrdering() {
        staticOrdering();
    }

    private static void staticOrdering() {
        Person john = new Person("John");
        Person joe = new Person("Joe");

        class Sort extends Comp<Person> {{ r = t1.getName().compareTo(t2.getName()); }}

        List<Person> sortedPersons = from(withComp(Sort.class)).sortedCopy(asList(john, joe));
        
        assertEquals(asList(joe, john), sortedPersons);
    }
    
    @Test(expected=ArithmeticException.class)
    public void testThrows() {
        class Fails extends Comp<Object> {{r = 42 / 0;}}
        withComp(Fails.class).compare(null, null);
    }

}
