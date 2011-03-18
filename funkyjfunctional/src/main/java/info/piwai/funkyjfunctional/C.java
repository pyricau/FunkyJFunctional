/*
 * Copyright 2011 Pierre-Yves Ricau (py.ricau at gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package info.piwai.funkyjfunctional;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public abstract class C<T> {
	private static final Object[] EMPTY_OBJECT_ARRAY = new Object[] {};

	private static ThreadLocal<Object> holder = new ThreadLocal<Object>();

	private static class Compared<T> {

		public final T t1;
		public final T t2;

		public Compared(T t1, T t2) {
			this.t1 = t1;
			this.t2 = t2;
		}
	}

	private static class ClassComparator<T> implements Comparator<T> {

		private final Object[] constructorParameters;
		private final Constructor<C<T>> constructor;

		public ClassComparator(Constructor<C<T>> constructor, Object[] constructorParameters) {
			this.constructor = constructor;
			this.constructorParameters = constructorParameters;
		}

		@Override
		public int compare(T t1, T t2) {
			holder.set(new Compared<T>(t1, t2));
			try {
				C<T> instance = (C<T>) constructor.newInstance(constructorParameters);
				return instance.r;
			} catch (InstantiationException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (InvocationTargetException e) {
				throw new RuntimeException(e);
			} finally {
				holder.set(null);
			}
		}
	}

	public static <T> Comparator<T> from(Class<? extends C<T>> applyingClass) {
		return from(applyingClass, null);
	}

	public static <T> Comparator<T> from(Class<? extends C<T>> applyingClass, Object instance) {
		Constructor<C<T>> constructor = extractConstructor(applyingClass);
		Object[] constructorParameters = createConstructorParameters(constructor, instance);

		return new ClassComparator<T>(constructor, constructorParameters);
	}

	private static Object[] createConstructorParameters(Constructor<?> constructor, Object instance) {
		if (constructor.getParameterTypes().length == 0) {
			return EMPTY_OBJECT_ARRAY;
		} else {
			return new Object[] { instance };
		}
	}

	@SuppressWarnings("unchecked")
	private static <T> Constructor<C<T>> extractConstructor(Class<? extends C<T>> applyingClass) {
		Constructor<?> constructor = applyingClass.getDeclaredConstructors()[0];
		if (!constructor.isAccessible()) {
			constructor.setAccessible(true);
		}
		return (Constructor<C<T>>) constructor;
	}

	protected T t1;
	protected T t2;

	protected int r;

	@SuppressWarnings("unchecked")
	public C() {
		Compared<T> compared = (Compared<T>) holder.get();
		t1 = compared.t1;
		t2 = compared.t2;
	}

}
