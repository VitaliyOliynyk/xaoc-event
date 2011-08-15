package eu.vitaliy.xaocevent;

import org.springframework.context.ApplicationListener;

/**
 * Created by IntelliJ IDEA.
 * User: xaoc
 * Date: 15.08.11
 * Time: 23:10
 */
public class XaocEventSpringNotifier implements IXaocEventSpringNotifier {

    private IEventQueue eventQueue;

    public void onApplicationEvent(XaocEvent xaocEvent) {
         if(xaocEvent.getMetadata().isFromAnnotatedObservable())
         {
             return;
         }else{
             try {
                 eventQueue.raiseEvent(xaocEvent.getEventID(), xaocEvent.getEventData(), xaocEvent.getSource(), false );
             } catch (Throwable throwable) {
                throw new RuntimeException(throwable);
             }
         }


    }

    public void setEventQueue(IEventQueue eventQueue) {
        this.eventQueue = eventQueue;
    }
}
