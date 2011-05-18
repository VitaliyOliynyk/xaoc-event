package eu.vitaliy.xaocevent;

import eu.vitaliy.xaocevent.annotation.Observable;
import eu.vitaliy.xaocevent.annotation.Observer;
import org.springframework.stereotype.Component;

/**
 *
 * @author Vitaliy Oliynyk
 */
public class Bean2 implements IBean {

    private String senderArgument = null;
    private boolean receiveNamedEventWithoutArgument = false;

    public Bean2() {
    }

    
    
    private String eventSenderImpl(String s) {
        System.out.println(getClass().getName()+".eventSender()");
        return s;
    }

    @Observable
    public String eventSender(String s){
       return  eventSenderImpl(s);
    }

    @Observer(Events.EVENT_SENDER)
    private void eventReceiverWithArguments(String s) {
        System.out.println(getClass().getName()+".eventReceiverWithArguments() " + s);
        senderArgument = s;
    }

    @Observer(Events.EVENT_SENDER)
    private void eventReceiverWithoutArguments() {
        System.out.println(getClass().getName()+".eventReceiverWithoutArguments() ");
        receiveNamedEventWithoutArgument = true;
    }

    public boolean isReceiveNamedEventWithoutArgument() {
       return receiveNamedEventWithoutArgument;
    }

    public String getSenderArgument() {
        return senderArgument;
    }

}
