package info.piwai.funkyjfunctional.wicket.test;

import static info.piwai.funkyjfunctional.wicket.FunkyWicket.withLdm;
import static org.junit.Assert.assertEquals;
import info.piwai.funkyjfunctional.wicket.LDM;

import org.apache.wicket.model.LoadableDetachableModel;
import org.junit.Test;


public class LDMTest {
	
    @Test
    public void callableReturnsFortyTwo() throws Exception {
        // @off
        class FortyTwo extends LDM<String> {{ r = "42"; }}
        // @on

        LoadableDetachableModel<String> ldm = withLdm(FortyTwo.class);

        assertEquals("42",  ldm.getObject());
    }


}
