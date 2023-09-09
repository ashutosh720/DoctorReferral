package com.example.DoctorReferral.Repositories;

import com.example.DoctorReferral.Util.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status,Integer> {
}
