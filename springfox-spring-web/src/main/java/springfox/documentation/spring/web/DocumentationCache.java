/*
 *
 *  Copyright 2015 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */

package springfox.documentation.spring.web;

import springfox.documentation.service.Documentation;
import springfox.documentation.spring.web.plugins.DocumentationPluginsBootstrapper;

import java.util.Collections;
import java.util.Map;

import static com.google.common.collect.Maps.*;

public class DocumentationCache {

  private final DocumentationPluginsBootstrapper documentationPluginsBootstrapper;

  public DocumentationCache(DocumentationPluginsBootstrapper documentationPluginsBootstrapper) {
    this.documentationPluginsBootstrapper = documentationPluginsBootstrapper;
  }

  public Documentation documentationByGroup(String groupName) {
    if (documentationPluginsBootstrapper.documentationLookup.isEmpty()){
      documentationPluginsBootstrapper.start();
    }
    Documentation documentation = documentationPluginsBootstrapper.documentationLookup.get(groupName);

    return documentation;
  }

  public Map<String, Documentation> all() {
    return Collections.unmodifiableMap(documentationPluginsBootstrapper.documentationLookup);
  }

  public void clear() {
    documentationPluginsBootstrapper.documentationLookup.clear();
  }
}
