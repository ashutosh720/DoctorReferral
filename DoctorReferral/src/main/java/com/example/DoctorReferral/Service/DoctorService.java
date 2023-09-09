package com.example.DoctorReferral.Service;

import com.example.DoctorReferral.Exception.AlreadyExistsException;
import com.example.DoctorReferral.Exception.NotFoundException;
import com.example.DoctorReferral.Model.Doctor;
import com.example.DoctorReferral.Model.Speciality;
import com.example.DoctorReferral.Repositories.DoctorRepository;
import com.example.DoctorReferral.Repositories.SpecialityRepository;
import com.example.DoctorReferral.Repositories.StatusRepository;
import com.example.DoctorReferral.Util.Validations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class DoctorService {

    @Autowired
    private DoctorRepository DoctorRepo;
    @Autowired
    private SpecialityRepository specialityRepo;
    @Autowired
    private StatusRepository statusRepo;


    public Doctor addDoctor(Doctor doc) {

        long existId=doc.getId();
        Optional<Doctor> existingDoc=DoctorRepo.findById(existId);
        if(existingDoc.isEmpty()){
            throw new AlreadyExistsException(String.format("Song already exists in database: %s", doc.getFirst_name()));
        }

        Doctor d = new Doctor();
        d.setFirst_name(doc.getFirst_name());
        d.setLast_name(doc.getLast_name());
        d.setCity(doc.getCity().toLowerCase());
        d.setEmail(doc.getEmail());
        d.setAddress(doc.getAddress());
        List<Speciality> bySpeciality = specialityRepo.findBySpeciality(String.valueOf(doc.getSpeciality()));
        if(bySpeciality.isEmpty()){
            throw new NullPointerException("No Speciality Found With -> "+ doc.getSpeciality());
        }
        d.setSpeciality(bySpeciality.get(0));
        if(!Validations.isValidMobileNo(doc.getPhone_number())){
            throw new NullPointerException("Please provide valid phone number");
        }
        d.setPhone_number(doc.getPhone_number());
        d.setStatus(statusRepo.findById(1).get());
        Doctor newDoc = DoctorRepo.save(d);
        return newDoc;
    }


    public void deleteById(Long id) throws NotFoundException {
        if(DoctorRepo.findById(id).isPresent()) {
            DoctorRepo.deleteByIdChangeStatus(2, id);
        }else{
            throw new NotFoundException("No doctor found with id -> "+id+" please consider providing valid id");
        }
    }

    public List<Doctor> getAll() {
        return DoctorRepo.findAll();
    }

    public Doctor updateDoctor(Doctor doc, Long doc_id) throws NotFoundException {
        if(!DoctorRepo.findById(doc_id).isPresent()){
            throw new NotFoundException("No doctor found with id -> "+doc_id+" please consider providing valid id");
        }
        Doctor d = DoctorRepo.findById(doc_id).get();
        d.setFirst_name(doc.getFirst_name());
        d.setLast_name(doc.getLast_name());
        d.setCity(doc.getCity().toLowerCase());
        d.setEmail(doc.getEmail());
        d.setAddress(doc.getAddress());
        List<Speciality> bySpeciality = specialityRepo.findBySpeciality(String.valueOf(doc.getSpeciality()));
        if(bySpeciality.isEmpty()){
            throw new NullPointerException("No Speciality Found With -> "+ doc.getSpeciality());
        }
        d.setSpeciality(bySpeciality.get(0));
        if(!Validations.isValidMobileNo(doc.getPhone_number())){
            throw new NullPointerException("Please provide valid phone number");
        }
        d.setPhone_number(doc.getPhone_number());
        d.setStatus(statusRepo.findById(1).get());
        Doctor updoc = DoctorRepo.save(d);
        return updoc;
    }
}
