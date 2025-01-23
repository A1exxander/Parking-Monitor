package com.citycite_api.enforcement.controller;

import com.citycite_api.enforcement.dto.JurisdictionResponse;
import com.citycite_api.enforcement.entity.Jurisdiction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/jurisdictions")
public interface iJurisdictionController {

    @GetMapping("/supported/check")
    public ResponseEntity<Boolean> isSupported(@Size(min = 2, max = 32) @RequestParam(name = "city") @NotNull String city,
                                               @Size(min = 2, max = 2) @RequestParam(name = "state") @NotNull String stateInitials
    );

    @GetMapping
    public ResponseEntity<Set<JurisdictionResponse>> getAll();

}
