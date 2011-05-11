/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.vitaliy.xaocevent.aspect;

import eu.vitaliy.xaocevent.IEventQueue;
import eu.vitaliy.xaocevent.ObserverContext;
import eu.vitaliy.xaocevent.annotation.Observable;
import java.lang.reflect.Method;
import java.util.List;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author Witalij
 */
@Aspect
@Component("eu.vitaliy.xaocevent.aspect.ObservableAspect")
public class ObservableAspect {

    @Autowired
    @Qualifier("eu.vitaliy.xaocevent.EventQueue")
    private IEventQueue eventQueue;

    @Pointcut("execution(@eu.vitaliy.xaocevent.annotation.Observable * *.*(..)) ")
    public void observablePointcut(){}


    @Around("observablePointcut()")
    public Object observablePointcutAround(ProceedingJoinPoint pjp) throws Throwable
    {
        Object result = pjp.proceed();
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        String methodName = methodSignature.getMethod().getName();
        Class clazz = pjp.getTarget().getClass();
        Method method = clazz.getDeclaredMethod(methodName, methodSignature.getMethod().getParameterTypes());
        Observable observable = method.getAnnotation(Observable.class);
        String eventKey = observable.value();
        if("".equals(eventKey))
        {
            eventKey = method.getName();
        }
        List<ObserverContext> observers = eventQueue.get(eventKey);
        if(observers != null)
        {
            for(ObserverContext observer : observers )
            {
                observer.invoke(result);
            }
        }
        return result;
    }

}
