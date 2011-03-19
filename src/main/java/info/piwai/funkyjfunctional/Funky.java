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

import info.piwai.funkyjfunctional.Call.ClassCallable;
import info.piwai.funkyjfunctional.Comp.ClassComparator;
import info.piwai.funkyjfunctional.Func.ClassFunction;
import info.piwai.funkyjfunctional.Pred.ClassPredicate;

import java.util.Comparator;
import java.util.concurrent.Callable;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class Funky {
    
    public static <T, U extends Comp<T>> Comparator<T> withComp(Class<U> applyingClass) {
        return withComp(applyingClass, null);
    }

    public static <T, U extends Comp<T>> Comparator<T> withComp(Class<U> applyingClass, Object instance) {
        return new ClassComparator<T, U>(applyingClass, instance);
    }
    
    public static <From, To, U extends Func<From, To>> Function<From, To> withFunc(Class<U> applyingClass) {
        return withFunc(applyingClass, null);
    }

    public static <From, To, U extends Func<From, To>> Function<From, To> withFunc(Class<U> applyingClass, Object instance) {
        return new ClassFunction<From, To, U>(applyingClass, instance);
    }
    
    public static <T, U extends Pred<T>> Predicate<T> withPred(Class<U> applyingClass) {
        return withPred(applyingClass, null);
    }

    public static <T, U extends Pred<T>> Predicate<T> withPred(Class<U> applyingClass, Object instance) {
        return new ClassPredicate<T, U>(applyingClass, instance);
    }
    
    public static Runnable withRun(Class<?> applyingClass) {
        return withRun(applyingClass, null);
    }

    public static <U> Runnable withRun(Class<U> applyingClass, Object instance) {
        return new ClassRunnable<U>(applyingClass, instance);
    }
    
    public static <T, U extends Call<T>> Callable<T> withCall(Class<U> applyingClass) {
        return withCall(applyingClass, null);
    }

    public static <T, U extends Call<T>> Callable<T> withCall(Class<U> applyingClass, Object instance) {
        return new ClassCallable<T, U>(applyingClass, instance);
    }


}
