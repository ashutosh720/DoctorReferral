package com.example.DoctorReferral.Model;

import com.example.DoctorReferral.Util.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String first_name;
    @NotBlank
    @NotNull
    private String last_name;
    @Length(max = 20, message = "Length of city should be less than 20 characters")
    private String city;

    @Email(message = "please provide valid email", regexp = "^[a-zA-Z0-9_+&*-]+(?:\\."+
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$")
    private String email;

    private String phone_number;
    @NotBlank
    private String address;

//    @NotBlank
//    private String speciality;

    @ManyToOne
    @JoinColumn
    private Speciality speciality;

    @OneToOne
    @JoinColumn
    private Status status;


}