package com.supportportal.repository;

import com.supportportal.domain.Agency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgencyRepository extends JpaRepository<Agency,Long> {
    Agency findAgencyByName(String name);
}
