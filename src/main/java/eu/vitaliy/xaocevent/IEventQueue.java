package eu.vitaliy.xaocevent;

import java.util.List;

/**
 *
 * @author Vitaliy Oliynyk
 */
public interface IEventQueue {

    void add(Object id, ObserverContext observer);

    List<ObserverContext> get(Object id);

}
