package eu.vitaliy.xaocevent;

import eu.vitaliy.xaocevent.aspect.ObservableAspect;
import eu.vitaliy.xaocevent.aspect.ObserverAspect;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

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

public class XaocEventBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
    private final static String
            EVENT_QUEUE_CLASS_NAME = "eu.vitaliy.xaocevent.EventQueue",
            OBSERVABLE_ASPECT_CLASS_NAME = "eu.vitaliy.xaocevent.aspect.ObservableAspect",
            OBSERVER_ASPECT_CLASS_NAME = "eu.vitaliy.xaocevent.aspect.ObserverAspect",
            EVENT_QUEUE_PROPERTY = "eventQueue",
            SPRING_NOTIFIER = "eu.vitaliy.xaocevent.XaocEventSpringNotifier",
            BEAN_NAME = "xaoc-event-init";

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {


        BeanDefinitionRegistry bdf = parserContext.getRegistry();

        RootBeanDefinition beanDef = new RootBeanDefinition(EventQueue.class);
        beanDef.setScope(BeanDefinition.SCOPE_SINGLETON);
        bdf.registerBeanDefinition(EVENT_QUEUE_CLASS_NAME, beanDef);

        beanDef = new RootBeanDefinition(ObservableAspect.class);
        beanDef.setPropertyValues(getMutablePropertyWithEventQueues());

        bdf.registerBeanDefinition(OBSERVABLE_ASPECT_CLASS_NAME, beanDef);

        beanDef = new RootBeanDefinition(ObserverAspect.class);
        beanDef.setPropertyValues(getMutablePropertyWithEventQueues());
        bdf.registerBeanDefinition(OBSERVER_ASPECT_CLASS_NAME, beanDef);

        beanDef = new RootBeanDefinition(XaocEventSpringNotifier.class);
        beanDef.setPropertyValues(getMutablePropertyWithEventQueues());
        bdf.registerBeanDefinition(SPRING_NOTIFIER, beanDef);
    }


    private MutablePropertyValues getMutablePropertyWithEventQueues() {
        MutablePropertyValues mpv;
        mpv = new MutablePropertyValues();
        mpv.addPropertyValue(EVENT_QUEUE_PROPERTY, new RuntimeBeanReference(EVENT_QUEUE_CLASS_NAME));
        return mpv;
    }

    @Override
    protected String resolveId(Element element, AbstractBeanDefinition definition, ParserContext parserContext) throws BeanDefinitionStoreException {
        return BEAN_NAME;
    }

    @Override
    protected Class getBeanClass(Element element) {
        return AnnotationAwareAspectJAutoProxyCreator.class;
    }


}
