package enforcement.controller;

import enforcement.service.iJurisdictionService;
import enforcement.entity.Jurisdiction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/api/v1/jurisdictions")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class JurisdictionController implements iJurisdictionController {

    @Autowired
    private iJurisdictionService jurisdictionService;

    @Override
    @GetMapping("/supported/check")
    public ResponseEntity<Boolean> isSupported(@Size(min = 2, max = 32) @RequestParam(name = "city") @NotNull String city,
                                               @Size(min = 2, max = 2) @RequestParam(name = "state") @NotNull String stateInitials
    ) {
        return ResponseEntity.ok(jurisdictionService.isSupported(city, stateInitials));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Jurisdiction>> getAll() {
        return ResponseEntity.ok(jurisdictionService.getAll());
    }

}
