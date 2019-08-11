package dubbo;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Consumer {
    @Resource
    private Provider provider;

    public User getUser() {
        return provider.getUser();
    }
}
