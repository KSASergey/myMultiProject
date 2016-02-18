package rest.dao;

import rest.model.Department;
import javax.sql.DataSource;
import java.util.List;

public interface DepartmentDao {

    void setDataSource(DataSource dataSource);

    Department insert(Department department);

    Department get(Long id);

    List<Department> getList();

    void delete(Long id);

    Department update(Department department);

}