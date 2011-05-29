package info.piwai.funkyjfunctional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public abstract class AbstractInputHolderTest {
    
    protected InputHolder inputHolder;
    
    @Test
    public void objectSetIsReturned() {
        Object input = new Object();
        inputHolder.set(input);
        assertSame(input, inputHolder.get());
    }
    
    @Test
    public void returnsNullAfterClean() {
        Object input = new Object();
        inputHolder.set(input);
        assertNotNull(inputHolder.get());
        
        inputHolder.clean();
        assertNull(inputHolder.get());
    }

}
