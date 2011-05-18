package eu.vitaliy.xaocevent;

import eu.vitaliy.xaocevent.aspect.ObservableAspect;
import eu.vitaliy.xaocevent.aspect.ObserverAspect;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.parsing.CompositeComponentDefinition;
import org.springframework.beans.factory.support.*;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * Created by IntelliJ IDEA.
 * User: xaoc
 * Date: 16.05.11
 * Time: 12:56
 * To change this template use File | Settings | File Templates.
 */
public class XaocEventBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
    private final static String
            EVENT_QUEUE_CLASS_NAME = "eu.vitaliy.xaocevent.EventQueue",
            OBSERVABLE_ASPECT_CLASS_NAME = "eu.vitaliy.xaocevent.aspect.ObservableAspect",
            OBSERVER_ASPECT_CLASS_NAME = "eu.vitaliy.xaocevent.aspect.ObserverAspect",
            EVENT_QUEUE_PROPERTY = "eventQueue";


    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        super.doParse(element, builder);       //To change body of overridden methods use File | Settings | File Templates.

    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {


        BeanDefinitionRegistry bdf = parserContext.getRegistry();
        RootBeanDefinition beanDef = new RootBeanDefinition(EventQueue.class);
        bdf.registerBeanDefinition(EVENT_QUEUE_CLASS_NAME, beanDef);

        MutablePropertyValues mpv = new MutablePropertyValues();
        mpv.addPropertyValue("eventQueue", new RuntimeBeanReference(EVENT_QUEUE_CLASS_NAME));
        beanDef = new RootBeanDefinition(ObservableAspect.class);
        beanDef.setPropertyValues(mpv);

        bdf.registerBeanDefinition(OBSERVABLE_ASPECT_CLASS_NAME, beanDef);

        mpv = new MutablePropertyValues();
        mpv.addPropertyValue(EVENT_QUEUE_PROPERTY, new RuntimeBeanReference(EVENT_QUEUE_CLASS_NAME));
        beanDef = new RootBeanDefinition(ObserverAspect.class);
        beanDef.setPropertyValues(mpv);
        bdf.registerBeanDefinition(OBSERVER_ASPECT_CLASS_NAME, beanDef);


    }

    @Override
    protected String resolveId(Element element, AbstractBeanDefinition definition, ParserContext parserContext) throws BeanDefinitionStoreException {
        return "xaoc-event-init";
    }

    @Override
    protected Class getBeanClass(Element element) {
        return AnnotationAwareAspectJAutoProxyCreator.class;
    }


}
