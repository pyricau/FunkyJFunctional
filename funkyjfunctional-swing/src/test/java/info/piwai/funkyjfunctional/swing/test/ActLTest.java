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
