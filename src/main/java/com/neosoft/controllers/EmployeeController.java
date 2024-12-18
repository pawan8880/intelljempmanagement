package com.neosoft.controllers;

import com.neosoft.dto.EmployeeProfileDTO;
import com.neosoft.entity.EmployeeProfile;
import com.neosoft.service.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
public class EmployeeController {


    @Autowired
    EmployeeServiceImpl employeeServiceImpl;
    @Autowired
    ModelMapper mapper;

    @PostMapping("/save")
    public ResponseEntity<EmployeeProfile> createRecord(@Valid  @RequestBody EmployeeProfile employeeProfile){
        EmployeeProfile empInsert =   employeeServiceImpl.insertRecord(employeeProfile);
        return ResponseEntity.status(HttpStatus.CREATED).body(empInsert);


    }

//    @PostMapping("/saveuser")
//    public ResponseEntity<EmployeeProfileDTO> createUserProfile(@Valid @RequestBody EmployeeProfileDTO userProfileDTO) {
//        try {
//            // Manually mapping DTO to entity
//            EmployeeProfile employeeProfile = new EmployeeProfile();
//
//            // Do not set employeeId during creation, let the database generate it
//            // employeeProfile.setEmployeeId(userProfileDTO.getEmployeeId()); // REMOVE this line
//
//            employeeProfile.setName(userProfileDTO.getName());
//           // employeeProfile.setAddress(userProfileDTO.getAddress());
//            employeeProfile.setPersonalPhone(userProfileDTO.getPersonalPhone());
//            employeeProfile.setWorkEmail(userProfileDTO.getWorkEmail());
//            employeeProfile.setDateOfBirth(userProfileDTO.getDateOfBirth());
//
//            // Insert the EmployeeProfile object, where the ID will be automatically generated
//            EmployeeProfile savedProfile = employeeServiceImpl.insertUserRecord(employeeProfile);
//
//            // Optionally map back to DTO if needed for the response
//            EmployeeProfileDTO savedProfileDTO = new EmployeeProfileDTO();
//            savedProfileDTO.setEmployeeId(savedProfile.getEmployeeId());  // This will now have the generated ID
//            savedProfileDTO.setName(savedProfile.getName());
//            savedProfileDTO.setWorkEmail(savedProfile.getWorkEmail());
//            //savedProfileDTO.setAddress(savedProfile.getAddress());
//            savedProfileDTO.setPersonalPhone(savedProfile.getPersonalPhone());
//            savedProfileDTO.setDateOfBirth(savedProfile.getDateOfBirth());
//
//            return ResponseEntity.status(HttpStatus.CREATED).body(savedProfileDTO);
//        } catch (Exception e) {
//            e.printStackTrace();  // Log the exception to trace the error
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }



    @GetMapping("getbyid/{id}")
    public ResponseEntity<EmployeeProfileDTO> getEmployeeProfileById(@PathVariable Long id) {
        EmployeeProfile employeeProfile = employeeServiceImpl.getEmployeeById(id);

        // Map Entity to DTO
        EmployeeProfileDTO employeeProfileDTO = mapper.map(employeeProfile,EmployeeProfileDTO.class );
//Model mapper ka use karne ke baad manuaalyy mapEntityToDTO(employeeProfile); ye method banane ki jarurat nahi hai.
      //  EmployeeProfileDTO employeeProfileDTO = mapEntityToDTO(employeeProfile);

        return ResponseEntity.ok().body(employeeProfileDTO);
    }

    // Helper method to map Entity to DTO (can be done with MapStruct for larger apps)
    private EmployeeProfileDTO mapEntityToDTO(EmployeeProfile employeeProfile) {
        EmployeeProfileDTO dto = new EmployeeProfileDTO();
        dto.setEmployeeId(employeeProfile.getEmployeeId());
        dto.setName(employeeProfile.getName());
       // dto.setAddress(employeeProfile.getAddress());
        dto.setPersonalPhone(employeeProfile.getPersonalPhone());
        dto.setWorkEmail(employeeProfile.getWorkEmail());
        dto.setDateOfBirth(employeeProfile.getDateOfBirth());
        // Continue mapping fields...
        return dto;

}
//get by id ki tarah yha bhi ModelMapper ka use karke
@DeleteMapping("deletebyid/{id}")
    public String delete(@PathVariable Long id){
       employeeServiceImpl.deleteById(id);
    return "Record Deleted By This id = "+id;
}

    @GetMapping("/getall")
    public List<EmployeeProfileDTO> getAllEmployeeProfiles() {
        // Fetch all employee profiles
        List<EmployeeProfile> employeeProfiles = employeeServiceImpl.getAllRecord();

        // Convert each EmployeeProfile to EmployeeProfileDTO
        List<EmployeeProfileDTO> employeeProfileDTOs = employeeProfiles.stream()
                .map(this::mapEntityToDTO)
                .toList();

        return employeeProfileDTOs;
    }


    @GetMapping("/greaterThan")
    public List<String> getEmployeesWithSalaryGreaterThan50000() {
        // Fetch all employee profiles
        List<EmployeeProfile> employeeProfiles = employeeServiceImpl.getAllRecordGreateThan50000();

        return employeeProfiles.stream().map(EmployeeProfile::getName).collect(Collectors.toList());
    }


}
