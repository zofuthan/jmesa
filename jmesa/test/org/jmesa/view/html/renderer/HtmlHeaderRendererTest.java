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
package org.jmesa.view.html.renderer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.jmesa.core.CoreContext;
import org.jmesa.core.CoreContextFactory;
import org.jmesa.core.CoreContextFactoryImpl;
import org.jmesa.core.PresidentsDao;
import org.jmesa.limit.Limit;
import org.jmesa.limit.LimitFactory;
import org.jmesa.limit.LimitFactoryImpl;
import org.jmesa.limit.RowSelect;
import org.jmesa.view.html.component.HtmlColumnImpl;
import org.jmesa.web.HttpServletRequestWebContext;
import org.jmesa.web.WebContext;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @since 2.0
 * @author Jeff Johnston
 */
public class HtmlHeaderRendererTest {
	private static final String ID = "pres";
	private static final int MAX_ROWS = 12;
	
	@Test
	public void getStyleClass() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		
		WebContext webContext = new HttpServletRequestWebContext(request);
		webContext.setParameterMap(getParameters());
		webContext.setLocale(Locale.US);
		
		CoreContext coreContext = createCoreContext(webContext);
		
		HtmlColumnImpl column = new HtmlColumnImpl("firstName");
		column.setWebContext(webContext);
		column.setCoreContext(coreContext);
		
		HtmlHeaderRendererImpl headerRenderer = new HtmlHeaderRendererImpl(column);
		headerRenderer.setWebContext(webContext);
		headerRenderer.setCoreContext(coreContext);

		String styleClass = headerRenderer.getStyleClass();
		assertNotNull(styleClass);
		assertTrue(styleClass.equals("header"));
	}

	public CoreContext createCoreContext(WebContext webContext) {
		Collection items = new PresidentsDao().getPresidents();

		LimitFactory limitFactory = new LimitFactoryImpl(ID, webContext);
		Limit limit = limitFactory.createLimit();
		RowSelect rowSelect = limitFactory.createRowSelect(MAX_ROWS, items.size());
		limit.setRowSelect(rowSelect);

		CoreContextFactory factory = new CoreContextFactoryImpl(webContext, false);
		CoreContext coreContext = factory.createCoreContext(items, limit);
		
		return coreContext;
	}
	
	private Map<?, ?> getParameters() {
		HashMap<String, Object> results = new HashMap<String, Object>();
		return results;
	}
}