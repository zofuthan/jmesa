/*
 * Copyright 2004 original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jmesa.limit.state;

import org.jmesa.limit.Limit;
import org.jmesa.web.WebContext;

/**
 * <p>
 * Works like the current implementation in that you pass it a parameter 
 * telling it to keep the persisted state.
 * </p>
 * 
 * @since 2.0
 * @author Jeff Johnston
 */
public class NotifyToPersistState implements State {
	private final WebContext context;
	private final String id;
	private final String stateAttr;

	public NotifyToPersistState(WebContext context, String id, String stateAttr) {
		this.context = context;
		this.id = id;
		this.stateAttr = stateAttr;
	}

	public Limit retrieveLimit() {
		String stateAttrValue = context.getParameter(stateAttr);
		if ("true".equalsIgnoreCase(stateAttrValue)) {
			return (Limit) context.getSessionAttribute(id);
		}

		return null;
	}

	public void persistLimit(Limit limit) {
		context.setSessionAttribute(id, limit);
	}
}