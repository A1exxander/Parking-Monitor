package com.citycite_api.enforcement.controller;

import com.citycite_api.enforcement.dto.JurisdictionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/jurisdictions/")
public interface iJurisdictionController {

    @GetMapping("supported/check")
    public ResponseEntity<Boolean> isSupported(@Size(min = 2, max = 32) @RequestParam(name = "city") @NotNull String city,
                                               @Size(min = 2, max = 2) @RequestParam(name = "state") @NotNull String stateInitials
    );

    @GetMapping
    public ResponseEntity<Set<JurisdictionResponse>> getAll();

    @GetMapping("search")
    ResponseEntity<Page<JurisdictionResponse>> findByCityStartingWith(
            @Size(min = 2, max = 32) @RequestParam(name = "city") @NotNull String city,
            @PageableDefault(page = 0, size = 10, sort = "city", direction = Sort.Direction.ASC) Pageable pageable
    );
}
