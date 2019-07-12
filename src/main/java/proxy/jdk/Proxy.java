package proxy.jdk;

import proxy.User;
import proxy.UserService;
import proxy.UserServiceImpl;

public class Proxy {
    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.setName("1");
        UserService us = new UserServiceImpl();
        UserServiceInterceptor usi = new UserServiceInterceptor(us);
        UserService userService = (UserService) java.lang.reflect.Proxy.newProxyInstance(
                us.getClass().getClassLoader(),
                us.getClass().getInterfaces(),
                usi
        );
        userService.save(user);
    }
}
