package eu.vitaliy.xaocevent;

import eu.vitaliy.xaocevent.annotation.Observable;
import eu.vitaliy.xaocevent.annotation.Observer;
import static org.fest.assertions.Assertions.*;
/**
 * Created by IntelliJ IDEA.
 * User: xaoc
 * Date: 19.05.11
 * Time: 11:12
 * To change this template use File | Settings | File Templates.
 */
public class BeanThreadSafe implements IBeanThreadSafe {

    @Observable(EventsList.EVENT_THREAD_SAFE)
    public long eventSender(long eventArg) {
        System.out.println(getClass().getName()+".eventSender() " + eventArg);
        return eventArg;
    }

    @Observer(EventsList.EVENT_THREAD_SAFE)
    private void eventReceiverWithArguments(long eventArg) {
        System.out.println(getClass().getName()+".eventReceiverWithArguments() " + eventArg);
        assertThat(eventArg).isEqualTo(Thread.currentThread().getId());
    }

}
