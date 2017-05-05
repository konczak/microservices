package pl.konczak.microservices.domain;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository
        extends MongoRepository<User, String> {

    User findByFirstname(String firstname);

    List<User> findByLastname(String lastname);
}
