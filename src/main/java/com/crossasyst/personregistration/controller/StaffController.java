package com.crossasyst.personregistration.controller;

import com.crossasyst.personregistration.model.Staff;
import com.crossasyst.personregistration.response.StaffResponse;
import com.crossasyst.personregistration.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class StaffController {
    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping(path = "/practices/{practiceId}/staff", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaffResponse> createStaff(@RequestBody Staff staff, @PathVariable Long practiceId) {
        StaffResponse staffResponse = staffService.createStaff(staff, practiceId);
        return new ResponseEntity<>(staffResponse, HttpStatus.OK);
    }
}
