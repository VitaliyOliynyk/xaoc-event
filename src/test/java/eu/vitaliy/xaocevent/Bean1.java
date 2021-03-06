package eu.vitaliy.xaocevent;

import eu.vitaliy.xaocevent.annotation.Observable;
import eu.vitaliy.xaocevent.annotation.Observer;

/**
 *
 * @author Vitaliy Oliynyk
 */
public class Bean1 implements IBean {

    private String senderArgument = null;
    private boolean receiveNamedEventWithoutArgument = false;

    public Bean1() {
    }

    
    
    private String eventSenderImpl(String s) {
        System.out.println(getClass().getName()+".eventSender()");
        return s;
    }

    @Observable(EventsList.EVENT1)
    public String eventSender(String s){
       return  eventSenderImpl(s);
    }

    @Observer(EventsList.EVENT1)
    private void eventReceiverWithArguments(String s) {
        System.out.println(getClass().getName()+".eventReceiverWithArguments() " + s);
        senderArgument = s;
    }

    @Observer(EventsList.EVENT1)
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
