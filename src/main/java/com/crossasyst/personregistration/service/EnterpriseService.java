package com.crossasyst.personregistration.service;

import com.crossasyst.personregistration.entity.EnterpriseEntity;
import com.crossasyst.personregistration.mapper.EnterpriseMapper;
import com.crossasyst.personregistration.model.Enterprise;
import com.crossasyst.personregistration.repository.EnterpriseRepository;
import com.crossasyst.personregistration.response.EnterpriseResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class EnterpriseService {

    private final EnterpriseRepository enterpriseRepository;
    private final EnterpriseMapper enterpriseMapper;

    @Autowired
    public EnterpriseService(EnterpriseRepository enterpriseRepository, EnterpriseMapper enterpriseMapper) {
        this.enterpriseRepository = enterpriseRepository;
        this.enterpriseMapper = enterpriseMapper;
    }

    public EnterpriseResponse createEnterprise(Enterprise enterprise) {
        log.info("Enrolling Enterprise");
        EnterpriseEntity enterpriseEntity = enterpriseMapper.modelToEntity(enterprise);
        enterpriseRepository.save(enterpriseEntity);
        log.info("Enterprise Enrolled Successfully");
        EnterpriseResponse enterpriseResponse = new EnterpriseResponse();
        enterpriseResponse.setEnterpriseId(enterpriseEntity.getEnterpriseId());
        return enterpriseResponse;
    }

    public Enterprise getEnterpriseById(Long enterpriseId) {
        log.info("Searching Enterprise");
        Optional<EnterpriseEntity> enterpriseEntityOptional = enterpriseRepository.findById(enterpriseId);
        Enterprise enterprise = new Enterprise();
        if (enterpriseEntityOptional.isPresent()) {
            enterprise = enterpriseMapper.entityToModel(enterpriseEntityOptional.get());
            log.info("Enterprise Details Found Successfully");
        } else {
            log.info("Enterprise details not available, Please enter correct Enterprise Id");
        }
        return enterprise;
    }

    public void updateEnterpriseById(Long enterpriseId, Enterprise enterprise) {
        Optional<EnterpriseEntity> enterpriseEntityOptional = enterpriseRepository.findById(enterpriseId);
        if (enterpriseEntityOptional.isPresent()) {
            EnterpriseEntity enterpriseEntity = enterpriseEntityOptional.get();
            enterpriseEntity = enterpriseMapper.modelToEntity(enterprise);
            enterpriseEntity.setEnterpriseId(enterpriseId);
            enterpriseRepository.save(enterpriseEntity);
            log.info("Update with enterpriseId {} successfull", enterpriseId);
        } else {
            log.info("enterpriseId {} not Found");
            
        }
    }

    public List<Enterprise> getAllEnterprise() {
        List<Enterprise> enterpriseList = enterpriseMapper.allEntityToModel(enterpriseRepository.findAll());
        log.info("Here is the list of all the Enterprises");
        return enterpriseList;
    }
}
