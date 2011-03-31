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

package org.apache.commons.proxy.invoker;

import org.apache.commons.proxy.util.AbstractTestCase;
import org.apache.commons.proxy.ProxyFactory;

/**
 * @since 1.1
 */
public class TestChainInvoker extends AbstractTestCase
{
    public void testSkipDefaultValues()
    {
        final ChainInvoker invoker = new ChainInvoker(new Object[]{new DefaultTester(), new NonDefaultTester()});
        Tester tester = (Tester)new ProxyFactory().createInvokerProxy(invoker, new Class[] { Tester.class });
        assertEquals(true,tester.booleanMethod());
        assertEquals(1, tester.byteMethod());
        assertEquals('1', tester.charMethod());
        assertEquals(1.0, tester.doubleMethod(), 0.0);
        assertEquals(1.0f, tester.floatMethod(), 0.0f);
        assertEquals(1, tester.intMethod());
        assertEquals(1, tester.longMethod());
        assertEquals(1, tester.shortMethod());
        assertEquals("One", tester.objectMethod());
    }

    public void testReturnDefaultValue()
    {
        final ChainInvoker invoker = new ChainInvoker(new Object[]{new DefaultTester(), new DefaultTester()});
        Tester tester = (Tester)new ProxyFactory().createInvokerProxy(invoker, new Class[] { Tester.class });
        assertEquals(false,tester.booleanMethod());
        assertEquals(0, tester.byteMethod());
        assertEquals(0, tester.charMethod());
        assertEquals(0.0, tester.doubleMethod(), 0.0);
        assertEquals(0.0f, tester.floatMethod(), 0.0f);
        assertEquals(0, tester.intMethod());
        assertEquals(0, tester.longMethod());
        assertEquals(0, tester.shortMethod());
        assertEquals(null, tester.objectMethod());
    }

    public static interface Tester
    {
        public int intMethod();

        public long longMethod();

        public short shortMethod();

        public byte byteMethod();

        public double doubleMethod();

        public float floatMethod();

        public boolean booleanMethod();

        public char charMethod();

        public Object objectMethod();
    }

    public class NonDefaultTester implements Tester
    {
        public boolean booleanMethod()
        {
            return true;
        }

        public byte byteMethod()
        {
            return 1;
        }

        public char charMethod()
        {
            return '1';
        }

        public double doubleMethod()
        {
            return 1.0;
        }

        public float floatMethod()
        {
            return 1.0f;
        }

        public int intMethod()
        {
            return 1;
        }

        public long longMethod()
        {
            return 1;
        }

        public Object objectMethod()
        {
            return "One";
        }

        public short shortMethod()
        {
            return 1;
        }
    }

    public class DefaultTester implements Tester
    {
        public boolean booleanMethod()
        {
            return false;
        }

        public byte byteMethod()
        {
            return 0;
        }

        public char charMethod()
        {
            return 0;
        }

        public double doubleMethod()
        {
            return 0;
        }

        public float floatMethod()
        {
            return 0;
        }

        public int intMethod()
        {
            return 0;
        }

        public long longMethod()
        {
            return 0;
        }

        public Object objectMethod()
        {
            return null;
        }

        public short shortMethod()
        {
            return 0;
        }
    }
}