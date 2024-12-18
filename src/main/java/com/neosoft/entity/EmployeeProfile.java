package com.neosoft.entity;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeProfile {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long employeeId;

        @NotBlank(message = "Name cannot be blank")
        private String name;

        @Embedded
        private Address address;

        @NotBlank(message = "Personal phone number is required")
        @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be exactly 10 digits")
        private String personalPhone;

        @Email(message = "Invalid email address")
        @NotBlank(message = "Work email is required")
        private String workEmail;

       // @NotBlank(message = "Position is required")
        private String position;

       // @NotBlank(message = "Department is required")
        private String department;

        @PastOrPresent(message = "Date of joining cannot be in the future")
        private Date dateOfJoining;

        @Min(value = 0, message = "Salary must be positive")
        private double salary;

      //  @NotBlank(message = "Role is required")
        private String role;

       // @NotBlank(message = "Gender is required")
        private String gender;

      //  @NotBlank(message = "Nationality is required")
        private String nationality;

        @Past(message = "Date of birth must be in the past")
        private Date dateOfBirth;

       // @NotBlank(message = "Marital status is required")
        private String maritalStatus;

       // @NotBlank(message = "Employee type is required")
        private String employeeType; // Full-time, Part-time, Contract, etc.

       // @NotBlank(message = "Office location is required")
        private String officeLocation;

        @ElementCollection
        private List<String> benefits; // List of benefits (Health, Dental, etc.)


        private List<String> skills;


        private List<String> languagesSpoken; // List of languages

       @Size(min = 1, message = "Languages spoken list must have at least one entry")
        private List<String> companyAssetsAssigned; // List of assets like laptop, phone, etc.

      //  @PastOrPresent(message = "Create date cannot be in the future")
        private Date createDate;

      //  @PastOrPresent(message = "Update date cannot be in the future")
        private Date updateDate;

       // @NotBlank(message = "Created by is required")
        private String createdBy;

      //  @NotBlank(message = "Updated by is required")
        private String updatedBy;
    }
