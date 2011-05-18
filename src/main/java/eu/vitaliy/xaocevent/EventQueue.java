package eu.vitaliy.xaocevent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 *
 * @author Vitaliy Oliynyk
 */
public class EventQueue implements IEventQueue {
    Map<Object, List<ObserverContext>> observerMap
            = new HashMap<Object, List<ObserverContext>>();

    public EventQueue() {
    }

    public void add(Object id, ObserverContext observer)
    {
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


    public List<ObserverContext>  get(Object id)
    {
        return observerMap.get(id);
    }



    
}
