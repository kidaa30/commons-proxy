/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.proxy2.provider.remoting;

import org.apache.commons.proxy2.exception.ObjectProviderException;
import org.apache.commons.proxy2.util.AbstractTestCase;
import org.apache.commons.proxy2.util.Echo;

public class TestHessianProvider extends AbstractTestCase
{
//**********************************************************************************************************************
// Other Methods
//**********************************************************************************************************************

    public void testSerialization()
    {
        final HessianProvider<Echo> p = new HessianProvider<Echo>();
        p.setServiceInterface(Echo.class);
        p.setUrl("a malformed URL");
        assertSerializable(p);
    }

    public void testWithMalformedUrl()
    {
        try
        {
            final HessianProvider<Echo> p = new HessianProvider<Echo>(Echo.class, "a malformed URL");
            p.getObject();
            fail();
        }
        catch( ObjectProviderException e )
        {
        }
    }

    public void testWithMalformedUrlBean()
    {
        try
        {
            final HessianProvider<Echo> p = new HessianProvider<Echo>();
            p.setServiceInterface(Echo.class);
            p.setUrl("a malformed URL");
            p.getObject();
            fail();
        }
        catch( ObjectProviderException e )
        {
        }
    }
}