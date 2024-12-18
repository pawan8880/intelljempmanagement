package com.neosoft.service;

import com.neosoft.dto.EmployeeProfileDTO;
import com.neosoft.entity.EmployeeProfile;

import java.util.List;

public interface EmployeeService {

    public EmployeeProfile insertRecord(EmployeeProfile employeeProfile);
    public EmployeeProfile insertUserRecord(EmployeeProfile employeeProfile);
    public EmployeeProfile getEmployeeById(Long id);
    public void deleteById(Long id);
    public List<EmployeeProfile> getAllRecord();

    List<EmployeeProfile> getAllRecordGreateThan50000();
}
