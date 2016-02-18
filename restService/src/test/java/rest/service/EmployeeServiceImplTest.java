package rest.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import rest.model.Employee;
import rest.model.Employees;

import static rest.service.MyDateUtils.ConvertToSQLDate;
import static rest.service.MyDateUtils.ConvertToUtilDate;

@ContextConfiguration(locations = "classpath:testApplicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImplTest.class);

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @Test
    @Transactional
    @Rollback(true)
    public void testInsert() throws Exception {
        logger.debug("Start testInsert");
        Employee employee = new Employee();
        employee.setDepartmentId(1L);
        employee.setFullName("Test Insert");
        employee.setBirthDate(ConvertToSQLDate("1990-10-10"));
        employee.setSalary(1000.0);
        Employee insertEmployee = employeeServiceImpl.insert(employee);

        Assert.assertNotNull(insertEmployee.getId());
        logger.debug("Finish testInsert");
    }

    @Test
    @Transactional
    public void testGet() throws Exception {
        logger.debug("Start testGet");
        Employee employee = employeeServiceImpl.get(1L);
        Assert.assertNotNull(employee.getId());
        logger.debug("Finish testGet");
    }

    @Test
    @Transactional
    public void testGetList() throws Exception {
        logger.debug("Start testGetList");
        Employees employees = new Employees();
        employees.setEmployees(employeeServiceImpl.getList(1L));
        Assert.assertTrue(employees.getEmployees().size() > 0);
        logger.debug("Finish testGetList");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdate() throws Exception {
        logger.debug("Start testUpdate");
        Employee employee = new Employee();
        employee.setId(3L);
        employee.setDepartmentId(1L);
        employee.setFullName("Test Update");
        employee.setBirthDate(ConvertToSQLDate("1999-10-10"));
        employee.setSalary(2000.0);

        employee = employeeServiceImpl.update(employee);
        Assert.assertEquals(employee.getFullName(), "Test Update");
        logger.debug("Finish testUpdate");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testDelete() throws Exception {
        logger.debug("Start testDelete");
        employeeServiceImpl.delete(1L);
        Employee employee = employeeServiceImpl.get(1L);
        Assert.assertNull(employee.getId());
        logger.debug("Finish testDelete");
    }

    @Test
    public void testFindBetween() throws Exception {
        logger.debug("Start testFindBetween");
        Employees employees = new Employees();
        java.util.Date firstBirthDate = ConvertToUtilDate("1985-01-01");
        java.util.Date lastBirthDate = ConvertToUtilDate("2015-12-31");
        employees.setEmployees(employeeServiceImpl.findBetween(firstBirthDate , lastBirthDate));
        Assert.assertTrue(employees.getEmployees().size() > 0);
        logger.debug("Finish testFindBetween");
    }
}