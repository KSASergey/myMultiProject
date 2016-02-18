package rest.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import rest.model.Department;
import rest.jdbc.DepartmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Repository for processing department
 */
@Repository("departmentDao")
@Transactional
public class DepartmentDaoImpl implements DepartmentDao {

    private static final Logger logger =  LoggerFactory.getLogger(DepartmentDao.class);

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    /**
     * Retrieves all department
     * @return a list of department
     */
    public List<Department> getList() {
        logger.debug("Received request to show all department");

        String salarySQL = "SELECT  COALESCE(AVG(Salary),0) FROM employee WHERE departmentId=department.ID";
        String SQL = "SELECT *, (" + salarySQL + ") AS AverageSalary FROM department";
        JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);
        List <Department> listDepartment = jdbcTemplateObject.query(SQL, new DepartmentMapper());

        for (Department department : listDepartment) {
            logger.debug("Department - Id:  {},  departmentName:  {}, averageSalary:  {}",
                    department.getId(), department.getDepartmentName(), department.getAverageSalary());
        }
        return listDepartment;
    }

    /**
     * Adds a new department
     * @param department the a new department
     * @return a new department
     */
    public Department insert(Department department) {
        logger.debug("Add new department");

        final String SQL = "INSERT into department (departmentName) values (?)";
        JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);

        final Department InsertDepartment = department;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplateObject.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement(SQL, new String[] {"id"});
                        ps.setString(1, InsertDepartment.getDepartmentName());
                        return ps;
                    }
                },
                keyHolder);

        department = get(keyHolder.getKey().longValue());
        logger.debug("Department - Id:  {},  departmentName:  {}, averageSalary:  {}",
                department.getId(), department.getDepartmentName(), department.getAverageSalary());
        return department;
    }

    /**
     * Deletes an existing department
     * @param id the id of the existing department
    */
    public void delete(Long id) {
        logger.debug("Delete department where id = {}", id);

        String SQL = "DELETE FROM department WHERE id = ?";
        JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);
        jdbcTemplateObject.update( SQL, id );

        System.out.println("Deleted Record with ID = " + id );
    }

    /**
     * Retrieves a single department
     * @param id the id of the existing department
     * @return the existing department
     */
    public Department get(Long id) {
        logger.debug("Retrieve existing department first where id = {}", id);

        String salarySQL = "SELECT  COALESCE(AVG(Salary),0) FROM employee WHERE departmentId=department.ID";
        String SQL = "SELECT *, (" + salarySQL + ") AS AverageSalary FROM department WHERE id = ?";
        JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);
        List <Department> departmentList = jdbcTemplateObject.query(SQL, new DepartmentMapper(), id);
        Department department = new Department();
        if (departmentList.size() > 0) {
            department = departmentList.get(0);
        }

        if (department != null) {
            logger.debug("Department - Id:  {},  departmentName:  {}, averageSalary:  {}",
                    department.getId(), department.getDepartmentName(), department.getAverageSalary());
        }
        return department;
    }

    /**
     * Edits an existing department
     * @param department the editing department
     * @return the editing department
     */
    public Department update(Department department) {
        logger.debug("Edits an existing department where id = {}", department.getId());

        String SQL = "UPDATE department SET departmentName = ? WHERE id = ?";
        JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);
        jdbcTemplateObject.update(SQL, department.getDepartmentName(), department.getId());

        department = get(department.getId());
        logger.debug("Department - Id:  {},  departmentName:  {}, averageSalary:  {}",
                department.getId(), department.getDepartmentName(), department.getAverageSalary());
        return department;
    }
}