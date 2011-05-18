package eu.vitaliy.xaocevent;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
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
    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        super.doParse(element, builder);       //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        super.doParse(element, parserContext, builder);
    }
}
