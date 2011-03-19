package info.piwai.funkyjfunctional;

import info.piwai.funkyjfunctional.Comp.ClassComparator;
import info.piwai.funkyjfunctional.Func.ClassFunction;
import info.piwai.funkyjfunctional.Pred.ClassPredicate;

import java.util.Comparator;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

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


}
