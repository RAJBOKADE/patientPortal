package com.crossasyst.personregistration.service;

import com.crossasyst.personregistration.entity.EnterpriseEntity;
import com.crossasyst.personregistration.entity.PracticeEntity;
import com.crossasyst.personregistration.mapper.PracticeMapper;
import com.crossasyst.personregistration.model.Practice;
import com.crossasyst.personregistration.repository.EnterpriseRepository;
import com.crossasyst.personregistration.repository.PracticeRepository;
import com.crossasyst.personregistration.response.PracticeResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2

public class PracticeService {
    private final PracticeMapper practiceMapper;
    private final PracticeRepository practiceRepository;
    private final EnterpriseRepository enterpriseRepository;

    public PracticeService(PracticeMapper practiceMapper, PracticeRepository practiceRepository, EnterpriseRepository enterpriseRepository) {
        this.practiceMapper = practiceMapper;
        this.practiceRepository = practiceRepository;
        this.enterpriseRepository = enterpriseRepository;
    }

    public PracticeResponse createPractice(Practice practice, Long enterpriseId) {
        EnterpriseEntity enterpriseEntity = enterpriseRepository.findById(enterpriseId).get();
        List<PracticeEntity> practiceEntities = enterpriseEntity.getPracticeEntity();
        PracticeEntity practiceEntity = practiceMapper.modelToEntity(practice);
        practiceEntities.add(practiceEntity);
        enterpriseEntity.setEnterpriseId(enterpriseId);
        practiceRepository.save(practiceEntity);
        PracticeResponse practiceResponse = new PracticeResponse();
        practiceResponse.setPracticeId(practiceEntity.getId());
        log.info("Created Practice with PracticeId = {}", practiceResponse.getPracticeId());
        return practiceResponse;
    }


    public Practice getPracticeById(Long practiceId) {
        log.info("Searching Practices");
        Optional<PracticeEntity> practiceEntityOptional = practiceRepository.findById(practiceId);
        Practice practice = new Practice();
        if (practiceEntityOptional.isPresent()) {
            practice = practiceMapper.entityToModel(practiceEntityOptional.get());
            log.info("Fetched Practice Details Successfully");
        } else {
            log.info("Practice Details not found");
        }
        return practice;
    }

    public List<Practice> getAllPractices(Long enterpriseId) {
        EnterpriseEntity enterpriseEntity = enterpriseRepository.findById(enterpriseId).get();
        List<PracticeEntity> practiceEntities = enterpriseEntity.getPracticeEntity();
        List<Practice> practice = practiceMapper.allEntityToModel(practiceEntities);
        enterpriseEntity.setEnterpriseId(enterpriseId);
        PracticeResponse practiceResponse = new PracticeResponse();
        log.info("Practices found with EnterpriseId={}",enterpriseId);
        return practice;
    }


}
