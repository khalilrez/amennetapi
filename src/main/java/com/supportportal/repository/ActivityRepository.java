package com.supportportal.repository;

import com.supportportal.domain.Activity;
import com.supportportal.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity,Long> {

    List<Activity> findActivityByUser(User user);
    List<Activity> findActivityByCreatedAt(Date date);


}
