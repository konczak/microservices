package pl.konczak.microservices.web.rest.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.konczak.microservices.domain.IUserBO;
import pl.konczak.microservices.domain.User;
import pl.konczak.microservices.domain.UserRepository;

@RestController
@RequestMapping("/user")
public class UserApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserApi.class);

    private final UserRepository userRepository;

    private final IUserBO userBO;

    @Autowired
    UserApi(UserRepository userRepository,
            IUserBO userBO) {
        this.userRepository = userRepository;
        this.userBO = userBO;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> list() {
        LOGGER.info("GET /user");
        return userRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET,
                    value = "/{id}")
    public HttpEntity<User> get(@PathVariable("id") String id) {
        LOGGER.info("GET /user/{}", id);
        User user = userRepository.findOne(id);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);
    }

    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity<User> add(@RequestBody UserAddCommand userAddCommand) {
        LOGGER.info("POST /user");
        User user = userBO.add(userAddCommand.getFirstname(), userAddCommand.getLastname());

        return ResponseEntity.ok(user);
    }

}
