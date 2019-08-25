package dubbo;

import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component("provider")
public class ProviderImpl implements Provider {
    private static final User user;

    static {
        user = new User();
        user.setId(1);
        user.setName("Jeff");
        user.setGender(1);
    }

    private static class UserException extends RuntimeException {
        UserException(String message) {
            super(message);
        }
    }

    @Override
    public User getUser() {
//        throw new UserException("this is a dubbo exception.");
        try {
            TimeUnit.SECONDS.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return user;
    }
}
