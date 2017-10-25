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

import javax.ws.rs.client.Entity;
import java.io.IOException;
import java.net.URISyntaxException;

public class DictionaryApi1 extends AbstractApi<JsonClient1> {

	public DictionaryApi1(JsonClient1 client) {
		super(client);
	}

	/**
	 * Create a list of synonyms
	 *
	 * @param indexName    The name of the index
	 * @param listName     Name of the list
	 * @param listSynonyms List of synonyms, one set of synonyms by line, raw text
	 */
	public void createSynonymsList(String indexName, String listName, String listSynonyms) {
		expectTrue200(target.path("index")
				.path(indexName)
				.path("synonyms")
				.path(listName)
				.request()
				.put(Entity.json(listSynonyms))
				.getStatus());
	}

	/**
	 * Check if a list of synonyms exists.
	 *
	 * @param indexName The name of the index.
	 * @param listName  The name of the list.
	 * @return true if the analyzer exists, false if not.
	 */
	public boolean checkSynonymsListExists(String indexName, String listName) throws IOException, URISyntaxException {
		return expectTrue200false404(
				target.path("index").path(indexName).path("synonyms").path(listName).request().head().getStatus());
	}

	/**
	 * Delete a synonym list.
	 *
	 * @param indexName The name of the index.
	 * @param listName  The name of the list.
	 */
	public void deleteSynonymsList(String indexName, String listName) {
		expectTrue200(
				target.path("index").path(indexName).path("synonyms").path(listName).request().delete().getStatus());
	}

	/**
	 * Create a list of stopwords
	 *
	 * @param indexName     The name of the index
	 * @param listName      Name of the list
	 * @param stopwordsList List of synonyms, one set of synonyms by line, raw text
	 */
	public void createStopWordsList(String indexName, String listName, String stopwordsList)
			throws IOException, URISyntaxException {
		expectTrue200(target.path("index")
				.path(indexName)
				.path("stopwords")
				.path(listName)
				.request()
				.put(Entity.json(stopwordsList))
				.getStatus());
	}

	/**
	 * Check if a list of stopwords exists.
	 *
	 * @param indexName The name of the index.
	 * @param listName  The name of the list.
	 * @return true if the analyzer exists, false if not.
	 */
	public boolean checkStopWordsListExists(String indexName, String listName) throws IOException, URISyntaxException {
		return expectTrue200false404(
				target.path("index").path(indexName).path("stopwords").path(listName).request().head().getStatus());
	}

	/**
	 * Delete a stopwords list.
	 *
	 * @param indexName The name of the index.
	 * @param listName  The name of the list.
	 * @throws IOException        if any IO error occurs
	 * @throws URISyntaxException if the URI is not valid
	 */
	public void deleteStopWordsList(String indexName, String listName) throws IOException, URISyntaxException {
		expectTrue200(
				target.path("index").path(indexName).path("stopwords").path(listName).request().delete().getStatus());
	}
}
