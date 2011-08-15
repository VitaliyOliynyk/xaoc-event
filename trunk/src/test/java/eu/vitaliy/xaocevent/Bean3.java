package eu.vitaliy.xaocevent;

import eu.vitaliy.xaocevent.annotation.Observer;
import org.springframework.context.ApplicationEventPublisher;

/**
 * Created by IntelliJ IDEA.
 * User: xaoc
 * Date: 15.08.11
 * Time: 22:51
 * To change this template use File | Settings | File Templates.
 */
public class Bean3 implements IBeanWithSpringEventSuport {

    private String senderArgument = null;
    private boolean receiveNamedEventWithoutArgument = false;

    private String senderArgumentFromSpring = null;
    private boolean receiveNamedEventWithoutArgumentFromSpring = false;
    private ApplicationEventPublisher publisher;

    public Bean3() {
    }

    public String eventSender(String s) {
        return null;
    }

    @Observer(EventsList.SPRING_EVENT_ID1)
    private void eventReceiverWithArguments(String s) {
        System.out.println(getClass().getName()+".eventReceiverWithArguments() " + s);
        senderArgument = s;
    }

    @Observer(EventsList.SPRING_EVENT_ID1)
    private void eventReceiverWithoutArguments() {
        System.out.println(getClass().getName()+".eventReceiverWithoutArguments() ");
        receiveNamedEventWithoutArgument = true;
    }

    public String getSenderArgument() {
        return senderArgument;
    }

    public boolean isReceiveNamedEventWithoutArgument() {
        return receiveNamedEventWithoutArgument;
    }

    public String eventSenderViaSpringEventQueue(String s) {
        publisher.publishEvent(new XaocEvent(this, s, EventsList.SPRING_EVENT_ID1));
        return s;
    }

    public String getSenderArgumentFromSpring() {
        return senderArgumentFromSpring;
    }

    public boolean isReceiveNamedEventWithoutArgumentFromSpring() {
        return receiveNamedEventWithoutArgumentFromSpring;
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void onApplicationEvent(XaocEvent xaocEvent) {
        senderArgumentFromSpring = (String)xaocEvent.getEventData();
        receiveNamedEventWithoutArgumentFromSpring = true;
    }
}
