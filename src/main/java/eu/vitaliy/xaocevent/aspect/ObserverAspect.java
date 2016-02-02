/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.vitaliy.xaocevent.aspect;

import eu.vitaliy.xaocevent.IEventQueue;
import eu.vitaliy.xaocevent.MetaDataContext;
import eu.vitaliy.xaocevent.ObserverContext;
import eu.vitaliy.xaocevent.annotation.Observer;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * @author Vitaliy Oliynyk
 */

@Aspect
public class ObserverAspect implements Serializable {

    private Map<MetaDataContext, Boolean> isCheck = new ConcurrentHashMap<MetaDataContext, Boolean>();

    private IEventQueue eventQueue;

    public ObserverAspect() {
    }

    @Pointcut("execution(* *.*(..)) && target(target)")
    public void allObserverMethods(Object target){}

    @After("allObserverMethods(target)")
    public void allObserverMethodsInterceptor(Object target) throws Throwable
    {
        Class clazz = target.getClass();
        MetaDataContext mctx = new MetaDataContext(target, clazz);
        
        Boolean isProcess = isCheck.get(mctx);
        if(isProcess != null && isProcess)
        {
            return;
        }
        
        findObservers(clazz, target);
        isCheck.put(mctx, true);
    }

    private void findObservers(Class clazz, Object target) throws SecurityException {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            Observer observer = m.getAnnotation(Observer.class);
            if (observer != null) {
                ObserverContext observerContext = new ObserverContext(m, target);
                eventQueue.add(observer.value(), observerContext);
            }
        }
    }

    public void setEventQueue(IEventQueue eventQueue) {
        this.eventQueue = eventQueue;
    }
}
