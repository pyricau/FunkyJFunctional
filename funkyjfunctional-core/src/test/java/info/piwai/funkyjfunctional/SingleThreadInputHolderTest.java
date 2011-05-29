package info.piwai.funkyjfunctional;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;


public class SingleThreadInputHolderTest extends AbstractInputHolderTest{
    

    @Before
    public void setup() {
        inputHolder = new SingleThreadInputHolder();
    }
    
    @Test
    public void newInputHolderContainsNull() {
        assertNull(inputHolder.get());
    }
    
    @Test
    public void twoThreadsSeeSameValue() throws InterruptedException {
        Object input = new Object();
        inputHolder.set(input);
        
        final Object input2 = new Object();
    
        Thread thread = new Thread() {
            @Override
            public void run() {
                inputHolder.set(input2);
            }
        };
        thread.start();
        
        thread.join();
        
        assertSame(input2, inputHolder.get());
    }
    
    

}
