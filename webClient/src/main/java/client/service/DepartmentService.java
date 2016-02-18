package client.service;

import client.model.Department;
import client.model.Departments;

public interface DepartmentService {

    Department insert(Department department);

    Department get(Long id);

    Departments getList();

    void delete(Long id);

    void update(Department department);
}
