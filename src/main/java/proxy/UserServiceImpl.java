package proxy;

public class UserServiceImpl implements UserService {
    @Override
    public void save(User user) {
        System.err.println("persist user in database..{" + user + "}");
    }
}
