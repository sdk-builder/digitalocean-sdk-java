/*
 * Copyright 2014 Stormpath, Inc.
 * Modifications Copyright 2018 Okta, Inc.
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
package com.digitalocean.sdk.impl.ds;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * @since 0.5.0
 */
public interface MapMarshaller {

    void marshal(OutputStream outputStream, Map map);

    Map<String, Object> unmarshal(InputStream inputStream, Map<String, String> linkMap);

}