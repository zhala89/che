/*
 * Copyright (c) 2012-2018 Red Hat, Inc.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Red Hat, Inc. - initial API and implementation
 */
package org.eclipse.che.api;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import java.io.File;
import org.eclipse.che.api.project.server.impl.RootDirPathProvider;
import org.testng.annotations.Test;

public class RootDirPathProviderTest {

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void test() {
    File root = new File("target");
    Injector injector =
        Guice.createInjector(
            new AbstractModule() {
              @Override
              protected void configure() {
                bind(File.class)
                    .annotatedWith(Names.named("che.user.workspaces.storage"))
                    .toInstance(root);
              }
            });

    RootDirPathProvider pathProvider = injector.getInstance(RootDirPathProvider.class);
    pathProvider.get();
  }
}
