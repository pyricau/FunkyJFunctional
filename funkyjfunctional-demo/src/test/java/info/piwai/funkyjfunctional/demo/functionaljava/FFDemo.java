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
package info.piwai.funkyjfunctional.demo.functionaljava;

import static fj.Function.*;
import static fj.data.Array.array;
import static fj.data.List.*;
import static info.piwai.funkyjfunctional.functionaljava.FunkyFunctionalJava.*;
import static java.lang.Character.*;
import static org.junit.Assert.*;
import fj.F;
import fj.data.Array;
import fj.data.List;
import fj.function.Integers;
import info.piwai.funkyjfunctional.functionaljava.FF;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class FFDemo {

    @Test
    public void lowercaseCompositionDemo() {

        // @off
        class IsLowerCase extends FF<Character, Boolean> {{ out = isLowerCase(in); }};
        // @on

        // @off
        class IsStringLowerCase extends FF<String, Boolean> {{ out = fromString(in).forall(withF(IsLowerCase.class)); }};
        // @on

        final Array<String> a = array("Hello", "There", "what", "DAY", "iS", "iT");
        
        boolean arrayContainsALowerCaseString = a.exists(withF(IsStringLowerCase.class));

        assertTrue("true ( [what] provides the only example; try removing it)", arrayContainsALowerCaseString);
        
    }
    
    @Test
    public void numbers() {
        
        F<Integer, Integer> plusOne = Integers.add.f(1);
        
        class TimesTwo extends FF<Integer, Integer> {{ out = in * 2; }};
        
        F<Integer, Integer> timesTwoPlusOne = compose(plusOne, withF(TimesTwo.class));
        
        assertEquals(5, (int) timesTwoPlusOne.f(2));
        
    }
    
    @Test
    public void testFactors() {
        assertEquals(Arrays.asList(1, 2, 3, 4, 6, 12), new ArrayList<Integer>(factors(12).toCollection()));
    }
    
    public static List<Integer> factors(final int number) {
        class Factor extends FF<Integer, Boolean> {{ out = number % in == 0; }}
        return range(1, number + 1).filter(withF(Factor.class, number));
    }

}
