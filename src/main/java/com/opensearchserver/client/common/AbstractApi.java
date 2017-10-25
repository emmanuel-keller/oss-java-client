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
package com.opensearchserver.client.common;

import com.opensearchserver.client.JsonClient1;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.WebTarget;

public class AbstractApi<T extends JsonClient1> {

	protected final WebTarget target;

	public AbstractApi(T client) {
		this.target = client.getTarget();
	}

	protected boolean expectTrue200false404(int code) {
		switch (code) {
		case 200:
			return true;
		case 404:
			return false;
		default:
			throw new WebApplicationException(code);
		}
	}

	protected void expectTrue200(int code) {
		if (code != 200)
			throw new WebApplicationException(code);
	}
}
