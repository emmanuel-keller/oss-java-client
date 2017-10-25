/*
 * Copyright 2014-2017 OpenSearchServer Inc.
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
package com.opensearchserver.client;

import com.qwazr.utils.StringUtils;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * This client is for OpenSearchServer v1.5.x
 */
public class JsonClient1 {

	protected final Client client;
	protected final WebTarget target;

	public JsonClient1(final String baseUrl, final String login, final String key) throws URISyntaxException {

		this.client = ClientBuilder.newClient().register(JacksonJsonProvider.class);
		WebTarget target = client.target(new URI(baseUrl));
		if (!StringUtils.isBlank(login))
			target = target.queryParam("login", login.trim());
		if (!StringUtils.isBlank(key))
			target = target.queryParam("key", key.trim());
		this.target = target;
	}

	public WebTarget getTarget() {
		return target;
	}
}
