package enforcement.service;

import enforcement.repository.iJurisdictionRepository;
import enforcement.entity.Jurisdiction;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class JurisdictionService implements iJurisdictionService {

    @Autowired
    private iJurisdictionRepository jurisdictionRepository;

    @Override
    public List<Jurisdiction> getAll() {
        List<Jurisdiction> jurisdictions = jurisdictionRepository.findAll();
        return jurisdictions;
    }

    @Override
    public boolean isSupported(@Size(min = 2, max = 32) @NotNull String city, @Size(min = 2, max = 2) @NotNull String stateInitials) {
        return jurisdictionRepository.existsByCityAndStateInitials(city, stateInitials);
    }

}
