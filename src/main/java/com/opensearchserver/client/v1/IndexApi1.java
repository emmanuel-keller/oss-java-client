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
import com.opensearchserver.client.common.index.TemplateEnum;
import com.qwazr.utils.StringUtils;

import javax.ws.rs.client.Entity;

public class IndexApi1 extends AbstractApi<JsonClient1> {

	public IndexApi1(JsonClient1 client) {
		super(client);
	}

	/**
	 * Create a new index
	 *
	 * @param indexName The name of the index
	 * @param template  The template
	 */
	public void createIndex(String indexName, TemplateEnum template) {
		if (indexName == null || indexName.isEmpty())
			throw new IllegalArgumentException("The index name is missing.");
		expectTrue200(target.path("index")
				.path(indexName)
				.path("template")
				.path(template.name())
				.request()
				.post(Entity.text(StringUtils.EMPTY))
				.getStatus());
	}

	/**
	 * Check if the index exists.
	 *
	 * @param indexName The name of the index.
	 * @return true if the index exists, false if not.
	 */
	public boolean indexExists(String indexName) {
		if (indexName == null || indexName.isEmpty())
			throw new IllegalArgumentException("The index name is missing.");
		return expectTrue200false404(target.path("index").path(indexName).request().get().getStatus());
	}

	/**
	 * Delete index
	 *
	 * @param indexName The name of the index
	 */
	public void deleteIndex(String indexName) {
		if (indexName == null || indexName.isEmpty())
			throw new IllegalArgumentException("The index name is missing.");
		expectTrue200(target.path("index").path(indexName).request().delete().getStatus());
	}

}
