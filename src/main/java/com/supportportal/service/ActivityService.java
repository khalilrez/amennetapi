package com.supportportal.service;

import com.supportportal.domain.Activity;
import com.supportportal.domain.User;

import java.util.List;

public interface ActivityService {

    List<Activity> getActivities();
    List<Activity> getActivitiesByAdmin(String username);
    void deleteActivities();
    Activity addNewActivity(String adminId,String desc,String type);

}
