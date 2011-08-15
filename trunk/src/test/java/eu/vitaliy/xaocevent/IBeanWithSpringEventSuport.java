package eu.vitaliy.xaocevent;

import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;

/**
 * Created by IntelliJ IDEA.
 * User: xaoc
 * Date: 15.08.11
 * Time: 22:55
 */
public interface IBeanWithSpringEventSuport extends IBean, ApplicationEventPublisherAware, ApplicationListener<XaocEvent> {
     String eventSenderViaSpringEventQueue(String s);
     String getSenderArgumentFromSpring();
     boolean isReceiveNamedEventWithoutArgumentFromSpring();
}
