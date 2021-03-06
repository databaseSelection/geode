/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.apache.geode.cache.lucene;

import org.apache.geode.cache.lucene.test.LuceneTestUtilities;
import org.apache.geode.test.dunit.SerializableRunnableIF;
import org.apache.geode.test.junit.categories.DistributedTest;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import junitparams.JUnitParamsRunner;

@Category(DistributedTest.class)
@RunWith(JUnitParamsRunner.class)
public class LuceneIndexCreationOnFixedPRDUnitTest extends LuceneIndexCreationDUnitTest {
  @Override
  protected void initDataStore(SerializableRunnableIF createIndex) throws Exception {
    createIndex.run();
    LuceneTestUtilities.initDataStoreForFixedPR(getCache());
  }

  protected void initDataStore(SerializableRunnableIF createIndex, String message)
      throws Exception {
    try {
      initDataStore(createIndex);
      fail("Should not have been able to create index");
    } catch (IllegalStateException e) {
      assertEquals(message, e.getMessage());
    }
  }

  protected String getClassSimpleName() {
    return getClass().getSuperclass().getSimpleName();
  }
}
