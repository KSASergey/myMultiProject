package rest.service;

import java.util.List;
import rest.model.Department;

public interface DepartmentService {

    Department insert(Department department);

    Department get(Long id);

    List<Department> getList();

    void delete(Long id);

    Department update(Department department);
}
