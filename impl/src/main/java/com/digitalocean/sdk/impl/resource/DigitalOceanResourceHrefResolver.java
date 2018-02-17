/*
 * Copyright 2018 Okta, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.digitalocean.sdk.impl.resource;

import com.digitalocean.sdk.lang.Collections;
import com.digitalocean.sdk.resource.Resource;

import java.util.Map;


public class DigitalOceanResourceHrefResolver implements ResourceHrefResolver {

    private final ResourceHrefResolver halResourceHrefResolver = new HalResourceHrefResolver();

    @Override
    public <R extends Resource> String resolveHref(Map<String, ?> properties, Class<R> clazz) {
        String href = halResourceHrefResolver.resolveHref(properties, clazz);

        return href != null
                ? href
                : fixSelfHref(properties, clazz);
    }

    private <R extends Resource> String fixSelfHref(Map<String, ?> properties, Class<R> clazz) {

        // the AppUsers object does NOT contain a self link, in this case we need build it based on the 'app' link
        Map<String, ?> links = getMapValue(properties, "_links");
        if (links != null) {
        }

        return null;
    }

    private Map<String, ?> getMapValue(Map<String, ?> properties, String key) {
        if (!Collections.isEmpty(properties)) {
            return (Map<String, ?>) properties.get(key);
        }
        return null;
    }
}