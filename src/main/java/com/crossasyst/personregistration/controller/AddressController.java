package com.crossasyst.personregistration.controller;

import com.crossasyst.personregistration.model.Address;
import com.crossasyst.personregistration.response.AddressResponse;
import com.crossasyst.personregistration.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping(path = "/person/{personId}/addresses",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressResponse> createAddress (@RequestBody Address address , @PathVariable Long personId){
        AddressResponse addressResponse = addressService.createAddres(personId,address);
        return new ResponseEntity<>(addressResponse, HttpStatus.OK);
    }

}
