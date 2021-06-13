package com.supportportal.resource;

import com.supportportal.domain.Account;
import com.supportportal.domain.Activity;
import com.supportportal.domain.HttpResponse;
import com.supportportal.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = {"/activity"})
public class ActivityResource {
    ActivityService activityService;

    @Autowired
    public ActivityResource(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping("/add")
    public ResponseEntity<Activity> addNewActivity(@RequestParam("adminId") String adminId,
                                                   @RequestParam("desc") String desc,
                                                   @RequestParam("type") String type){
        Activity newActivity = activityService.addNewActivity(adminId, desc, type);
        return new ResponseEntity<>(newActivity, OK);
    }
    @GetMapping("/list")
    public ResponseEntity<List<Activity>> getAll(){
        List<Activity> activities = activityService.getActivities();
        return new ResponseEntity<>(activities,OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpResponse> deleteActivities(){
        activityService.deleteActivities();
        return response(OK,"ALL ACTIVITIES DELETED");
    }

    @GetMapping("/{adminId}")
    public ResponseEntity<List<Activity>> getActivitiesByAdmin(@PathVariable("adminId") String adminId){
        List<Activity> activities = activityService.getActivitiesByAdmin(adminId);
        return new ResponseEntity<>(activities,OK);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }

}
