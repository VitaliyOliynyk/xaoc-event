package eu.vitaliy.xaocevent;

import java.io.Serializable;
import java.lang.reflect.Method;


/**
 *
 * @author Vitaliy Oliynyk
 */
public class ObserverContext implements Serializable {
    private Method method;
    private Object target;

    public ObserverContext(Method method, Object target) {
        this.method = method;
        this.target = target;
    }

    public ObserverContext() {
    }


    public void invoke(Object observableResult) throws Throwable
    {
        method.setAccessible(true);
        if(method.getParameterTypes().length > 0)
        {
            method.invoke(target, observableResult);
        }else{
            method.invoke(target);
        }
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

}
