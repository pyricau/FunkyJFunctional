package info.piwai.funkyjfunctional.java.apitest;

import info.piwai.funkyjfunctional.java.FunkyJava;

import org.junit.Test;

public class FunkyJavaTest {

    @Test(expected = Exception.class)
    public void funkyJavaCannotBeInstanciated() throws Exception {
        FunkyJava.class.newInstance();
    }

}
