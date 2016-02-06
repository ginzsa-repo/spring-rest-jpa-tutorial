package com.ginzsa.test.resources;

import com.ginzsa.test.model.Department;
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
public class DepartmentResources {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/departments", method = RequestMethod.DELETE)
    public ResponseEntity<String>  deleteDepartments() {
        testService.deleteDepartment();
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public List<Department>  getDepartments() {
        return testService.listDepartment();
    }

    @RequestMapping(value = "/department", method = RequestMethod.POST)
    public Department postDepartment(@RequestBody Department department) {
        Department newDepartment = new Department.Builder()
                .withName(department.getName())
                .build();
        testService.createDepartment(newDepartment);
        return newDepartment;
    }

    @RequestMapping(value = "/department/{id}", method = RequestMethod.GET)
    public Department department(@PathVariable("id") Long id) {
        return testService.findDepartment(id);
    }

    @RequestMapping(value = "/department", method = RequestMethod.PUT)
    public Department updateDepartment(@RequestBody Department department) {

        return testService.updateDepartment(new Department.Builder()
                .withId(department.getId())
                .withName(department.getName())
                .build());
    }
}