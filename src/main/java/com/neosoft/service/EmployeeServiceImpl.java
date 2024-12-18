package com.neosoft.service;

import com.neosoft.entity.EmployeeProfile;
import com.neosoft.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl  implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;




    public EmployeeProfile insertRecord(EmployeeProfile employeeProfile){
        return  employeeRepository.save(employeeProfile);
    }
    public EmployeeProfile insertUserRecord(EmployeeProfile employeeProfile){
        return  employeeRepository.save(employeeProfile);
    }



    public EmployeeProfile getEmployeeById(Long id) {
        Optional<EmployeeProfile> employeeProfile = employeeRepository.findById(id);
        return employeeProfile.orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeProfile> getAllRecord() {
        return employeeRepository.findAll();
    }

    @Override
    public List<EmployeeProfile> getAllRecordGreateThan50000() {
        // Fetch all employees from the repository
        List<EmployeeProfile> employeeProfiles = employeeRepository.findAll();

        // Filter employees with salary greater than 50,000
        return employeeProfiles.stream()
                .filter(e -> e.getSalary() > 50000) // Assuming getSalary() returns a Double
                .collect(Collectors.toList());
    }
}
