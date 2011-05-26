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
package info.piwai.funkyjfunctional.festassert.apitest;

import static info.piwai.funkyjfunctional.festassert.FunkyFestAssert.withDesc;
import static org.junit.Assert.assertEquals;
import info.piwai.funkyjfunctional.festassert.Desc;

import org.fest.assertions.Description;
import org.junit.Test;

/**
 * @author Nicolas Francois (nicolas.franc at gmail.com)
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class DescTest {

    @Test
    public void values(){
        // @off
        class NotEquals extends Desc {{ out = "We're not equals";}};
        // @on
        Description description = withDesc(NotEquals.class);
        
        assertEquals("We're not equals", description.value());
    };

}
