package eu.vitaliy.xaocevent;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by IntelliJ IDEA.
 * User: xaoc
 * Date: 16.05.11
 * Time: 12:54
 * To change this template use File | Settings | File Templates.
 */
public class XaocEventNamespaceHandler extends NamespaceHandlerSupport {
    public void init() {
       registerBeanDefinitionParser("init", new XaocEventBeanDefinitionParser());
    }
}
