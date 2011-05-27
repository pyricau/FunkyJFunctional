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
 * Works the same way as a {@link ClassExecutor}, but the functional code can
 * have input data, which is usually set as instance attribute right before the
 * initblock is called.
 * 
 * @see ClassExecutor
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public interface ClassExecutorWithInput<T> extends HasClassSimpleName, Serializable {

    /**
     * @param input The input that will be used by the functional code
     * @see ClassExecutor#createExecutedInstance()
     */
    T createExecutedInstance(Object input);

}