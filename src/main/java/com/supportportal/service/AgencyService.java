package com.supportportal.service;

import com.supportportal.domain.Agency;
import com.supportportal.domain.User;

import java.util.List;

public interface AgencyService {

    Agency addNewAgency(String name,String coordsLong,String coordsLarg);
    void deleteAgency(String name);
    List<Agency> getAgencies();
    List<User> getAllUserByAgency(String name);

}
