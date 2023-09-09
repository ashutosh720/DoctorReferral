package com.example.DoctorReferral.Service;


import com.example.DoctorReferral.Model.Speciality;
import com.example.DoctorReferral.Model.Symptoms;
import com.example.DoctorReferral.Repositories.SpecialityRepository;
import com.example.DoctorReferral.Repositories.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SymptomService {
    @Autowired
    private SymptomRepository symptomsRepo;
    @Autowired
    private SpecialityRepository specialityRepo;
    public Symptoms addSymptom(Symptoms sym) {
        List<Speciality> bySpeciality = specialityRepo.findBySpeciality(String.valueOf(sym.getSpeciality()));
        Speciality s = bySpeciality.get(0);
        Symptoms symptoms = new Symptoms();
        symptoms.setSymptom(sym.getSymptom().toLowerCase());
        symptoms.setSpeciality(s);
        Symptoms save = symptomsRepo.save(symptoms);
        return save;
    }

    public List<Symptoms> getAllSymptoms() {
        return symptomsRepo.findAll();
    }
}

