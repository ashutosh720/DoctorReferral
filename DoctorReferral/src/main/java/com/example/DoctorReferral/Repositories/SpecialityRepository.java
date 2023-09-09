package com.example.DoctorReferral.Repositories;

import com.example.DoctorReferral.Model.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpecialityRepository extends JpaRepository<Speciality,Long> {
    @Query(value = "Select * From speciality Where speciality_type= :speciality", countQuery = "Select count(*) From speciality", nativeQuery = true)
    public List<Speciality> findBySpeciality(String speciality);

}

