package com.example.DoctorReferral.Service;

import com.example.DoctorReferral.Repositories.StatusRepository;
import com.example.DoctorReferral.Util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepo;

    public Status createStatus(Status status) {
        Status save = statusRepo.save(status);
        return save;
    }
}
