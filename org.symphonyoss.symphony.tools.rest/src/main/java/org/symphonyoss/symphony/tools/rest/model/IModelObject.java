/*
 *
 *
 * Copyright 2017 Symphony Communication Services, LLC.
 *
 * Licensed to The Symphony Software Foundation (SSF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.symphonyoss.symphony.tools.rest.model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Nullable;

import org.symphonyoss.symphony.tools.rest.model.osmosis.IComponentProxy;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Includes an analog of org.eclipse.jface.viewers.ITreeContentProvider which
 * allows us to provide models from "pom-first land" for Eclipse based
 * UI consumption (the Eclipse UI plugins always have to be in 
 * "manifest-first land" so we don't want a dependency on org.eclipse.jface.*
 * from in here.
 * 
 * @author bruce.skingle
 *
 */
public interface IModelObject extends IComponentProxy
{
  static final String CONFIG_FILE_NAME = "config";
  static final String DOT_JSON         = ".json";
  
  boolean                 hasChildren();
  IModelObject[]          getChildren();
  
  /**
   * Returns the parent for the given element, or <code>null</code>
   * indicating that it is a top level node.
   *
   * @return the parent element, or <code>null</code> if it
   *   has none or if the parent cannot be computed
   */
  @Nullable IModelObject  getParent();
  
  String                  getTypeName();
  String                  getName();
  String                  getErrorText();
  void                    print(PrintWriter out);
  ObjectNode              toJson();
  void store(File configDir, String fileName) throws IOException;
  void store(File configDir) throws IOException;
}