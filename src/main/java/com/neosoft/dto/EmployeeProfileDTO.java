package com.neosoft.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.neosoft.entity.Address;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeProfileDTO {


    private Long employeeId;

    @NotNull( message=" Name can not be null")
    @Size(max = 100, message = "Name can have a maximum of 100 characters")
    private String name;

    // Address field validation could be handled in AddressDTO, if used separately.
    //@NotNull(message = "Address cannot be null")
   // @Embedded
  //  private Address address;

    @NotBlank(message = "Personal phone number cannot be empty")
    @Pattern(regexp = "\\+?[0-9\\- ]{7,15}", message = "Invalid phone number format")
    private String personalPhone;

    @NotBlank(message = "Work email cannot be empty")
    @Email(message = "Invalid email format")
    private String workEmail;



    @NotNull(message = "Date of birth cannot be null")
    @Past(message = "Date of birth must be in the past")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfBirth;




}
