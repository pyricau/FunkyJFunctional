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
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public final class FunctionFactory {
    
    private final InputHolder inputHolder;
    
    public FunctionFactory(InputHolder inputHolder) {
        this.inputHolder = inputHolder;
    }
    
    /**
     * @param constructorArguments
     *            if you want the {@link ClassFunction} to be serialized, all
     *            constructorArguments elements should be {@link Serializable}.
     */
    public <T> ClassFunction<T> newFunction(Class<T> applyingClass, Object... constructorArguments) {
        return new FunkyFunction<T>(applyingClass, constructorArguments);
    }

    public <T> ClassFunctionWithInput<T> newFunctionWithInput(Class<T> applyingClass, Object... constructorArguments) {
        return new FunkyFunctionWithInput<T>(newFunction(applyingClass, constructorArguments), inputHolder);
    }

    public InputHolder getInputHolder() {
        return inputHolder;
    }
    
}
