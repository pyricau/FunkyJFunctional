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

import info.piwai.funkyjfunctional.Funky;

import java.util.Comparator;

/**
 * A Funky {@link Comparator}
 * 
 * @see FunkyJava FunkyJava documentation
 * 
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public abstract class Comp<T> {

    protected T in1;
    
    protected T in2;
    
    protected int out;

    public Comp() {
        Compared<T> compared = Funky.getInput();
        in1 = compared.t1;
        in2 = compared.t2;
    }
}