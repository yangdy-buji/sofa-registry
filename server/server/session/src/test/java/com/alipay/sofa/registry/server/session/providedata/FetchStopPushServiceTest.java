/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.registry.server.session.providedata;

import com.alipay.sofa.registry.common.model.ServerDataBox;
import com.alipay.sofa.registry.common.model.constants.ValueConstants;
import com.alipay.sofa.registry.common.model.metaserver.ProvideData;
import com.alipay.sofa.registry.server.session.bootstrap.SessionServerConfig;
import com.alipay.sofa.registry.server.session.provideData.FetchStopPushService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * @author xiaojian.xj
 * @version $Id: FetchStopPushServiceTest.java, v 0.1 2021年06月04日 19:09 xiaojian.xj Exp $
 */
public class FetchStopPushServiceTest extends FetchStopPushService {

  @Before
  public void beforeFetchStopPushServiceTest() {
    SessionServerConfig sessionServerConfig = mock(SessionServerConfig.class);

    this.setSessionServerConfig(sessionServerConfig);
  }

  @Test
  public void test() {
    Assert.assertTrue(isStopPushSwitch());

    Assert.assertTrue(
        doProcess(
            storage.get(),
            new ProvideData(
                new ServerDataBox("false"), ValueConstants.STOP_PUSH_DATA_SWITCH_DATA_ID, 2L)));
    Assert.assertEquals(isStopPushSwitch(), false);

    Assert.assertTrue(
        doProcess(
            storage.get(),
            new ProvideData(
                new ServerDataBox("true"), ValueConstants.STOP_PUSH_DATA_SWITCH_DATA_ID, 1L)));
    Assert.assertEquals(isStopPushSwitch(), true);
  }
}
