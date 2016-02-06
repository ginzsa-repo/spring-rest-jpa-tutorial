package com.ginzsa.test.repo;

import com.ginzsa.test.model.Department;
import com.ginzsa.test.model.Employee;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by santiago.ginzburg on 2/2/16.
 */
@Component
@Transactional
public class TestDaoImpl implements TestDao {

    @PersistenceContext
    private EntityManager em;

    public int deleteDepartments() {
        return em.createQuery("DELETE FROM Department").getFirstResult();
    }

    public Long save(Department department) {
        em.persist(department);
        return department.getId();
    }

    public List<Department> listDepartment() {
        return em.createQuery("FROM Department", Department.class).getResultList();
    }

    @Override
    public void saveDepartment(Department newDepartment) {
        save(newDepartment);
    }

    public Department findDepartment(Long id) {
        List<Department> departments = em.createQuery("SELECT d FROM Department d WHERE d.id = :id")
                .setParameter("id", id)
                .getResultList();

        if (departments.isEmpty()) {
            return  null;
        } else {
            return departments.get(0);
        }
    }

    @Override
    public Department updateDepartment(Department department) {
        return  em.merge(department);
    }

    @Override
    public Long save(Employee employee) {
        em.persist(employee);
        return employee.getId();
    }

    @Override
    public int deleteEmployees() {
        return em.createQuery("DELETE FROM Employee").getFirstResult();
    }

    @Override
    public List<Employee> listEmployee() {
        return em.createQuery("FROM Employee", Employee.class).getResultList();
    }

    @Override
    public void saveEmployee(Employee newEmployee) {
        save(newEmployee);
    }

    @Override
    public Employee findEmployee(Long id) {
        List<Employee> employees = em.createQuery("SELECT d FROM Employee d WHERE d.id = :id")
                .setParameter("id", id)
                .getResultList();

        if (employees.isEmpty()) {
            return  null;
        } else {
            return employees.get(0);
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return  em.merge(employee);
    }
}