package pl.konczak.microservices.client.user;

import org.springframework.stereotype.Component;

@Component
public class UserClientFallback
        implements IUserClient {

    @Override
    public User getUser(String id) {
        User user = new User();

        user.setId(id);
        user.setFirstname("Unknown");
        user.setLastname("TBD by ID <" + id + ">");

        return user;
    }

}
