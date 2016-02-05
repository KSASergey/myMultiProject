package rest.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import rest.model.Department;
import rest.jdbc.DepartmentMapper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

/**
 * Repository for processing department
 */
@Repository("departmentDao")
@Transactional
public class DepartmentDaoImpl implements DepartmentDao {

//    private static final Logger logger =  LoggerFactory.getLogger(DepartmentDao.class);

    @Autowired
    private DataSource dataSourceJDBC;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSourceJDBC = dataSourceJDBC;
        this.jdbcTemplateObject = new JdbcTemplate(dataSourceJDBC);
    }

    /**
     * Retrieves all department
     * @return a list of department
     */
	@Transactional(readOnly = true)
    public List<Department> getList() {
        String SQL = "SELECT *, (SELECT  COALESCE(AVG(Salary),0) FROM employee WHERE ID_Department=department.ID) AS AverageSalary FROM department";
        JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSourceJDBC);
        List <Department> listDepartment = jdbcTemplateObject.query(SQL, new DepartmentMapper());
        return listDepartment;
    }

    /**
     * Adds a new department
     * @param department the a new department
     */
    @Transactional
    public void insert(Department department) {
        String SQL = "INSERT into department (department) values (?)";
        JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSourceJDBC);
        jdbcTemplateObject.update( SQL, department.getDepartmentName());
        System.out.println("Created Record department = " + department);
    }

    /**
     * Deletes an existing department
     * @param id the id of the existing department
    */
    @Transactional
    public void delete(Long id) {
        String SQL = "DELETE FROM department WHERE id = ?";
        JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSourceJDBC);
        jdbcTemplateObject.update( SQL, id );
        System.out.println("Deleted Record with ID = " + id );
    }

    /**
     * Retrieves a single department
     * @param id the id of the existing department
     * @return the existing department
     */
    @Transactional(readOnly = true)
    public Department get(Long id) {
        String SQL = "SELECT *, 0 AS AverageSalary FROM department WHERE id = ?";
        JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSourceJDBC);
        List <Department> listDepartment = jdbcTemplateObject.query(SQL, new DepartmentMapper(), id);
        return listDepartment.get(0);
    }

    /**
     * Edits an existing department
     * @param department the editing department
     */
    @Transactional
    public void update(Department department) {
        String SQL = "UPDATE department SET department = ? WHERE id = ?";
        JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSourceJDBC);
        jdbcTemplateObject.update(SQL, department.getDepartmentName(), department.getId());
        System.out.println("Updated Record with ID = " + department.getId() );
        return;
    }
}