package com.crossasyst.personregistration.mapper;

import com.crossasyst.personregistration.entity.AddressEntity;
import com.crossasyst.personregistration.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressEntity modelToEntity (Address address);

}
