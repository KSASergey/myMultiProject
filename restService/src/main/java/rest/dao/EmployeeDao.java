package rest.dao;

import rest.model.Employee;
import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

public interface EmployeeDao {

    void setDataSource(DataSource dataSource);

    Employee insert(Employee employee);

    Employee get(Long id);

    List<Employee> getList(Long departmentId);

    void delete(Long id);

    Employee update(Employee employee);

    List<Employee> findBetween(Date firstBirthDate, Date lastBirthDate);

}