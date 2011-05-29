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

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class FunkyFunctionTest {

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
        ClassFunction<Instantiated> executor = new FunkyFunction<Instantiated>(Instantiated.class, NULL_PARAM_ARRAY);

        assertEquals(0, staticCounter.count);
        executor.execute();
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
        ClassFunction<Instantiated> executor = new FunkyFunction<Instantiated>(Instantiated.class);

        assertEquals(0, staticCounter.count);
        executor.execute();
        assertEquals(1, staticCounter.count);
    }

    @Test
    public void differentInstances() {

        // @off
        class Instantiated {{ staticCounter.count++; }}
        // @on
        ClassFunction<Instantiated> executor = new FunkyFunction<Instantiated>(Instantiated.class, NULL_PARAM_ARRAY);

        assertEquals(0, staticCounter.count);
        Instantiated instance1 = executor.execute();
        Instantiated instance2 = executor.execute();

        assertEquals(2, staticCounter.count);
        assertNotSame(instance1, instance2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void multipleConstructors() {
        @SuppressWarnings("unused")
        class Instantiated {
            Instantiated(FunkyFunctionTest obj) {
            }

            Instantiated() {
            }
        }

        new FunkyFunction<Instantiated>(Instantiated.class, NULL_PARAM_ARRAY);
    }

    @Test
    public void instanceMemberAccess() {
        // @off 
        class Instantiated {{ counter.count++; }}
        // @on
        ClassFunction<Instantiated> executor = new FunkyFunction<Instantiated>(Instantiated.class, this);
        assertEquals(0, counter.count);
        executor.execute();
        assertEquals(1, counter.count);
    }

    @Test
    public void localVariableAccess() {

        final Counter counter = new Counter();
        // @off
        class Instantiated {{ counter.count++; }}
        // @on
        ClassFunction<Instantiated> executor = new FunkyFunction<Instantiated>(Instantiated.class, this, counter);
        assertEquals(0, counter.count);
        executor.execute();
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
        ClassFunction<Instantiated> executor = new FunkyFunction<Instantiated>(Instantiated.class, counter);
        assertEquals(0, counter.count);
        executor.execute();
        assertEquals(1, counter.count);
    }

    @SuppressWarnings("all")
    @Test(expected = IllegalArgumentException.class)
    public void nullconstructorArgumentsParameter() {
        // @off
        class Instantiated {}
        // @on
        new FunkyFunction<Instantiated>(Instantiated.class, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void incompatibleParameters() {
        // @off
        class Instantiated {}
        // @on
        new FunkyFunction<Instantiated>(Instantiated.class, new Object());
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongNumberOfParameters() {
        // @off
        class Instantiated {}
        // @on
        new FunkyFunction<Instantiated>(Instantiated.class, this, new Object());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullConstructorParamsArray() {
        // @off
        class Instantiated {}
        // @on
        Object[] nullArray = null;
        new FunkyFunction<Instantiated>(Instantiated.class, nullArray);
    }

    @Test(expected = ArithmeticException.class)
    public void throwsRuntimeException() {
        // @off
        class Instantiated {{ System.out.println(42 / 0); }}
        // @on
        ClassFunction<Instantiated> executor = new FunkyFunction<Instantiated>(Instantiated.class, NULL_PARAM_ARRAY);
        executor.execute();
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwingConstructor() {
        @SuppressWarnings("unused")
        class Instantiated {
            public Instantiated() throws Exception {
            }
        }
        new FunkyFunction<Instantiated>(Instantiated.class, NULL_PARAM_ARRAY);
    }

    @Test(expected = Error.class)
    public void throwsError() {
        // @off
        class Instantiated {{ methodThrowingError(); }}
        // @on
        ClassFunction<Instantiated> executor = new FunkyFunction<Instantiated>(Instantiated.class, this);
        executor.execute();
    }

    private void methodThrowingError() {
        throw new Error();
    }

    @Test(expected = IllegalArgumentException.class)
    public void abstractApplyingClass() {
        // @off
        abstract class Instantiated {}
        // @on
        new FunkyFunction<Instantiated>(Instantiated.class, NULL_PARAM_ARRAY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullApplyClass() {
        new FunkyFunction<Void>(null);
    }

    @Test
    public void simpleExecutorDoesNotThrow() {
        // @off
        class Instantiated {}
        // @on
        new FunkyFunction<Instantiated>(Instantiated.class);
    }

    @Test
    public void getSimpleNameReturnsDeclaringClassSimpleName() {
        // @off
        class Instantiated {}
        // @on
        FunkyFunction<Instantiated> executor = new FunkyFunction<Instantiated>(Instantiated.class);
        assertEquals("Instantiated", executor.getClassSimpleName());
    }

    @Test
    public void primitiveConstantIsDirectlyCopied() {
        final int test = 2;

        // @off
        class Instantiated {{ assertEquals(2, test); }}
        // @on

        FunkyFunction<Instantiated> executor = new FunkyFunction<Instantiated>(Instantiated.class);

        executor.execute();
    }

    @Test
    public void primitiveIsWrapped() {

        int a = 1;

        final int total = a + 1;

        // @off
        class Instantiated {{ assertEquals(2, total); }}
        // @on

        FunkyFunction<Instantiated> executor = new FunkyFunction<Instantiated>(Instantiated.class, null, total);

        executor.execute();
    }

    @Test(expected = IllegalArgumentException.class)
    public void primitiveWithNullThrows() {

        int a = 1;

        final int total = a + 1;

        // @off
        class Instantiated {{ assertEquals(2, total); }}
        // @on

        FunkyFunction<Instantiated> executor = new FunkyFunction<Instantiated>(Instantiated.class, null, null);

        executor.execute();
    }

    @Test
    public void intConstructorParamWorks() {
        int test = 2;

        class Holder {
            int result;
        }

        final Holder holder = new Holder();

        class Instantiated {
            @SuppressWarnings("unused")
            Instantiated(int param) {
                holder.result = param;
            }
        }

        FunkyFunction<Instantiated> executor = new FunkyFunction<Instantiated>(Instantiated.class, this, test, holder);

        executor.execute();

        assertSame(test, holder.result);
    }

    enum SomeEnum {
    }

    @Test(expected = IllegalArgumentException.class)
    public void enumClassThrows() {
        FunkyFunction<SomeEnum> executor = new FunkyFunction<SomeEnum>(SomeEnum.class, "Yes", 0);
        executor.execute();
    }

    interface SomeInterface {
    }

    @Test(expected = IllegalArgumentException.class)
    public void interfaceClassThrows() {
        FunkyFunction<SomeInterface> executor = new FunkyFunction<SomeInterface>(SomeInterface.class, "Yes", 0);
        executor.execute();
    }

    @Test
    public void isSerializable() throws IOException {
        // @off
        class Instantiated {{ }}
        // @on

        FunkyFunction<Instantiated> executor = new FunkyFunction<Instantiated>(Instantiated.class);

        ByteArrayOutputStream out = serialize(executor);
        assertTrue(out.toByteArray().length > 0);
    }

    private ByteArrayOutputStream serialize(Object original) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(original);
        oos.close();
        return out;
    }

    private static boolean checked;

    @Test
    public void isDeserializable() throws IOException, ClassNotFoundException {

        checked = false;

        // @off
        class Instantiated {{ checked = true; }}
        // @on

        FunkyFunction<Instantiated> original = new FunkyFunction<Instantiated>(Instantiated.class);

        ByteArrayOutputStream out = serialize(original);

        FunkyFunction<Instantiated> copy = deserialize(out);

        copy.execute();

        assertTrue(checked);
    }

    @SuppressWarnings("unchecked")
    private <T> T deserialize(ByteArrayOutputStream out) throws IOException, ClassNotFoundException {
        byte[] pickled = out.toByteArray();
        InputStream in = new ByteArrayInputStream(pickled);
        ObjectInputStream ois = new ObjectInputStream(in);
        return (T) ois.readObject();
    }

    static class SerializableHolder implements Serializable {
        private static final long serialVersionUID = 1L;

        boolean checked = false;
    }

    @Test
    public void argumentsAreDeserializables() throws IOException, ClassNotFoundException {

        final SerializableHolder holder = new SerializableHolder();

        class Instantiated {
            {
                holder.checked = true;
            }

            SerializableHolder getHolder() {
                return holder;
            }
        }

        FunkyFunction<Instantiated> original = new FunkyFunction<Instantiated>(Instantiated.class, null, holder);

        ByteArrayOutputStream out = serialize(original);

        FunkyFunction<Instantiated> copy = deserialize(out);

        Instantiated inst = copy.execute();

        assertTrue(inst.getHolder().checked);
    }

}
