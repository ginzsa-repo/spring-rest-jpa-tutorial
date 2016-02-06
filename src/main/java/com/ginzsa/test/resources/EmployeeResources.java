package com.ginzsa.test.resources;

import com.ginzsa.test.model.Employee;
import com.ginzsa.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by santiago.ginzburg on 2/2/16.
 */
@RestController
public class EmployeeResources {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/employees", method = RequestMethod.DELETE)
    public ResponseEntity<String>  deleteEmployees() {
        testService.deleteDepartment();
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public List<Employee>  getEmployees() {
        return testService.listEmployee();
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public Employee postEmployee(@RequestBody Employee employee) {
        Employee newEmployee = new Employee.Builder()
                .withFirstName(employee.getFirstName())
                .withLastName(employee.getLastName())
                .withDepartment(employee.getDepartment())
                .build();
        testService.createEmployee(newEmployee);
        return newEmployee;
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public Employee employee(@PathVariable("id") Long id) {
        return testService.findEmployee(id);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    public Employee updateEmployee(@RequestBody Employee employee) {

        return testService.updateEmployee (new Employee.Builder()
                .withId(employee.getId())
                .withFirstName(employee.getFirstName())
                .withLastName(employee.getLastName())
                .build());
    }
}