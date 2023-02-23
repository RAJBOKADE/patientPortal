package com.crossasyst.personregistration.controller;

import com.crossasyst.personregistration.mapper.PracticeMapper;
import com.crossasyst.personregistration.model.Practice;
import com.crossasyst.personregistration.response.PracticeResponse;
import com.crossasyst.personregistration.service.PracticeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
public class PracticeController {

    private final PracticeService practiceService;
    private final PracticeMapper practiceMapper;

    @Autowired
    public PracticeController(PracticeService practiceService, PracticeMapper practiceMapper) {
        this.practiceService = practiceService;
        this.practiceMapper = practiceMapper;
    }

    @PostMapping(path = "enterprise/{enterpriseId}/practices", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PracticeResponse> createPractice(@RequestBody Practice practice, @PathVariable Long enterpriseId) {
        log.info("Creating Practice");
        PracticeResponse practiceResponse = practiceService.createPractice(practice, enterpriseId);
        return new ResponseEntity<>(practiceResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/practice/{practiceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Practice> getPracticeById(@PathVariable Long practiceId) {
        Practice practice = practiceService.getPracticeById(practiceId);
        return new ResponseEntity<>(practice, HttpStatus.OK);
    }

    @GetMapping(path = "/enterprise/{enterpriseId}/practices")
    public ResponseEntity<List<Practice>> getAllPractices(@PathVariable Long enterpriseId) {
        List<Practice> practiceList = practiceService.getAllPractices(enterpriseId);
        return new ResponseEntity<>(practiceList, HttpStatus.OK);
    }

}
