package com.crossasyst.personregistration.repository;

import com.crossasyst.personregistration.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository <AddressEntity, Long> {
}
