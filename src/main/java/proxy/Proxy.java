package proxy;

public class Proxy {
    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.setName("1");
        UserService userService = new UserServiceImpl();
        userService.save(user);
    }
}
