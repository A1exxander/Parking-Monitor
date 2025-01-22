package enforcement.entity;

import org.springframework.data.repository.CrudRepository;

public interface JurisdictionRepository extends CrudRepository<Integer, Jurisdiction> {
    boolean existsByCityAndStateInitials(String city, String stateInitials);
}
