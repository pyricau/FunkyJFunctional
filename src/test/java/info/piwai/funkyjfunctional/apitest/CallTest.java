package info.piwai.funkyjfunctional.apitest;

import static info.piwai.funkyjfunctional.Funky.withCall;
import static org.junit.Assert.assertEquals;

import java.util.concurrent.Callable;

import info.piwai.funkyjfunctional.Call;

import org.junit.Test;


public class CallTest {
    
    @Test
    public void callReturnsResult() throws Exception {
        class FortyTwo extends Call<Integer> {{ r = 42; }}
        
        Callable<Integer> callable = withCall(FortyTwo.class);
        
        assertEquals(42, (int) callable.call());
    }

}
