/**
 * Copyright (C) 2011 Pierre-Yves Ricau (py.ricau at gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed To in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package info.piwai.funkyjfunctional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
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
    public void classInstantiated() {
        // @off
        class Instantiated {{ staticCounter.count++; }}
        // @on
        ClassExecutor<Instantiated> executor = new FunkyExecutor<Instantiated>(Instantiated.class, NULL_PARAM_ARRAY);

        assertEquals(0, staticCounter.count);
        executor.createExecutedInstance();
        assertEquals(1, staticCounter.count);
    }

    @Test
    public void classInstantiatedFromStaticMethod() {
        delegatedStaticTest();
    }

    public static void delegatedStaticTest() {
        // @off
        class Instantiated {{ staticCounter.count++; }}
        // @on
        ClassExecutor<Instantiated> executor = new FunkyExecutor<Instantiated>(Instantiated.class);

        assertEquals(0, staticCounter.count);
        executor.createExecutedInstance();
        assertEquals(1, staticCounter.count);
    }

    @Test
    public void differentInstances() {

        // @off
        class Instantiated {{ staticCounter.count++; }}
        // @on
        ClassExecutor<Instantiated> executor = new FunkyExecutor<Instantiated>(Instantiated.class, NULL_PARAM_ARRAY);

        assertEquals(0, staticCounter.count);
        Instantiated instance1 = executor.createExecutedInstance();
        Instantiated instance2 = executor.createExecutedInstance();

        assertEquals(2, staticCounter.count);
        assertNotSame(instance1, instance2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void multipleConstructors() {
        @SuppressWarnings("unused")
        class Instantiated {
            Instantiated(FunkyExecutorTest obj) {
            }

            Instantiated() {
            }
        }

        new FunkyExecutor<Instantiated>(Instantiated.class, NULL_PARAM_ARRAY);
    }

    @Test
    public void instanceMemberAccess() {
        // @off 
        class Instantiated {{ counter.count++; }}
        // @on
        ClassExecutor<Instantiated> executor = new FunkyExecutor<Instantiated>(Instantiated.class, this);
        assertEquals(0, counter.count);
        executor.createExecutedInstance();
        assertEquals(1, counter.count);
    }

    @Test
    public void localVariableAccess() {

        final Counter counter = new Counter();
        // @off
        class Instantiated {{ counter.count++; }}
        // @on
        ClassExecutor<Instantiated> executor = new FunkyExecutor<Instantiated>(Instantiated.class, this, counter);
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
        // @off
        class Instantiated {{ counter.count++; }}
        // @on
        ClassExecutor<Instantiated> executor = new FunkyExecutor<Instantiated>(Instantiated.class, counter);
        assertEquals(0, counter.count);
        executor.createExecutedInstance();
        assertEquals(1, counter.count);
    }

    @SuppressWarnings("all")
    @Test(expected = IllegalArgumentException.class)
    public void nullconstructorArgumentsParameter() {
        // @off
        class Instantiated {}
        // @on
        new FunkyExecutor<Instantiated>(Instantiated.class, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void incompatibleParameters() {
        // @off
        class Instantiated {}
        // @on
        new FunkyExecutor<Instantiated>(Instantiated.class, new Object());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullConstructorParamsArray() {
        // @off
        class Instantiated {}
        // @on
        Object[] nullArray = null;
        new FunkyExecutor<Instantiated>(Instantiated.class, nullArray);
    }

    @Test(expected = ArithmeticException.class)
    public void throwsRuntimeException() {
        // @off
        class Instantiated {{ System.out.println(42 / 0); }}
        // @on
        ClassExecutor<Instantiated> executor = new FunkyExecutor<Instantiated>(Instantiated.class, NULL_PARAM_ARRAY);
        executor.createExecutedInstance();
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwingConstructor() {
        @SuppressWarnings("unused")
        class Instantiated {
            public Instantiated() throws Exception {
            }
        }

        new FunkyExecutor<Instantiated>(Instantiated.class, NULL_PARAM_ARRAY);
    }

    @Test(expected = Error.class)
    public void throwsError() {
        // @off
        class Instantiated {{ methodThrowingError(); }}
        // @on
        ClassExecutor<Instantiated> executor = new FunkyExecutor<Instantiated>(Instantiated.class, this);
        executor.createExecutedInstance();
    }

    private void methodThrowingError() {
        throw new Error();
    }

    @Test(expected = IllegalArgumentException.class)
    public void abstractApplyingClass() {
        // @off
        abstract class Instantiated {}
        // @on
        new FunkyExecutor<Instantiated>(Instantiated.class, NULL_PARAM_ARRAY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullApplyClass() {
        new FunkyExecutor<Void>(null);
    }
    
    @Test
    public void factoryBuildsValidExecutor() {
        // @off
        class Instantiated {}
        // @on
        Funky.classExecutor(Instantiated.class);
    }
    
    @Test
    public void factoryBuildsValidExecutorWithInput() {
        // @off
        class Instantiated {}
        // @on
        Funky.classExecutorWithInput(Instantiated.class);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nullConstructorParamsArrayFromFactoryExecutor() {
        // @off
        class Instantiated {}
        // @on
        Object[] nullArray = null;
        Funky.classExecutor(Instantiated.class, nullArray);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nullConstructorParamsArrayFromFactoryExecutorWithInput() {
        // @off
        class Instantiated {}
        // @on
        Object[] nullArray = null;
        Funky.classExecutorWithInput(Instantiated.class, nullArray);
    }

}
