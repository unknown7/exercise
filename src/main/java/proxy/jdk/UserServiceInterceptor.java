package proxy.jdk;

import proxy.User;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UserServiceInterceptor implements InvocationHandler {
    private Object object;
    public UserServiceInterceptor(Object object) {
        this.object = object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (args.length > 0 && args[0] instanceof User) {
            User user = (User) args[0];
            if (user.getName().trim().length() <= 1)
                throw new RuntimeException("The variable [name] was too short");
        }
        Object invoke = method.invoke(object, args);
        System.err.println("invoke success..");
        return invoke;
    }
}
