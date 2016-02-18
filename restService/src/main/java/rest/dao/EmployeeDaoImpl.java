package rest.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import rest.jdbc.EmployeeMapper;
import rest.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Repository for processing dpartment
 */
@Repository("employeeDao")
@Transactional
public class EmployeeDaoImpl implements EmployeeDao {

    private static final Logger logger =  LoggerFactory.getLogger(DepartmentDao.class);

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }


   /**
     * Retrieves all employee for the selected department
     * @param departmentId - the id of the selected department
     * @return a list of employee
     */
    public List<Employee> getList(Long departmentId) {
        logger.debug("Received request to show all employee for the selected department id - {}", departmentId);

        String SqlDepartmenName = "SELECT departmentName FROM department WHERE id = employee.departmentId";
        String SQL = "SELECT *, (" + SqlDepartmenName + ") AS departmentName FROM employee WHERE departmentId = ?";
        JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);
        List <Employee> employeeList = jdbcTemplateObject.query(SQL, new EmployeeMapper(), departmentId);

        for (Employee employee : employeeList) {
            logger.debug("Employee - Id:  {}, departmentId: {}, nameDepartment:  {}, fullName:  {}, birthDate: {}",
                    employee.getId(), employee.getDepartmentId(), employee.getDepartmentName(),
                    employee.getFullName(), employee.getBirthDate());
        }
        return employeeList;
    }

    /**
     * Adds a new employee
     * @param employee the a new employee
     * @return a new employee
     */
    public Employee insert(Employee employee) {
        logger.debug("Add new employee");

        final String SQL = "INSERT into employee (DepartmentId, fullName, birthDate, salary) values (?, ?, ?, ?)";
        JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);
        final Employee InsertEmployee = employee;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplateObject.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement(SQL, new String[] {"id"});
                        ps.setLong(1, InsertEmployee.getDepartmentId());
                        ps.setString(2, InsertEmployee.getFullName());
                        ps.setDate(3, InsertEmployee.getBirthDate());
                        ps.setDouble(4, InsertEmployee.getSalary());
                        return ps;
                    }
                },
                keyHolder);

        employee = get(keyHolder.getKey().longValue());
        logger.debug("Employee - Id:  {}, departmentId: {}, nameDepartment:  {}, fullName:  {}, birthDate: {}",
                employee.getId(), employee.getDepartmentId(), employee.getDepartmentName(),
                employee.getFullName(), employee.getBirthDate());
        return employee;
    }

    /**
     * Deletes an existing employee
     * @param id the id of the existing employee
     */
    public void delete(Long id){
        logger.debug("Delete employee where  id - {}", id);

        String SQL = "DELETE FROM employee WHERE id = ?";
        JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);
        jdbcTemplateObject.update( SQL, id );
    }

    /**
     * Retrieves a single employee
     * @param id the id of the existing employee
     * @return the existing employee
     */
    public Employee get(Long id) {
        logger.debug("Retrieve existing employee first id - {}", id);

        String SqlDepartmenName = "SELECT departmentName FROM department WHERE id = employee.departmentId";
        String SQL = "SELECT *, (" + SqlDepartmenName + ") AS departmentName FROM employee WHERE id = ?";
        JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);
        List <Employee> employeeList = jdbcTemplateObject.query(SQL, new EmployeeMapper(), id);
        Employee employee = new Employee();
        if (employeeList.size() > 0) {
            employee = employeeList.get(0);
        }

        if (employee != null) {
            logger.debug("Employee - Id:  {}, departmentId: {}, nameDepartment:  {}, fullName:  {}, birthDate: {}",
                    employee.getId(), employee.getDepartmentId(), employee.getDepartmentName(),
                    employee.getFullName(), employee.getBirthDate());
            }
        return employee;
    }

    /**
     * Edits an existing employee
     * @param employee the editing employee
     * @return the editing employee
     */
    public Employee update(Employee employee){
        logger.debug("Edits an existing employee id - {}", employee.getId());

        String SQL = "UPDATE employee SET departmentId = ?, fullName = ?, birthDate = ?, salary = ? WHERE id = ?";
        JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);
        jdbcTemplateObject.update(SQL, employee.getDepartmentId(), employee.getFullName(),
                employee.getBirthDate(), employee.getSalary(), employee.getId());

        employee = get(employee.getId());
        logger.debug("Employee - Id:  {}, departmentId: {}, nameDepartment:  {}, fullName:  {}, birthDate: {}",
                employee.getId(), employee.getDepartmentId(), employee.getDepartmentName(),
                employee.getFullName(), employee.getBirthDate());
        return employee;
    }

    /**
     * Seach employee between the two dates including their
     * @param firstBirthDate - the start date
     * @param lastBirthDate - the end Date
     * @return a list of employee is responsible search criteria
     */
    public List<Employee> findBetween(Date firstBirthDate, Date lastBirthDate){
        logger.debug("Received request to show all employee between the two dates: {} - {}", firstBirthDate, lastBirthDate);

        if (lastBirthDate == null) { lastBirthDate = firstBirthDate; }
        String SqlDepartmenName = "SELECT departmentName FROM department WHERE id = employee.departmentId";
        String SQLRangeDate  = "SELECT *, (" + SqlDepartmenName + ") AS departmentName FROM employee WHERE (birthDate >= ?) AND (birthDate <= ?) ";
        JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);
        List <Employee> employeeList = jdbcTemplateObject.query(SQLRangeDate, new EmployeeMapper(), firstBirthDate, lastBirthDate);

        for (Employee employee : employeeList) {
            logger.debug("Employee - Id:  {}, departmentId: {}, nameDepartment:  {}, fullName:  {}, birthDate: {}",
                    employee.getId(), employee.getDepartmentId(), employee.getDepartmentName(),
                    employee.getFullName(), employee.getBirthDate());
        }
        return employeeList;
    }
}