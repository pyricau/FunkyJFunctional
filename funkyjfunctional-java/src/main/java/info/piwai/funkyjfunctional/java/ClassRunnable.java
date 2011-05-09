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
package info.piwai.funkyjfunctional.java;

import info.piwai.funkyjfunctional.ClassExecutor;

/**
 * <p>
 * {@link ClassRunnable} is not part of the API, which is why it has
 * package-private scope.
 * 
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
final class ClassRunnable<U> implements Runnable {

    private final ClassExecutor<U> executor;

    ClassRunnable(ClassExecutor<U> executor) {
        this.executor = executor;
    }

    @Override
    public void run() {
        executor.createExecutedInstance();
    }
}