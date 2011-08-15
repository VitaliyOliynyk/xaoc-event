package eu.vitaliy.xaocevent;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Vitaliy Oliynyk
 */
public interface IEventQueue extends Serializable {

    void add(Object id, ObserverContext observer);

    List<ObserverContext> get(Object id);

    void raiseEvent(String eventID, Object eventArg, Object source, boolean fromAnnotatedObservable) throws Throwable;

}
