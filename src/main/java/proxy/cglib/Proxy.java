package proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import proxy.User;
import proxy.UserService;
import proxy.UserServiceImpl;

public class Proxy {
    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.setName("1");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(new UserServiceInterceptor());
        UserService userService = (UserService) enhancer.create();
        userService.save(user);
    }
}
