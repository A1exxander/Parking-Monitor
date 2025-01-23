package com.citycite_api.enforcement.service;

import com.citycite_api.enforcement.entity.Jurisdiction;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Service
@Transactional
public interface iJurisdictionService {
    public List<Jurisdiction> getAll();
    public boolean isSupported(@Size(min = 2, max = 32) @NotNull String city, @Size(min = 2, max = 2) @NotNull String stateInitials);
}
