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

import java.io.ObjectStreamException;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public final class ThreadSafeInputHolder extends AbstractInputHolder implements InputHolder {
    
    public static final InputHolder INSTANCE = new ThreadSafeInputHolder();
    
    private static final long serialVersionUID = 1L;

    private transient final ThreadLocal<Object> holder = new ThreadLocal<Object>();
    
    private ThreadSafeInputHolder() {}
    
    @Override
    public void set(Object input) {
        holder.set(input);
    }

    @Override
    public <T> T get() {
        return this.<T>cast(holder.get());
    }
    
    private Object readResolve() throws ObjectStreamException {
        return INSTANCE;
    }

}
