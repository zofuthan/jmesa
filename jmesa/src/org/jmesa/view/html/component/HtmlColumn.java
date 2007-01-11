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
package org.jmesa.view.html.component;

import org.jmesa.view.component.Column;
import org.jmesa.view.html.renderer.HtmlCellRenderer;
import org.jmesa.view.html.renderer.HtmlHeaderRenderer;
import org.jmesa.view.renderer.FilterRenderer;

/**
 * @since 2.0
 * @author Jeff Johnston
 */
public interface HtmlColumn extends Column {
	public boolean isFilterable();
	
	public void setFilterable(boolean filterable);
	
	public boolean isSortable();
	
	public void setSortable(boolean sortable);
	
	public FilterRenderer getFilterRenderer();
	
	public void setFilterRenderer(FilterRenderer filterRenderer);
	
	public HtmlCellRenderer getCellRenderer();
	
	public HtmlHeaderRenderer getHeaderRenderer();
}