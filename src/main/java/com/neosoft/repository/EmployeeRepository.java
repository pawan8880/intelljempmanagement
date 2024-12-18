package com.neosoft.repository;

import com.neosoft.entity.EmployeeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeProfile,Long> {

   // List<EmployeeProfile> findBySalaryGreaterThan(Double salary);
}
