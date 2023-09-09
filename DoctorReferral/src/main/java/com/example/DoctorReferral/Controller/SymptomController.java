package com.example.DoctorReferral.Controller;

import com.example.DoctorReferral.Model.Symptoms;
import com.example.DoctorReferral.Service.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/symptoms")
public class SymptomController {
    @Autowired
    private SymptomService service;
    @PostMapping("/addSymptoms")
    public Symptoms addSymptoms(@RequestBody Symptoms sym){
        Symptoms s = service.addSymptom(sym);
        return s;
    }

    @GetMapping("/getAllSymptomsAndSpeciality")
    public List<Symptoms> getAllSymptoms(){
        return service.getAllSymptoms();
    }
}
