package com.citycite_api.enforcement.service;

import com.citycite_api.enforcement.dto.OfficerResponse;
import com.citycite_api.enforcement.entity.Officer;
import com.citycite_api.enforcement.mapper.iOfficerMapper;
import com.citycite_api.enforcement.repository.iOfficerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
@Transactional
public class OfficerService implements iOfficerService {

    @Autowired
    private iOfficerRepository officerRepository;

    @Autowired
    private iOfficerMapper officerMapper;

    @Override
    public OfficerResponse getOfficerByID(Integer ID) {
        Officer officer = officerRepository.findById(ID).orElseThrow(() -> new NoSuchElementException("Officer with the ID " + ID + " not found."));
        return officerMapper.officerToOfficerResponse(officer);
    }

}
