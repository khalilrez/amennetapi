package com.supportportal.service.impl;

import com.supportportal.domain.Activity;
import com.supportportal.domain.User;
import com.supportportal.repository.ActivityRepository;
import com.supportportal.service.ActivityService;
import com.supportportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {
    ActivityRepository activityRepository;
    UserService userService;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository, UserService userService) {
        this.activityRepository = activityRepository;
        this.userService = userService;
    }

    @Override
    public List<Activity> getActivities() {
        return activityRepository.findAll();
    }

    @Override
    public List<Activity> getActivitiesByAdmin(String username) {
        User user = userService.findUserByUsername(username);
        return activityRepository.findActivityByUser(user);
    }

    @Override
    public void deleteActivities() {
        activityRepository.deleteAll();
    }


    @Override
    public Activity addNewActivity(String adminId, String desc, String type) {
        Activity activity = new Activity();
        User user = userService.findUserByUsername(adminId);
        activity.setUser(user);
        activity.setDescription(desc);
        activity.setType(type);
        activityRepository.save(activity);
        return activity;
    }
}
