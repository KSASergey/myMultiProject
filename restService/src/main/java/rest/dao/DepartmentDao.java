package rest.dao;

import rest.model.Department;
import java.util.List;

public interface DepartmentDao {

    public void insert(Department department);

    public Department get(Long id);

    public List<Department> getList();

    public void delete(Long id);

    public void update(Department department);

}