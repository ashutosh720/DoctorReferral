package com.example.DoctorReferral.Service;

import com.example.DoctorReferral.Model.Speciality;
import com.example.DoctorReferral.Repositories.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialityService {
    @Autowired
    private SpecialityRepository specialityRepo;

    public Speciality addSpeciality(Speciality specialityType) {
        Speciality speciality = new Speciality();
        speciality.setSpeciality_type(specialityType.getSpeciality_type());
        Speciality save = specialityRepo.save(speciality);
        return save;
    }


    public List<Speciality> getAllSpecialities() {
        return specialityRepo.findAll();
    }
}

