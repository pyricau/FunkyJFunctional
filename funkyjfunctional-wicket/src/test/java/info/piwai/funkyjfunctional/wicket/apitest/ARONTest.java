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
package info.piwai.funkyjfunctional.wicket.apitest;

import static info.piwai.funkyjfunctional.wicket.FunkyWicket.withARON;
import static org.junit.Assert.assertEquals;
import info.piwai.funkyjfunctional.wicket.ARON;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.junit.Test;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class ARONTest {
	
    @Test
    public void callableReturnsFortyTwo() throws Exception {
        // @off
        class FortyTwo extends ARON<String> {{ out = "42"; }}
        // @on

        AbstractReadOnlyModel<String> aron = withARON(FortyTwo.class);

        assertEquals("42",  aron.getObject());
    }


}
