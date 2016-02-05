package client.service;

import java.util.List;
import client.model.Department;
import client.model.Departments;

public interface DepartmentService {

    public void insert(Department department);

    public Department get(Long id);

    public Departments getList();

    public void delete(Long id);

    public void update(Department department);
}
