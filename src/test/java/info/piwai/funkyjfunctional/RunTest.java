package info.piwai.funkyjfunctional;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class RunTest {
    
    int counter;
    static int staticCounter;
    
    @Before
    public void setup() {
        counter = 0;
        staticCounter = 0;
    }
    
    @Test
    public void runWithInstance() {
        class RunnableIncrement {{counter++;}}
        
        Runnable runnable = Run.with(RunnableIncrement.class, this);
        
        runnable.run();
        
        assertEquals(1, counter);
    }
    
    @Test
    public void runWithoutInstance() {
        class RunnableIncrement {{staticCounter++;}}
        
        Runnable runnable = Run.with(RunnableIncrement.class);
        
        runnable.run();
        
        assertEquals(1, staticCounter);
    }
    
    
    @Test
    public void runFromStaticTest() {
        runFromStatic();
    }
    
    public static void runFromStatic() {
        class RunnableIncrement {{staticCounter++;}}
        
        Runnable runnable = Run.with(RunnableIncrement.class);
        
        runnable.run();
        
        assertEquals(1, staticCounter);
    }

}
