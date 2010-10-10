/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.vitaliy.xaocevent;

import java.util.List;

/**
 *
 * @author Witalij
 */
public interface IEventQueue {

    void add(Object id, ObserverContext observer);

    List<ObserverContext> get(Object id);

}
