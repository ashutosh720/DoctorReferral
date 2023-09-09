package com.example.DoctorReferral.Service;

import com.example.DoctorReferral.Exception.AlreadyExistsException;
import com.example.DoctorReferral.Exception.NotFoundException;
import com.example.DoctorReferral.Exception.NotValid;
import com.example.DoctorReferral.Model.Patient;
import com.example.DoctorReferral.Model.Doctor;
import com.example.DoctorReferral.Model.Symptoms;
import com.example.DoctorReferral.Repositories.*;
import com.example.DoctorReferral.Util.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepo;
    @Autowired
    private DoctorRepository DoctorRepo;

    @Autowired
    private SymptomRepository symptomsRepo;
    @Autowired
    private StatusRepository statusRepo;

    @Autowired
    private SpecialityRepository specialityRepo;
    public Patient addPatient(Patient patient) throws NotValid {
        if(!Validations.isValidMobileNo(patient.getPhone_number())){
            throw new NotValid("Please consider providing valid phone number");
        }
        if(!Validations.isValidEmail(patient.getEmail())){
            throw new NotValid("Please consider providing valid email");
        }

        long existId=patient.getId();
        Optional<Patient> existingPat=patientRepo.findById(existId);
        if(existingPat.isEmpty()){
            throw new AlreadyExistsException(String.format("Song already exists in database: %s", patient.getName()));
        }


        Patient p = new Patient();
        p.setName(patient.getName());
        p.setPhone_number(patient.getPhone_number());
        p.setEmail(patient.getEmail());
        p.setCity(patient.getCity());
        p.setStatus(statusRepo.findById(1).get());
        Patient savedPatient = patientRepo.save(p);

        return savedPatient;
    }

    public Doctor getDoctor(String city, String symptom, boolean flag) {
        List<Symptoms> bySymptom = symptomsRepo.findBySymptom(symptom);
        if(!bySymptom.isEmpty()){
            Long id = bySymptom.get(0).getSpeciality().getId();
            List<Doctor> bySpecialityIdAndCity = DoctorRepo.findBySpecialityIdAndCity(id, city);
            if(!bySpecialityIdAndCity.isEmpty()){
                return bySpecialityIdAndCity.get(0);
            }else{
                throw new NullPointerException("No doctor is available in -> "+city+" -:- We are still waiting to expand to your location");
            }
        }
        List<String> byFirstLetter = symptomsRepo.findByFirstLetter(symptom.substring(0,2));
        if(!flag){
            throw new NullPointerException("Please check symptom spelling -> consider appropriate symptom from this list ->"+byFirstLetter.toString());
        }
        return null;
    }

    public Patient getPatientById(Long id) throws NotFoundException ,NotValid {
        if(!patientRepo.findById(id).isPresent()){
            throw new NotFoundException("No patient found with id -> "+id+" please consider providing valid id");
        }
        Patient p = patientRepo.findById(id).get();
        if(p.getStatus().getId()==1){
            return  p;
        }else{
            throw new NotValid("No patient found with id -> "+id+" please consider providing valid id");
        }

    }

    public Doctor getDoctorById(long id) throws NotFoundException ,NotValid{
        Doctor doc=null;
        if(!patientRepo.findById(id).isPresent()){
            throw new NotFoundException("No patient found with id -> "+id+" please consider providing valid id");
        }
        Patient p = patientRepo.findById(id).get();
        if(p.getStatus().getId()!=1){
            throw new NotValid("No patient found with id -> "+id+" please consider providing valid id");
        }
        String city = p.getCity();
        List<String>symptoms = p.getSymptom();
        boolean flag = true;
        if(!symptoms.isEmpty()) {
            for (String sym : symptoms) {
                Doctor doctorBySym = this.getDoctorBySym(sym);
                if(doctorBySym!=null){
                    doc = doctorBySym;
                }
                Doctor doctor = this.getDoctorForID(city.toLowerCase(), sym.toLowerCase());
                if (doctor!=null) {
                    flag = false;
                    return doctor;
                }
            }
        }
        if(flag && doc!=null) {
            throw new NullPointerException("No doctor found for patient having symptoms -> " + symptoms.toString()+"" +
                    "  But you can consider visiting this "+doc.toString()+" doctor in the city -> "+doc.getCity());
        }else if(flag){
            throw new NullPointerException("No doctor found for patient having symptoms -> " + symptoms.toString());
        }
        return null;
    }

    public void deleteById(Long id) throws NotValid {
        if(!patientRepo.findById(id).isPresent()){
            throw new NotValid("No patient found with id -> "+id+" please consider providing valid id");
        }
//        patientRepo.deleteById(id);
        patientRepo.deleteByIdChangeStatus(2, id);
    }


    public Doctor getDoctorForID(String city, String symptom) {
        List<Symptoms> bySymptom = symptomsRepo.findBySymptom(symptom);
        if (!bySymptom.isEmpty()) {
            Long id = bySymptom.get(0).getSpeciality().getId();
            List<Doctor> bySpecialityIdAndCity = DoctorRepo.findBySpecialityIdAndCity(id, city);
            if (!bySpecialityIdAndCity.isEmpty()) {
                return bySpecialityIdAndCity.get(0);
            }
        }
        return null;
    }

    public Doctor getDoctorBySym(String symptom) {
        List<Symptoms> bySymptom = symptomsRepo.findBySymptom(symptom);
        if (!bySymptom.isEmpty()) {
            Long id = bySymptom.get(0).getSpeciality().getId();
            List<Doctor> bySpecialityIdAndCity = DoctorRepo.findBySpecialityId(id);
            if (!bySpecialityIdAndCity.isEmpty()) {
                return bySpecialityIdAndCity.get(0);
            }
        }
        return null;
    }


    public List<Symptoms> getAlldata() {
        return symptomsRepo.getAllSymptoms();
    }
}
