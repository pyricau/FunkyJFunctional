package info.piwai.funkyjfunctional;

import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

public class ThreadSafeInputHolderTest extends AbstractInputHolderTest {

    @Before
    public void setup() {
        inputHolder = ThreadSafeInputHolder.INSTANCE;
        inputHolder.clean();
    }

    @Test
    public void twoThreadsSeeDifferentValues() throws InterruptedException {
        Object input = new Object();
        inputHolder.set(input);

        Thread thread = new Thread() {
            @Override
            public void run() {
                inputHolder.set(new Object());
            }
        };
        thread.start();

        thread.join();

        assertSame(input, inputHolder.get());
    }

    @Test(expected = IllegalAccessException.class)
    public void cannotBeInstanciated() throws Exception {
        ThreadSafeInputHolder.class.newInstance();
    }

}
