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

import com.google.common.base.Function;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public abstract class F<FROM, TO> {
	private static final Object[] EMPTY_OBJECT_ARRAY = new Object[] {};

	private static ThreadLocal<Object> holder = new ThreadLocal<Object>();

	private static class ClassFunction<FROM, TO> implements Function<FROM, TO> {

		private final Object[] constructorParameters;
		private final Constructor<F<FROM, TO>> constructor;

		public ClassFunction(Constructor<F<FROM, TO>> constructor, Object[] constructorParameters) {
			this.constructor = constructor;
			this.constructorParameters = constructorParameters;
		}

		@Override
		public TO apply(FROM input) {
			holder.set(input);
			try {
				F<FROM, TO> instance = (F<FROM, TO>) constructor.newInstance(constructorParameters);
				return instance.t;
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

	public static <FROM, TO> Function<FROM, TO> from(Class<? extends F<FROM, TO>> applyingClass) {
		return from(applyingClass, null);
	}

	public static <FROM, TO> Function<FROM, TO> from(Class<? extends F<FROM, TO>> applyingClass, Object instance) {

		Constructor<F<FROM, TO>> constructor = extractConstructor(applyingClass);
		Object[] constructorParameters = createConstructorParameters(constructor, instance);

		return new ClassFunction<FROM, TO>(constructor, constructorParameters);
	}

	private static Object[] createConstructorParameters(Constructor<?> constructor, Object instance) {
		if (constructor.getParameterTypes().length == 0) {
			return EMPTY_OBJECT_ARRAY;
		} else {
			return new Object[] { instance };
		}
	}

	@SuppressWarnings("unchecked")
	private static <FROM, TO> Constructor<F<FROM, TO>> extractConstructor(Class<? extends F<FROM, TO>> applyingClass) {
		Constructor<?> constructor = applyingClass.getDeclaredConstructors()[0];
		if (!constructor.isAccessible()) {
			constructor.setAccessible(true);
		}
		return (Constructor<F<FROM, TO>>) constructor;
	}

	protected FROM f;

	protected TO t;

	@SuppressWarnings("unchecked")
	public F() {
		f = (FROM) holder.get();
	}
}
