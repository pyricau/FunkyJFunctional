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

import org.fest.assertions.core.Condition;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
final class NegativeCondition<T> extends Condition<T> {

    public static <T> Condition<T> not(Condition<T> condition) {
        return new NegativeCondition<T>(condition);
    }

    private final Condition<T> condition;

    public NegativeCondition(Condition<T> condition) {
        this.condition = condition;
    }

    @Override
    public boolean matches(T value) {
        boolean matches = condition.matches(value);
        as("not " + condition.description());
        return !matches;
    }
}
