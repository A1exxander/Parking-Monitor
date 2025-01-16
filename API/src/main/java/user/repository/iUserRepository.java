package user.repository;

import org.springframework.data.repository.CrudRepository;
import user.entity.User;

public interface iUserRepository extends CrudRepository<User, Integer> {}
