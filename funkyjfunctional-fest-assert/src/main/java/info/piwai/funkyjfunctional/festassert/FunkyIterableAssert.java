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
package info.piwai.funkyjfunctional.festassert;

import static info.piwai.funkyjfunctional.festassert.FunkyFestAssert.withCond;
import static info.piwai.funkyjfunctional.festassert.NegativeCondition.not;

import org.fest.assertions.Condition;
import org.fest.assertions.IteratorAssert;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class FunkyIterableAssert<T> extends IteratorAssert {
    
    public static <T> FunkyIterableAssert<T> assertThat(Iterable<T> actual) {
        return new FunkyIterableAssert<T>(actual);
    }

    private final Iterable<T> actualIterable;

    public FunkyIterableAssert(Iterable<T> actual) {
        super(actual.iterator());
        actualIterable = actual;
    }
    
    public FunkyIterableAssert<T> eachSatisfies(Condition<T> condition) {
        
        long notMatching = 0;
        StringBuilder notMatchingDescription = new StringBuilder();
        long index = 0;
        for (T value : actualIterable) {
            if (!condition.matches(value)) {
                notMatching++;
                notMatchingDescription //
                        .append("Element ") //
                        .append(index) //
                        .append(", [") //
                        .append(value.toString()) //
                        .append("] does not match condition: ") //
                        .append(condition.description()) //
                        .append("\n") //
                ;
            }
            index++;
        }

        if (notMatching == 0) {
            return this;
        }

        failIfCustomMessageIsSet();
        throw failure(notMatching + " element(s) in the iterable not matching the provided condition:\n" + notMatchingDescription.toString());
    }
    
    public FunkyIterableAssert<T> noneSatisfies(Condition<T> condition) {
        return eachSatisfies(not(condition));
    }
    
    public <U extends Cond<T>> FunkyIterableAssert<T> eachSatisfies(Class<U> applyingClass) {
        return eachSatisfies(withCond(applyingClass));
    }
    
    public <U extends Cond<T>> FunkyIterableAssert<T> noneSatisfies(Class<U> applyingClass) {
        return noneSatisfies(withCond(applyingClass));
    }
    
    public FunkyIterableAssert<T> eachIs(Condition<T> condition) {
        return eachSatisfies(condition);
    }
    
    public <U extends Cond<T>> FunkyIterableAssert<T> eachIs(Class<U> applyingClass) {
        return eachSatisfies(applyingClass);
    }
    
    public <U extends Cond<T>> FunkyIterableAssert<T> noneIs(Class<U> applyingClass) {
        return noneSatisfies(applyingClass);
    }
    
    public FunkyIterableAssert<T> noneIs(Condition<T> condition) {
        return noneSatisfies(condition);
    }

}
