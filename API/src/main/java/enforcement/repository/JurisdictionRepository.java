package enforcement.repository;

import enforcement.entity.Jurisdiction;
import org.springframework.data.repository.CrudRepository;

public interface JurisdictionRepository extends CrudRepository<Integer, Jurisdiction> {
    boolean existsByCityAndStateInitials(String city, String stateInitials);
}
