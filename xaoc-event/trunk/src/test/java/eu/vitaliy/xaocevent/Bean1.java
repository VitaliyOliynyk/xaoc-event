/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.vitaliy.xaocevent;

import eu.vitaliy.xaocevent.annotation.Observable;
import eu.vitaliy.xaocevent.annotation.Observer;
import eu.vitaliy.xaocevent.annotation.WithObservers;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 *
 * @author Witalij
 */
@Component("bean1")
public class Bean1 implements IBean1{

    private boolean m2OK = false;

    public Bean1() {
    }

    @PostConstruct
    private void postConstruct(){}

    @Override
    @Observable("event1")
    public String m1() {
        System.out.println(getClass().getName()+".m1()");
        return "aaa";
    }

    @Observer("event1")
    private void m2(String s) {
        System.out.println(getClass().getName()+".m2() " + s);
        m2OK = true;
    }

    @Observer("event1")
    private void m3() {
        System.out.println(getClass().getName()+".m3() ");
    }

    public boolean isM2OK() {
        return m2OK;
    }

}
