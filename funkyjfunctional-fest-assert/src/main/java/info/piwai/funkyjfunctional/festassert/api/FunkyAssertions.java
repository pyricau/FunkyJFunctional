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
package info.piwai.funkyjfunctional.festassert.api;

import info.piwai.funkyjfunctional.festassert.Cond;
import info.piwai.funkyjfunctional.festassert.Desc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.fest.assertions.api.Assertions;
import org.fest.assertions.api.CharacterAssert;
import org.fest.assertions.api.InputStreamAssert;
import org.fest.assertions.api.IterableAssert;
import org.fest.assertions.util.ImageReader;

/**
 * This is a clone of Fest {@link Assertions} class, that replicates its
 * behavior while adding support for {@link Cond} and {@link Desc}.
 * 
 * @see Assertions
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class FunkyAssertions {

	/**
	 * Creates a new instance of <code>{@link FunkyBigDecimalAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyBigDecimalAssert assertThat(BigDecimal actual) {
		return new FunkyBigDecimalAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyBooleanAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyBooleanAssert assertThat(boolean actual) {
		return new FunkyBooleanAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyBooleanAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyBooleanAssert assertThat(Boolean actual) {
		return new FunkyBooleanAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyBooleanArrayAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyBooleanArrayAssert assertThat(boolean[] actual) {
		return new FunkyBooleanArrayAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyImageAssert}</code>. To read
	 * an image from the file system use
	 * <code>{@link ImageReader#readImageFrom(String)}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyImageAssert assertThat(BufferedImage actual) {
		return new FunkyImageAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyByteAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyByteAssert assertThat(byte actual) {
		return new FunkyByteAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyByteAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyByteAssert assertThat(Byte actual) {
		return new FunkyByteAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyByteArrayAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyByteArrayAssert assertThat(byte[] actual) {
		return new FunkyByteArrayAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyCharacterAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyCharacterAssert assertThat(char actual) {
		return new FunkyCharacterAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyCharArrayAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyCharArrayAssert assertThat(char[] actual) {
		return new FunkyCharArrayAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link CharacterAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyCharacterAssert assertThat(Character actual) {
		return new FunkyCharacterAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link IterableAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyIterableAssert assertThat(Iterable<?> actual) {
		return new FunkyIterableAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyDoubleAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyDoubleAssert assertThat(double actual) {
		return new FunkyDoubleAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyDoubleAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyDoubleAssert assertThat(Double actual) {
		return new FunkyDoubleAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyDoubleArrayAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyDoubleArrayAssert assertThat(double[] actual) {
		return new FunkyDoubleArrayAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyFileAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyFileAssert assertThat(File actual) {
		return new FunkyFileAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link InputStreamAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyInputStreamAssert assertThat(InputStream actual) {
		return new FunkyInputStreamAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyFloatAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyFloatAssert assertThat(float actual) {
		return new FunkyFloatAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyFloatAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyFloatAssert assertThat(Float actual) {
		return new FunkyFloatAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyFloatArrayAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyFloatArrayAssert assertThat(float[] actual) {
		return new FunkyFloatArrayAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyIntegerAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyIntegerAssert assertThat(int actual) {
		return new FunkyIntegerAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyIntArrayAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyIntArrayAssert assertThat(int[] actual) {
		return new FunkyIntArrayAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyIntegerAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyIntegerAssert assertThat(Integer actual) {
		return new FunkyIntegerAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyListAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyListAssert assertThat(List<?> actual) {
		return new FunkyListAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyLongAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyLongAssert assertThat(long actual) {
		return new FunkyLongAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyLongAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyLongAssert assertThat(Long actual) {
		return new FunkyLongAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyLongArrayAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyLongArrayAssert assertThat(long[] actual) {
		return new FunkyLongArrayAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyObjectAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyObjectAssert assertThat(Object actual) {
		return new FunkyObjectAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyObjectArrayAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyObjectArrayAssert assertThat(Object[] actual) {
		return new FunkyObjectArrayAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyMapAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyMapAssert assertThat(Map<?, ?> actual) {
		return new FunkyMapAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyShortAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyShortAssert assertThat(short actual) {
		return new FunkyShortAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyShortAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyShortAssert assertThat(Short actual) {
		return new FunkyShortAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyShortArrayAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyShortArrayAssert assertThat(short[] actual) {
		return new FunkyShortArrayAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyStringAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyStringAssert assertThat(String actual) {
		return new FunkyStringAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyDateAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion object.
	 */
	public static FunkyDateAssert assertThat(Date actual) {
		return new FunkyDateAssert(actual);
	}

	/**
	 * Creates a new instance of <code>{@link FunkyThrowableAssert}</code>.
	 * 
	 * @param actual
	 *            the actual value.
	 * @return the created assertion Throwable.
	 */
	public static FunkyThrowableAssert assertThat(Throwable actual) {
		return new FunkyThrowableAssert(actual);
	}

	protected FunkyAssertions() {
	}
}
