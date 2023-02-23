package com.crossasyst.personregistration.service;

import com.crossasyst.personregistration.entity.PracticeEntity;
import com.crossasyst.personregistration.entity.PracticeStaffEntity;
import com.crossasyst.personregistration.mapper.PracticeMapper;
import com.crossasyst.personregistration.model.Staff;
import com.crossasyst.personregistration.repository.PracticeRepository;
import com.crossasyst.personregistration.repository.StaffRepository;
import com.crossasyst.personregistration.response.StaffResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StaffService {

    private final StaffRepository staffRepository;
    private final PracticeRepository practiceRepository;
    private final PracticeMapper practiceMapper;

    @Autowired
    public StaffService(StaffRepository staffRepository, PracticeRepository practiceRepository, PracticeMapper practiceMapper) {
        this.staffRepository = staffRepository;
        this.practiceRepository = practiceRepository;
        this.practiceMapper = practiceMapper;
    }

    public StaffResponse createStaff(Staff staff, Long practiceId) {
        PracticeEntity practiceEntity = practiceRepository.findById(practiceId).get();
        List<PracticeStaffEntity> practiceStaffEntity = practiceEntity.getPracticeStaffEntities();
        return new StaffResponse();
    }



}
