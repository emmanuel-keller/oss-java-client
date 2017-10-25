/*
 * Copyright 2015-2017 OpenSearchServer Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.opensearchserver.client.v1;

import com.opensearchserver.client.JsonClient1;
import com.opensearchserver.client.common.AbstractApi;
import com.opensearchserver.client.v1.field.ResultField;
import com.opensearchserver.client.v1.field.ResultFieldList;
import com.opensearchserver.client.v1.field.SchemaField;
import com.qwazr.utils.StringUtils;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class FieldApi1 extends AbstractApi<JsonClient1> {

	public FieldApi1(JsonClient1 client) {
		super(client);
	}

	/**
	 * Get one field
	 *
	 * @param indexName The index name
	 * @param fieldName The field name
	 * @return The field details
	 */
	public ResultField getField(String indexName, String fieldName) throws IOException, URISyntaxException {
		return target.path("index")
				.path(indexName)
				.path("field")
				.path(fieldName)
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.get(ResultField.class);
	}

	/**
	 * Get the field list
	 *
	 * @param indexName The index name
	 * @return A list of fields
	 */
	public ResultFieldList getFields(String indexName) throws IOException, URISyntaxException {
		return target.path("index")
				.path(indexName)
				.path("field")
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.get(ResultFieldList.class);
	}

	/**
	 * Create or replace a field
	 *
	 * @param indexName The name of the index
	 * @param field     The field to create or update
	 */
	public void setField(String indexName, SchemaField field) throws IOException, URISyntaxException {
		expectTrue200(target.path("index")
				.path(indexName)
				.path("field")
				.path(field.name)
				.request()
				.put(Entity.json(field))
				.getStatus());
	}

	/**
	 * Create or update a set of fields
	 *
	 * @param indexName The name of the index
	 * @param fields    A list of fields
	 */
	public void setFields(String indexName, List<SchemaField> fields) throws IOException, URISyntaxException {
		expectTrue200(
				target.path("index").path(indexName).path("field").request().put(Entity.json(fields)).getStatus());
	}

	/**
	 * Delete a field from a schema
	 *
	 * @param indexName The name of the index
	 * @param fieldName The name of the field to delete
	 */
	public void deleteField(String indexName, String fieldName) throws IOException, URISyntaxException {
		expectTrue200false404(
				target.path("index").path(indexName).path("field").path(fieldName).request().delete().getStatus());
	}

	/**
	 * Set the default and unique field
	 *
	 * @param indexName    The name of the index
	 * @param defaultField The name of the default field
	 * @param uniqueField  The name of the unique field
	 */
	public void setDefaultUniqueField(String indexName, String defaultField, String uniqueField)
			throws IOException, URISyntaxException {
		expectTrue200(target.path("index")
				.path(indexName)
				.path("field")
				.queryParam("default", defaultField)
				.queryParam("unique", uniqueField)
				.request()
				.post(Entity.text(StringUtils.EMPTY))
				.getStatus());
	}
}
