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
package info.piwai.funkyjfunctional.festassert;

import org.junit.Test;
import java.util.List;

import static info.piwai.funkyjfunctional.festassert.FunkyFestAssert.withCond;
import static java.util.Arrays.asList;
import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Nicolas Francois (nicolas.franc at gmail.com)
 */
public class CondTest{

    @Test
    public void condition() {

        // @off
        class Adult extends Cond<Integer> {{ r = t > 18; }}
        // @on

        assertThat(20).satisfies(withCond(Adult.class)).is(withCond(Adult.class));
        assertThat(5).doesNotSatisfy(withCond(Adult.class)).isNot(withCond(Adult.class));

    }


    @Test
    public void conditionOnList() {
        List<Integer> evenList = asList(16, 21);
        List<Integer> oddList = asList(16, 21, 38);
        // @off
        class EvenSize extends Cond<List<?>> {{ r = t != null && t.size() %2 == 0; }};
        // @on
        assertThat(evenList).satisfies(withCond(EvenSize.class)).is(withCond(EvenSize.class));
        assertThat(oddList).doesNotSatisfy(withCond(EvenSize.class)).isNot(withCond(EvenSize.class));

    }

}
