package com.ginzsa.test.repo;

import com.ginzsa.test.model.Department;
import com.ginzsa.test.model.Employee;

import java.util.List;

/**
 * Created by santiago.ginzburg on 2/2/16.
 */
public interface TestDao {

    Long save(Department department);

    int deleteDepartments();

    List<Department> listDepartment();

    void saveDepartment(Department newDepartment);

    Department findDepartment(Long id);

    Department updateDepartment(Department department);


    Long save(Employee employee);

    int deleteEmployees();

    List<Employee> listEmployee();

    void saveEmployee(Employee newEmployee);

    Employee findEmployee(Long id);

    Employee updateEmployee(Employee employee);
}
