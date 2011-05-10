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
package info.piwai.funkyjfunctional.swing.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static info.piwai.funkyjfunctional.swing.FunkySwing.withActL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import info.piwai.funkyjfunctional.swing.ActL;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class ActLTest {
	
    static int counter;

    @Before
    public void setup() {
        counter = 0;
    }

    @Test
    public void executed() {
        // @off
        class Increment extends ActL{{ counter++; }}
        // @on

       ActionListener listener = withActL(Increment.class);

        assertEquals(0, counter);
        listener.actionPerformed(null);
        assertEquals(1, counter);
    }

    @Test
    public void paramTransmitted() {
    	ActionEvent event = mock(ActionEvent.class);
    	
        // @off
        class Increment extends ActL{{ e.getActionCommand(); }}
        // @on

        withActL(Increment.class).actionPerformed(event);

        verify(event).getActionCommand();
    }

    
}
