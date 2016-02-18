package client.service;

import client.model.Employee;
import client.model.Employees;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static client.service.MyDateUtils.ConvertToSQLDate;
import static client.service.MyDateUtils.ConvertToUtilDate;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml",
                                   "classpath:META-INF/spring/restful-client-app-context.xml"})
public class EmployeeServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImplTest.class);

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @Test
    public void testInsert() throws Exception {
        logger.debug("Start testInsert");
        Employee employee = new Employee();
        employee.setDepartmentId(2L);
        employee.setFullName("New test Employee");
        employee.setBirthDate(ConvertToSQLDate("1982-02-13"));
        employee.setSalary(378.0);
        Employee employeeNew = employeeServiceImpl.insert(employee);
        assertNotNull(employeeNew.getId());
        assertEquals(employeeNew.getFullName(), "New test Employee");
        logger.debug("Finish testInsert");
    }

    @Test
    public void testGet() throws Exception {
        logger.debug("Start testGet");
        Employee Employee = employeeServiceImpl.get(1L);
        assertTrue(Employee.getId() == 1L);
        logger.debug("Finish testGet");
    }

    @Test
    public void testGetList() throws Exception {
        logger.debug("Start testGetList");
        Employees Employees = employeeServiceImpl.getList(1L);
        assertTrue(Employees.getEmployees().size() > 0);
        logger.debug("Finish testGetList");
    }

    @Test
    public void testUpdate() throws Exception {
        logger.debug("Start testUpdate");
        Employee employee = employeeServiceImpl.get(5L);
        employee.setDepartmentId(2L);
        employee.setFullName("Update test Employee");
        employee.setBirthDate(ConvertToSQLDate("1985-08-21"));
        employee.setSalary(255.0);
        employeeServiceImpl.update(employee);
        logger.debug("Finish testUpdate");
    }

    @Test
    public void testDelete() throws Exception {
        logger.debug("Start testDelete");
        employeeServiceImpl.delete(3L);
        logger.debug("Finish testDelete");
    }

    @Test
    public void testFindBetween() throws Exception {
        logger.debug("Start testFindBetween");
        Date firstBirthDate = ConvertToUtilDate("1982-12-23");
        Date lastBirthDate = ConvertToUtilDate("2011-02-10");
        Employees Employees = employeeServiceImpl.findBetween(firstBirthDate, lastBirthDate);
        assertTrue(Employees.getEmployees().size() > 0);
        logger.debug("Finish testFindBetween");
    }
}