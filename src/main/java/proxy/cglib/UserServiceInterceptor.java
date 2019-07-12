package proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import proxy.User;

import java.lang.reflect.Method;

public class UserServiceInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (objects.length > 0 && objects[0] instanceof User) {
            User user = (User) objects[0];
            if (user.getName().trim().length() <= 1)
                throw new RuntimeException("The variable [name] was too short");
        }
        Object invokeSuper = methodProxy.invokeSuper(o, objects);
        System.err.println("invoke success..");
        return invokeSuper;
    }
}
