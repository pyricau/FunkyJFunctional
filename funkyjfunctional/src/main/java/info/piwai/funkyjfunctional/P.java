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

import com.google.common.base.Predicate;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public abstract class P<T> {
	private static final Object[] EMPTY_OBJECT_ARRAY = new Object[] {};

	private static ThreadLocal<Object> holder = new ThreadLocal<Object>();

	private static class ClassPredicate<T> implements Predicate<T> {

		private final Object[] constructorParameters;
		private final Constructor<P<T>> constructor;

		public ClassPredicate(Constructor<P<T>> constructor, Object[] constructorParameters) {
			this.constructor = constructor;
			this.constructorParameters = constructorParameters;
		}

		@Override
		public boolean apply(T input) {
			holder.set(input);
			try {
				P<T> instance = (P<T>) constructor.newInstance(constructorParameters);
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

	public static <T> Predicate<T> from(Class<? extends P<T>> applyingClass) {
		return from(applyingClass, null);
	}

	public static <T> Predicate<T> from(Class<? extends P<T>> applyingClass, Object instance) {
		Constructor<P<T>> constructor = extractConstructor(applyingClass);
		Object[] constructorParameters = createConstructorParameters(constructor, instance);

		return new ClassPredicate<T>(constructor, constructorParameters);
	}

	private static Object[] createConstructorParameters(Constructor<?> constructor, Object instance) {
		if (constructor.getParameterTypes().length == 0) {
			return EMPTY_OBJECT_ARRAY;
		} else {
			return new Object[] { instance };
		}
	}

	@SuppressWarnings("unchecked")
	private static <T> Constructor<P<T>> extractConstructor(Class<? extends P<T>> applyingClass) {
		Constructor<?> constructor = applyingClass.getDeclaredConstructors()[0];
		if (!constructor.isAccessible()) {
			constructor.setAccessible(true);
		}
		return (Constructor<P<T>>) constructor;
	}

	protected T t;

	protected boolean r;

	@SuppressWarnings("unchecked")
	public P() {
		t = (T) holder.get();
	}

}
