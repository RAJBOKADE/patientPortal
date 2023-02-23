package com.crossasyst.personregistration.service;

import com.crossasyst.personregistration.entity.AddressEntity;
import com.crossasyst.personregistration.entity.PersonEntity;
import com.crossasyst.personregistration.mapper.AddressMapper;
import com.crossasyst.personregistration.model.Address;
import com.crossasyst.personregistration.repository.AddressRepository;
import com.crossasyst.personregistration.response.AddressResponse;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;

    public AddressService(AddressMapper addressMapper, AddressRepository addressRepository) {
        this.addressMapper = addressMapper;
        this.addressRepository = addressRepository;
    }

//    public AddressResponse createAddress(Address address) {
//        AddressEntity addressEntity = null;
////        PersonEntity personEntity = addressEntity.getPersonEntity();
////        Long personId = personEntity.getPersonId();
//        addressEntity = addressMapper.modelToEntity(address);
//        addressRepository.save(addressEntity);
//        AddressResponse addressResponse = new AddressResponse();
//        addressResponse.setAddressId(addressEntity.getAddressId());
//        return addressResponse;
//    }

    public AddressResponse createAddres(Long personId, Address address) {
        AddressEntity addressEntity = null;
//        PersonEntity personEntity = addressEntity.getPersonEntity();
//        Long personId = personEntity.getPersonId();
        addressEntity = addressMapper.modelToEntity(address);
        addressRepository.save(addressEntity);
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setAddressId(addressEntity.getAddressId());
        return addressResponse;
    }
}
