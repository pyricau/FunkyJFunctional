package info.piwai.funkyjfunctional.apitest;

import static info.piwai.funkyjfunctional.Funky.withRun;
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
        
        Runnable runnable = withRun(RunnableIncrement.class, this);
        
        runnable.run();
        
        assertEquals(1, counter);
    }
    
    @Test
    public void runWithoutInstance() {
        class RunnableIncrement {{staticCounter++;}}
        
        Runnable runnable = withRun(RunnableIncrement.class);
        
        runnable.run();
        
        assertEquals(1, staticCounter);
    }
    
    
    @Test
    public void runFromStaticTest() {
        runFromStatic();
    }
    
    public static void runFromStatic() {
        class RunnableIncrement {{staticCounter++;}}
        
        Runnable runnable = withRun(RunnableIncrement.class);
        
        runnable.run();
        
        assertEquals(1, staticCounter);
    }

}
