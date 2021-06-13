package com.supportportal.service.impl;

import com.supportportal.domain.Agency;
import com.supportportal.domain.User;
import com.supportportal.repository.AgencyRepository;
import com.supportportal.repository.UserRepository;
import com.supportportal.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AgencyServiceImpl implements AgencyService {

    AgencyRepository agencyRepository;
    UserRepository userRepository;

    @Autowired
    public AgencyServiceImpl(AgencyRepository agencyRepository, UserRepository userRepository) {
        this.agencyRepository = agencyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Agency addNewAgency(String name, String coordsLong, String coordsLarg) {
        Agency agency = new Agency();
        agency.setName(name);
        agency.setCoordsLong(coordsLong);
        agency.setCoordsLarg(coordsLarg);
        agencyRepository.save(agency);
        return agency;
    }

    @Override
    public void deleteAgency(String name) {
    Agency agency = agencyRepository.findAgencyByName(name);
    agencyRepository.deleteById(agency.getIdAgency());
    }

    @Override
    public List<Agency> getAgencies() {
        List<Agency> agencies = agencyRepository.findAll();
        return agencies;
    }

    @Override
    public List<User> getAllUserByAgency(String name) {
        List<User> users = userRepository.findUsersByAgency_Name(name);
        return users;
    }
}
