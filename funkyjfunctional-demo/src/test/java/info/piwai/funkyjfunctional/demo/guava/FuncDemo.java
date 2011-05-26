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

import static com.google.common.collect.Lists.transform;
import static info.piwai.funkyjfunctional.guava.FunkyGuava.withFunc;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import info.piwai.funkyjfunctional.guava.Func;

import java.util.List;

import org.junit.Test;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class FuncDemo {

    static class Person {
        final String firstName;
        final String lastName;

        Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    @Test
    public void transforming() {
        Person johnSmith = new Person("John", "Smith");
        Person joeBar = new Person("Joe", "Bar");
        List<Person> persons = asList(johnSmith, joeBar);

        // @off
		class FullName extends Func<Person, String> {{ out = in.firstName + " " + in.lastName; }}
		// @on

        List<String> fullNames = transform(persons, withFunc(FullName.class));

        assertEquals(asList("John Smith", "Joe Bar"), fullNames);
    }
}
