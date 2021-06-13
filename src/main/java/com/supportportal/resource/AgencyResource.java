package com.supportportal.resource;

import com.supportportal.domain.Agency;
import com.supportportal.domain.HttpResponse;
import com.supportportal.domain.User;
import com.supportportal.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = {"/agency"})
public class AgencyResource {
    AgencyService agencyService;

    @Autowired
    public AgencyResource(AgencyService agencyService) {
        this.agencyService = agencyService;
    }

    @PostMapping("/add")
    public ResponseEntity<Agency> addNewAgency(@RequestParam("name") String name,
                                               @RequestParam("coordsLong") String coordsLong,
                                               @RequestParam("coordsLarg") String coordsLarg)
    {
        Agency agency = agencyService.addNewAgency(name, coordsLong, coordsLarg);
        return new ResponseEntity<>(agency, OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Agency>> getAgencies(){
        List<Agency> agencies = agencyService.getAgencies();
        return new ResponseEntity<>(agencies, OK);
    }

    @DeleteMapping("/delete/{agencyName}")
    public ResponseEntity<HttpResponse> deleteAgency(@PathVariable("agencyName") String agencyName){
        agencyService.deleteAgency(agencyName);
        return response(OK,"AGENCY DELETED");
    }

    @GetMapping("/find/{agencyName}")
    public ResponseEntity<List<User>> getAllUsersByAgency(@PathVariable("agencyName") String agencyName){
        List<User> users = agencyService.getAllUserByAgency(agencyName);
        return new ResponseEntity<>(users, OK);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }
}
