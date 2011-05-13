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

import info.piwai.funkyjfunctional.ClassExecutor;
import org.fest.assertions.Description;

/**
 * @author Nicolas Francois (nicolas.franc at gmail.com)
 */
public abstract class Desc {

    /**
     * <p>
     * {@link ClassDescription} is not part of the API, which is why it has
     * package-private scope.
     */
    static class ClassDescription<U extends Desc> implements Description {

        private final ClassExecutor<U> executor;

        ClassDescription(ClassExecutor<U> executor) {
            this.executor = executor;
        }

        @Override
        public String value() {
            U instance = executor.createExecutedInstance();
            return instance.r;
        }
    }

    protected String r;

}
