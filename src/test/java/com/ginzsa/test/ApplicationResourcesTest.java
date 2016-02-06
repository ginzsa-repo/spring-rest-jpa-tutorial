package com.ginzsa.test;

import com.ginzsa.test.model.Department;
import com.ginzsa.test.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by santiago.ginzburg on 2/2/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest(randomPort = true)
public class ApplicationResourcesTest {

    @Value("${local.server.port}")
    private int port;

    private RestTemplate restTemplate = new TestRestTemplate();

    /**
     * Start checking helth status of the service
     * */
    @Test
    public void applicationHelthcheck() {
        ResponseEntity<String> entity = this.restTemplate
                .getForEntity("http://localhost:" + this.port + "/healthcheck", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    /**
     * List department after deleting content, assert that we start with an empty list
     * */
    @Test
    public void listDepartments() {
        //given an empty department table
        this.restTemplate
                .delete("http://localhost:" + this.port + "/departments");

        //when department endpoint service is hit
        ResponseEntity<List> entity = this.restTemplate
                .getForEntity("http://localhost:" + this.port + "/departments", List.class);

        //then I should get and ok status and a list object shouldn't be null
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertNotNull(entity.getBody());
    }

    /*
    *Create three department
    */
    @Test
    public void createThreeDepartments() {

        //given an empty department table
        this.restTemplate
                .delete("http://localhost:" + this.port + "/departments");

        //when I add three department
        List<Department> departments = Arrays.asList(
                new Department.Builder().withName("department one").build(),
                new Department.Builder().withName("department two").build(),
                new Department.Builder().withName("department three").build()
                );

        for(Department department: departments) {
            Department result = this.restTemplate
                    .postForObject("http://localhost:" + this.port + "/department", department, Department.class);
            assertNotNull(result);
        }
        //and I hit the /departments end point
        ResponseEntity<List> entity = this.restTemplate
                .getForEntity("http://localhost:" + this.port + "/departments", List.class);

        //then I should get and ok status and a list object shouldn't be empty
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(false, entity.getBody().isEmpty());
    }

    /*
     * Edit department, change name
     */
    @Test
    public void editDepartment() {
        //given after making sure that department table is empty and there is only one ...
        this.restTemplate
                .delete("http://localhost:" + this.port + "/departments");

        Department newDepartment = new Department.Builder().withName("department new").build();

        Department result = this.restTemplate
                .postForObject("http://localhost:" + this.port + "/department", newDepartment, Department.class);

        //when getting the new department id
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", result.getId().toString());

        ResponseEntity<Department> entity = this.restTemplate
                .getForEntity("http://localhost:" + this.port + "/department/{id}", Department.class, params);
        assertEquals(HttpStatus.OK, entity.getStatusCode());

        Department department = entity.getBody();
        assertNotNull(department);

        //and editing the department name
        department.setName("departmentNuevo");

        restTemplate.put("http://localhost:" + this.port + "/department", department);

        Department updatedDepartment = restTemplate
                .getForObject("http://localhost:" + this.port + "/department/{id}", Department.class, params);

        //then the updated department should have a new name
        assertEquals(updatedDepartment.getName(), "departmentNuevo");
    }

    /**
     * List employees after deleting content, assert that we start with an empty list
     * */
    @Test
    public void listEmployees() {
        //given an empty employee table
        this.restTemplate
                .delete("http://localhost:" + this.port + "/employees");

        //when employees endpoint service is hit
        ResponseEntity<List> entity = this.restTemplate
                .getForEntity("http://localhost:" + this.port + "/employees", List.class);

        //then I should get and ok status and a list object shouldn't be null
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertNotNull(entity.getBody());
    }

    /*
    *Create Employee
    */
    @Test
    public void createEmployee() {
        //given after making sure that department table is empty and there is one Department...
        this.restTemplate
                .delete("http://localhost:" + this.port + "/departments");

        Department newDepartment = new Department.Builder().withName("department new").build();

        Department result = this.restTemplate
                .postForObject("http://localhost:" + this.port + "/department", newDepartment, Department.class);

        //when Employee is created
        Employee employee = new Employee.Builder()
                .withFirstName("testEmployeeName")
                .withLastName("testEmployeeLastname")
                .withDepartment(result)
                .build();

        Employee newEmployee = this.restTemplate
                .postForObject("http://localhost:" + this.port + "/employee", employee, Employee.class);

        //then get User with department included
        assertEquals(employee.getFirstName(), newEmployee.getFirstName());
        assertEquals(employee.getLastName(), newEmployee.getLastName());
        assertNotNull(newEmployee.getDepartment());
        assertEquals(employee.getDepartment().getId(), newEmployee.getDepartment().getId());
    }

}
