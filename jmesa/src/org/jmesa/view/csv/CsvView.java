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
package org.jmesa.view.csv;

import java.util.Collection;
import java.util.List;

import org.jmesa.core.CoreContext;
import org.jmesa.view.View;
import org.jmesa.view.component.Column;
import org.jmesa.view.component.Table;

/**
 * @since 2.0
 * @author Jeff Johnston
 */
public class CsvView implements View {
	private Table table;
	private CoreContext coreContext;
	
	public CsvView(Table table, CoreContext coreContext) {
		this.table = table;
		this.coreContext = coreContext;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Object render() {
		StringBuilder data = new StringBuilder();
		
		List<Column> columns = table.getRow().getColumns();
		
		int rowcount = 0;
		Collection items = coreContext.getPageItems();
		for (Object item : items) {
			rowcount++;
			
			for (Column column : columns) {
				data.append(column.getCellRenderer().render(item, rowcount));
			}

			data.append("\r\n");
		}
		
		return data.toString();
	}
}