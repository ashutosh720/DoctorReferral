package com.example.DoctorReferral.Controller;

import com.example.DoctorReferral.Exception.NotFoundException;
import com.example.DoctorReferral.Exception.NotValid;
import com.example.DoctorReferral.Model.Doctor;
import com.example.DoctorReferral.Service.DoctorService;
import io.micrometer.common.lang.NonNull;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping("/addDoctor")
    public Doctor addDoctor(@Valid @RequestBody Doctor doc) {
        return doctorService.addDoctor(doc);
    }

    @DeleteMapping("/deleteDoctorById")
    public ResponseEntity<String> deleteById(@RequestParam Long id) throws NotFoundException {
        doctorService.deleteById(id);
        return new ResponseEntity<>("Doctor deleted having id -> "+id, HttpStatus.OK);
    }

    @GetMapping("/getAllDoctors")
    public ResponseEntity<List<Doctor>> getAll(){
        return new ResponseEntity<>(doctorService.getAll(), HttpStatus.OK);
    }

    @PutMapping("/updateDoctor")
    public Doctor updateDoctor(@RequestBody Doctor doc,@NonNull @RequestParam Long doc_id) throws NotFoundException {
        return doctorService.updateDoctor(doc, doc_id);
    }

}

