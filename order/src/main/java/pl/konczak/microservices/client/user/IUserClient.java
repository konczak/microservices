package pl.konczak.microservices.client.user;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${client.user.name}",
             decode404 = true,
             fallback = UserClientFallback.class)
public interface IUserClient {

    @RequestMapping(method = RequestMethod.GET,
                    value = "/user/{id}")

    User getUser(@PathVariable("id") String id);

//    static class HystrixUserClientFallback
//            implements IUserClient {
//
//        @Override
//        public User getUser(String id) {
//            User user = new User();
//
//            user.setId(id);
//            user.setFirstname("Unknown");
//            user.setLastname("TBD by ID <" + id + ">");
//
//            return user;
//        }
//
//    }
}
