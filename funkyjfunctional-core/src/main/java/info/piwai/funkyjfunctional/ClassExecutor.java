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
package info.piwai.funkyjfunctional;

import java.io.Serializable;

/**
 * Executor that creates executed instances. An "executed instance" means that
 * the functional code is executed by creating a T instance. In fact, the
 * functional code is located in an init block, ie in the constructor, and
 * therefor you need to create a new instance to execute the functional code.
 * 
 * <T> The type of the class that will be instanciated, and therefore executed.
 * 
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public interface ClassExecutor<T> extends HasClassSimpleName, Serializable {

    /**
     * Creates a new T instance, which also means that the functional code is
     * executed.
     * 
     * @return the created instance, after the functional code has been
     *         executed. This is useful to extract the result data set by the
     *         functional code.
     */
    T createExecutedInstance();
}