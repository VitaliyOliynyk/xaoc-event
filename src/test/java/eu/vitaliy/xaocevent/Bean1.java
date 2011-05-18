package eu.vitaliy.xaocevent;

import eu.vitaliy.xaocevent.annotation.Observable;
import eu.vitaliy.xaocevent.annotation.Observer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author Vitaliy Oliynyk
 */
public class Bean1 implements IBean {

    private String senderArgument = null;
    private boolean receiveNamedEventWithoutArgument = false;
    @Qualifier("eu.vitaliy.xaocevent.EventQueue")
    @Autowired
    IEventQueue eventQueue;

    public Bean1() {
    }

    
    
    private String eventSenderImpl(String s) {
        System.out.println(getClass().getName()+".eventSender() " + eventQueue.getClass());
        return s;
    }

    @Observable(Events.EVENT1)
    public String eventSender(String s){
       return  eventSenderImpl(s);
    }

    @Observer(Events.EVENT1)
    private void eventReceiverWithArguments(String s) {
        System.out.println(getClass().getName()+".eventReceiverWithArguments() " + s);
        senderArgument = s;
    }

    @Observer(Events.EVENT1)
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
