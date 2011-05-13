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

import static info.piwai.funkyjfunctional.guava.FunkyGuava.withSupp;
import static org.junit.Assert.assertEquals;
import info.piwai.funkyjfunctional.guava.Supp;

import org.junit.Test;

import com.google.common.base.Supplier;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class SuppTest {

    @Test
    public void supplierReturnsFortyTwo() {
        // @off
        class FortyTwo extends Supp<String> {{ r = "42"; }}
        // @on

        Supplier<String> supplier = withSupp(FortyTwo.class);

        assertEquals("42", supplier.get());
    }

}
