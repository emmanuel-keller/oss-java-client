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
import com.opensearchserver.client.common.search.query.DocumentsQuery;
import com.opensearchserver.client.v1.search.DocumentsResult1;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @version For OpenSearchServer v1.5.x
 */
public class DocumentApi1 extends AbstractApi<JsonClient1> {

	public DocumentApi1(JsonClient1 client) {
		super(client);
	}

	/**
	 * Create or update a search field template
	 *
	 * @param indexName The name of the index
	 * @param query     The query
	 * @return the current instance
	 * @throws IOException        if any I/O error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public DocumentsResult1 documentsSearch(String indexName, DocumentsQuery query) {
		return target.path("index")
				.path(indexName)
				.path("documents")
				.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.json(query))
				.readEntity(DocumentsResult1.class);
	}

}
