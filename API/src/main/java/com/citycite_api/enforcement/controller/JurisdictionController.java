package com.citycite_api.enforcement.controller;

import com.citycite_api.enforcement.dto.JurisdictionDTO;
import com.citycite_api.enforcement.service.iJurisdictionService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/v1/jurisdictions")
@Validated
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
    public ResponseEntity<Set<JurisdictionDTO>> getAll() {
        return ResponseEntity.ok(jurisdictionService.getAll());
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<Page<JurisdictionDTO>> findByCityStartingWith(
            @Size(min = 2, max = 32) @RequestParam(name = "city") @NotNull String city,
            @PageableDefault(page = 0, size = 10, sort = "city", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(jurisdictionService.findByCityStartingWith(pageable, city));
    }

}
