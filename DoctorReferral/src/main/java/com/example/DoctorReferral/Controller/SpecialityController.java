package com.example.DoctorReferral.Controller;

import com.example.DoctorReferral.Model.Speciality;
import com.example.DoctorReferral.Service.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speciality")
public class SpecialityController {
    @Autowired
    private SpecialityService service;
    @PostMapping("/addSpeciality")
    public Speciality addSpeciality(@RequestBody Speciality specialityType){
        Speciality speciality = service.addSpeciality(specialityType);
//        speciality.setDoctors_list(new ArrayList<>());
        return speciality;
    }

    @GetMapping("/getAllSpecialities")
    public List<Speciality> getAllSpeciality(){
        return service.getAllSpecialities();
    }
}

