package com.example.DoctorReferral.Controller;

import com.example.DoctorReferral.Service.StatusService;
import com.example.DoctorReferral.Util.Status;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/status")
public class StatusController {

    @Autowired
    private StatusService service;
    @PostMapping("/addStatus")
    public Status addStatus(@Valid @RequestBody Status status){
        return service.createStatus(status);
    }
}

