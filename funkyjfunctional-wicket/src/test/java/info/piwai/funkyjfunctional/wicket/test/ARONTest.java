package info.piwai.funkyjfunctional.wicket.test;

import static info.piwai.funkyjfunctional.wicket.FunkyWicket.withARON;
import static org.junit.Assert.assertEquals;
import info.piwai.funkyjfunctional.wicket.ARON;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.junit.Test;


public class ARONTest {
	
    @Test
    public void callableReturnsFortyTwo() throws Exception {
        // @off
        class FortyTwo extends ARON<String> {{ r = "42"; }}
        // @on

        AbstractReadOnlyModel<String> aron = withARON(FortyTwo.class);

        assertEquals("42",  aron.getObject());
    }


}
