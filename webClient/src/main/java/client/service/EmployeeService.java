package client.service;

import client.model.Employee;
import client.model.Employees;

import java.util.Date;

public interface EmployeeService {

    Employee insert(Employee employee);

    Employee get(Long id);

    Employees getList(Long departmentId);

    void delete(Long id);

    void update(Employee employee);

    Employees findBetween(Date firstBirthDate, Date lastBirthDate);

}
