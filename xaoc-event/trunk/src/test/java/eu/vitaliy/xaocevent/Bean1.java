package eu.vitaliy.xaocevent;

import eu.vitaliy.xaocevent.annotation.Observable;
import eu.vitaliy.xaocevent.annotation.Observer;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 *
 * @author Vitaliy Oliynyk
 */
@Component("bean1")
public class Bean1 implements IBean1{

    private String senderArgument = null;
    private boolean test2OK = false;

    public Bean1() {
    }

    @Override
    @Observable("event1")
    public String eventSender(String s) {
        System.out.println(getClass().getName()+".eventSender()");
        return s;
    }

    @Observer("event1")
    private void eventReceiverWithArguments(String s) {
        System.out.println(getClass().getName()+".eventReceiverWithArguments() " + s);
        senderArgument = s;
    }

    @Observer("event1")
    private void eventReceiverWithoutArguments() {
        System.out.println(getClass().getName()+".eventReceiverWithoutArguments() ");
         test2OK = true;
    }

    public boolean isTest2OK() {
       return test2OK;
    }

    public String getSenderArgument() {
        return senderArgument;
    }

}
