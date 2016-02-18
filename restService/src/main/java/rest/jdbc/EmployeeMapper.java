package rest.jdbc;

import rest.model.Employee;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {

    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getLong("id"));
        employee.setDepartmentId(rs.getLong("departmentId"));
        employee.setDepartmentName(rs.getString("departmentName"));
        employee.setFullName(rs.getString("fullName"));
        employee.setBirthDate(rs.getDate("birthDate"));
        employee.setSalary(rs.getDouble("salary"));
        return employee;
    }
}

