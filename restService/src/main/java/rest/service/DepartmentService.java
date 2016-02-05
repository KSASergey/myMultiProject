package rest.service;

import java.util.List;
import rest.model.Department;

public interface DepartmentService {

    public void insert(Department department);

    public Department get(Long id);

    public List<Department> getList();

    public void delete(Long id);

    public void update(Department department);
}
