package info.piwai.funkyjfunctional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;

public class FunkyExecutorTest {
    
    private static final Object[] NULL_PARAM_ARRAY = new Object[] { null };

    static class Counter {
        int count = 0;
    }

    private static final Counter staticCounter = new Counter();

    private final Counter counter = new Counter();

    @Before
    public void setup() {
        counter.count = 0;
        staticCounter.count = 0;
    }

    @Test
    public void classInstanciated() {
        // @off
        class Instanciated {{ staticCounter.count++; }}
        // @on
        ClassExecutor<Instanciated> executor = new FunkyExecutor<Instanciated>(Instanciated.class, NULL_PARAM_ARRAY);

        assertEquals(0, staticCounter.count);
        executor.createExecutedInstance();
        assertEquals(1, staticCounter.count);
    }

    @Test
    public void classInstanciatedFromStaticMethod() {
        delegatedStaticTest();
    }

    public static void delegatedStaticTest() {
        // @off
        class Instanciated {{ staticCounter.count++; }}
        // @on
        ClassExecutor<Instanciated> executor = new FunkyExecutor<Instanciated>(Instanciated.class);

        assertEquals(0, staticCounter.count);
        executor.createExecutedInstance();
        assertEquals(1, staticCounter.count);
    }

    @Test
    public void differentInstances() {

        // @foff
        class Instanciated {
            {
                staticCounter.count++;
            }
        }
        // @fon
        ClassExecutor<Instanciated> executor = new FunkyExecutor<Instanciated>(Instanciated.class, NULL_PARAM_ARRAY);

        assertEquals(0, staticCounter.count);
        Instanciated instance1 = executor.createExecutedInstance();
        Instanciated instance2 = executor.createExecutedInstance();

        assertEquals(2, staticCounter.count);
        assertNotSame(instance1, instance2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void multipleConstructors() {
        @SuppressWarnings("unused")
        class Instanciated {
            Instanciated(FunkyExecutorTest obj) {
            }

            Instanciated() {
            }
        }

        new FunkyExecutor<Instanciated>(Instanciated.class, NULL_PARAM_ARRAY);
    }

    @Test
    public void instanceMemberAccess() {
        // @foff
        class Instanciated {
            {
                counter.count++;
            }
        }
        // @fon
        ClassExecutor<Instanciated> executor = new FunkyExecutor<Instanciated>(Instanciated.class, this);
        assertEquals(0, counter.count);
        executor.createExecutedInstance();
        assertEquals(1, counter.count);
    }

    @Test
    public void localVariableAccess() {

        final Counter counter = new Counter();
        // @foff
        class Instanciated {
            {
                counter.count++;
            }
        }
        // @fon
        ClassExecutor<Instanciated> executor = new FunkyExecutor<Instanciated>(Instanciated.class, this, counter);
        assertEquals(0, counter.count);
        executor.createExecutedInstance();
        assertEquals(1, counter.count);
    }

    @Test
    public void localstaticVariableAccess() {
        localStaticVariableAccessDelegate();
    }

    public static void localStaticVariableAccessDelegate() {

        final Counter counter = new Counter();
        // @foff
        class Instanciated {
            {
                counter.count++;
            }
        }
        // @fon
        ClassExecutor<Instanciated> executor = new FunkyExecutor<Instanciated>(Instanciated.class, counter);
        assertEquals(0, counter.count);
        executor.createExecutedInstance();
        assertEquals(1, counter.count);
    }

    @Test(expected = IllegalArgumentException.class)
    public void missingConstructorParameters() {
        // @foff
        class Instanciated {
        }
        // @fon
        new FunkyExecutor<Instanciated>(Instanciated.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void incompatibleParameters() {
        // @foff
        class Instanciated {
        }
        // @fon
        new FunkyExecutor<Instanciated>(Instanciated.class, new Object());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullConstructorParamsArray() {
        // @foff
        class Instanciated {
        }
        ;
        // @fon
        Object[] nullArray = null;
        new FunkyExecutor<Instanciated>(Instanciated.class, nullArray);
    }

    @Test(expected = ArithmeticException.class)
    public void throwsRuntimeException() {
        // @foff
        class Instanciated {
            {
                System.out.println(42 / 0);
            }
        }
        // @fon
        ClassExecutor<Instanciated> executor = new FunkyExecutor<Instanciated>(Instanciated.class, NULL_PARAM_ARRAY);
        executor.createExecutedInstance();
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwingConstructor() {
        @SuppressWarnings("unused")
        class Instanciated {
            public Instanciated() throws Exception {
            }
        }

        new FunkyExecutor<Instanciated>(Instanciated.class, NULL_PARAM_ARRAY);
    }

    @Test(expected = Error.class)
    public void throwsError() {
        // @foff
        class Instanciated {
            {
                methodThrowingError();
            }
        }
        // @fon
        ClassExecutor<Instanciated> executor = new FunkyExecutor<Instanciated>(Instanciated.class, this);
        executor.createExecutedInstance();
    }

    private void methodThrowingError() {
        throw new Error();
    }

    @Test(expected = IllegalArgumentException.class)
    public void abstractApplyingClass() {
        abstract class Instanciated {
        }

        new FunkyExecutor<Instanciated>(Instanciated.class, NULL_PARAM_ARRAY);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void nullApplyClass() {
        new FunkyExecutor<Void>(null);
    }

}
