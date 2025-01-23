package enforcement.repository;

import enforcement.entity.Jurisdiction;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface iJurisdictionRepository extends CrudRepository<Jurisdiction, Integer> {
    public List<Jurisdiction> findAll();
    boolean existsByCityAndStateInitials(String city, String stateInitials);
}
