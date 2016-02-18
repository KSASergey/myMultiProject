package rest.jdbc;

import rest.model.Department;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentMapper implements RowMapper<Department> {

    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
        Department department = new Department();
        department.setId(rs.getLong("id"));
        department.setDepartmentName(rs.getString("departmentName"));
        department.setAverageSalary(rs.getDouble("averageSalary"));
        return department;
    }
}

