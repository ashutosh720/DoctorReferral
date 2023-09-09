package com.example.DoctorReferral.Repositories;


import com.example.DoctorReferral.Model.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient,Long> {

        @Modifying
        @Transactional
        @Query(value = "Update patient set status_id=:status_id where id=:id", countQuery = "Select count(*) from patient", nativeQuery = true)
        public void deleteByIdChangeStatus(int status_id , Long id);

}


