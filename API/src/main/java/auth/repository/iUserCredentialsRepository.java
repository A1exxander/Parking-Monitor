package auth.repository;

import auth.entity.UserCredentials;
import org.springframework.data.repository.CrudRepository;

public interface iUserCredentialsRepository extends CrudRepository<UserCredentials, Integer> {
    UserCredentials findByEmailAddressEquals(String emailAddress);
}
