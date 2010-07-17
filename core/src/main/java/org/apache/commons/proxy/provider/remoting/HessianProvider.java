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

package org.apache.commons.proxy.provider.remoting;

import com.caucho.hessian.client.HessianProxyFactory;
import org.apache.commons.proxy.ObjectProvider;
import org.apache.commons.proxy.exception.ObjectProviderException;

import java.io.Serializable;
import java.net.MalformedURLException;

/**
 * Provides a hessian service object.
 * <p/>
 * <p>
 * <b>Dependencies</b>:
 * <ul>
 * <li>Hessian version 3.0.1 or greater</li>
 * </ul>
 * </p>
 *
 * @author James Carman
 * @since 1.0
 */
public class HessianProvider<T> implements ObjectProvider<T>, Serializable
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private Class<T> serviceInterface;
    private String url;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public HessianProvider()
    {
    }

    public HessianProvider( Class<T> serviceInterface, String url )
    {
        this.serviceInterface = serviceInterface;
        this.url = url;
    }

//**********************************************************************************************************************
// ObjectProvider Implementation
//**********************************************************************************************************************

    @SuppressWarnings("unchecked")
    public T getObject()
    {
        try
        {
            return (T)new HessianProxyFactory().create(serviceInterface, url);
        }
        catch( MalformedURLException e )
        {
            throw new ObjectProviderException("Invalid url given.", e);
        }
    }

//**********************************************************************************************************************
// Getter/Setter Methods
//**********************************************************************************************************************

    public void setServiceInterface( Class<T> serviceInterface )
    {
        this.serviceInterface = serviceInterface;
    }

    public void setUrl( String url )
    {
        this.url = url;
    }
}
