/*
 * Copyright 2013-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.kubernetes.commons.config;

import java.util.Base64;
import java.util.Map;

import org.springframework.core.env.MapPropertySource;

/**
 * Kubernetes property source for secrets.
 *
 * @author l burgazzoli
 * @author Haytham Mohamed
 */
public class SecretsPropertySource extends MapPropertySource {

	private static final String PREFIX = "secrets";

	public SecretsPropertySource(String name, Map<String, Object> source) {
		super(name, source);
	}

	protected static String getSourceName(String name, String namespace) {
		return new StringBuilder().append(PREFIX).append(Constants.PROPERTY_SOURCE_NAME_SEPARATOR).append(name)
				.append(Constants.PROPERTY_SOURCE_NAME_SEPARATOR).append(namespace).toString();
	}

	protected static void putAll(Map<String, String> data, Map<String, Object> result) {
		if (data != null) {
			data.forEach((k, v) -> result.put(k, new String(Base64.getDecoder().decode(v)).trim()));
		}
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " {name='" + this.name + "'}";
	}

}
