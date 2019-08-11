package dubbo;

import org.springframework.stereotype.Component;

@Component
public class ProviderImpl implements Provider {
    private static final User user;
    static {
        user = new User();
        user.setId(1);
        user.setName("Jeff");
        user.setGender(1);
    }
    @Override
    public User getUser() {
        return user;
    }
}
