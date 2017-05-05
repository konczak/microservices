package pl.konczak.microservices.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserBO
        implements IUserBO {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserBO.class);

    private final UserRepository userRepository;

    UserBO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User add(String firstname, String lastname) {
        User user = new User(firstname, lastname);

        user = userRepository.save(user);

        LOGGER.info("Add User <{}> <{}> <{}>", user.getId(), user.getFirstname(), user.getLastname());

        return user;
    }
}
