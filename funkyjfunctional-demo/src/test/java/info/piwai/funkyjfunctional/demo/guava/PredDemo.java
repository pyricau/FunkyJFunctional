package info.piwai.funkyjfunctional.demo.guava;
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


import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Lists.newArrayList;
import static info.piwai.funkyjfunctional.guava.FunkyGuava.withPred;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import info.piwai.funkyjfunctional.guava.Pred;

import java.util.List;

import org.junit.Test;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class PredDemo {

    @Test
    public void filtering() {
        User minor = new User(16);
        User major = new User(21);
        List<User> users = asList(minor, major);

        // @off
        class Major extends Pred<User> {{ r = t.age >= 18; }}
        // @on

        List<User> majors = newArrayList(filter(users, withPred(Major.class)));

        assertEquals(asList(major), majors);
    }
}
