package com.crossasyst.personregistration.controller;

import com.crossasyst.personregistration.model.Enterprise;
import com.crossasyst.personregistration.response.EnterpriseResponse;
import com.crossasyst.personregistration.service.EnterpriseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
public class EnterpriseController {

    private final EnterpriseService enterpriseService;

    @Autowired
    public EnterpriseController(EnterpriseService enterpriseService) {
        this.enterpriseService = enterpriseService;
    }

    @PostMapping(path = "/enterprise", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EnterpriseResponse> createEnterprise(@RequestBody Enterprise enterprise) {
        EnterpriseResponse enterpriseResponse = enterpriseService.createEnterprise(enterprise);
        return new ResponseEntity<>(enterpriseResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/enterprise/{enterpriseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Enterprise> getEnterpriseById(@PathVariable Long enterpriseId) {
        Enterprise enterprise = enterpriseService.getEnterpriseById(enterpriseId);
        return new ResponseEntity<>(enterprise, HttpStatus.OK);
    }

    @GetMapping(path = "/enterprises", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Enterprise>> getall() {
        List<Enterprise> enterpriseList = enterpriseService.getAllEnterprise();
        log.info("Following is the List of all the Enterprises");
        return new ResponseEntity<>(enterpriseList, HttpStatus.OK);
    }

    @PutMapping(path = "/enterprise/{enterpriseId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateBookById(@PathVariable Long enterpriseId, @RequestBody Enterprise enterprise) {
        log.info("Updating Details of Enterprise");
        enterpriseService.updateEnterpriseById(enterpriseId, enterprise);
        return ResponseEntity.ok().build();
    }



}
