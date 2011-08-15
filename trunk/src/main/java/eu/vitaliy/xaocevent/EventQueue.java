package eu.vitaliy.xaocevent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 *
 * @author Vitaliy Oliynyk
 */
public class EventQueue implements IEventQueue, ApplicationEventPublisherAware {
    ThreadLocal<HashMap<Object, List<ObserverContext>>> observerMapThreadLocal
                = new ThreadLocal<HashMap<Object, List<ObserverContext>>>(){
        @Override
        protected HashMap<Object, List<ObserverContext>> initialValue() {
            return new HashMap<Object, List<ObserverContext>>();
        }
    };

    private ApplicationEventPublisher publisher;

    public EventQueue() {
    }

    public void add(Object id, ObserverContext observer)
    {

        Map<Object, List<ObserverContext>> observerMap = getObserverMap();
        List<ObserverContext> observers;
        if(!observerMap.containsKey(id) || observerMap.get(id) == null)
        {
           observers = new  LinkedList<ObserverContext>();
           observerMap.put(id, observers);
        }else{
            observers = observerMap.get(id);
        }

        observers.add(observer);     
    }

    private HashMap<Object, List<ObserverContext>> getObserverMap() {
        return observerMapThreadLocal.get();
    }


    public List<ObserverContext>  get(Object id)
    {
        return getObserverMap().get(id);
    }

    public void raiseEvent(String eventID, Object eventArg, Object source, boolean fromAnnotatedObservable ) throws Throwable {
        List<ObserverContext> observers = get(eventID);
        if(observers != null)
        {
            for(ObserverContext observer : observers )
            {
                observer.invoke(eventArg);
            }
        }

        if(fromAnnotatedObservable)
        {
            sendToSpringEventQueue(eventID, eventArg, source);
        }


    }

    private void sendToSpringEventQueue(String eventID, Object eventArg, Object source) {
        XaocEvent xaocEvent = new XaocEvent(source, eventArg, eventID );
        XaocEvent.Metadata metadata = new XaocEvent.Metadata();
        metadata.setFromAnnotatedObservable(true);
        xaocEvent.setMetadata(metadata);
        publisher.publishEvent(xaocEvent);
    }


    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
