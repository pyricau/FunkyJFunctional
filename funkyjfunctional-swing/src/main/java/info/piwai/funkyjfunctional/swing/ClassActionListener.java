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
package info.piwai.funkyjfunctional.swing;

import info.piwai.funkyjfunctional.ClassExecutorWithInput;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>
 * {@link ClassActionListener} is not part of the API, which is why it has
 * package-private scope.
 */
final class ClassActionListener<T extends ActL> implements ActionListener {

    private final ClassExecutorWithInput<T> executor;

    ClassActionListener(ClassExecutorWithInput<T> executor) {
        this.executor = executor;
    }

	@Override
	public void actionPerformed(ActionEvent event) {
		executor.createExecutedInstance(event);
	}
}
