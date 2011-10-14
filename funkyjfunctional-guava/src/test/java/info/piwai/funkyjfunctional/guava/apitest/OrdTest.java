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
package info.piwai.funkyjfunctional.guava.apitest;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import info.piwai.funkyjfunctional.guava.Ord;
import org.junit.Test;

import static info.piwai.funkyjfunctional.guava.FunkyGuava.withOrd;
import static org.junit.Assert.assertEquals;

/**
 * @author Nicolas Francois (nicolas.franc at gmail.com)
 */
public class OrdTest {

    @Test
    public void filtering() {
        // @off
		class AscOrder extends Ord<Integer> {{ out = left.compareTo(right); }}
		// @on

        Ordering<Integer> ordering = withOrd(AscOrder.class);

        Iterable<Integer> numbers = Lists.newArrayList(2, 3, 1, 5, 10, 8);

        assertEquals(new Integer(1), ordering.min(numbers)) ;
        assertEquals(new Integer(10), ordering.max(numbers)) ;
    }
}
