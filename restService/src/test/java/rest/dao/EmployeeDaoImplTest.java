package rest.dao;

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
import rest.model.Department;
import rest.model.Employee;

import java.util.List;

import static rest.service.MyDateUtils.ConvertToSQLDate;
import static rest.service.MyDateUtils.ConvertToUtilDate;

@ContextConfiguration(locations = "classpath:testApplicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeDaoImplTest {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeDaoImplTest.class);

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    @Transactional
    public void testGetList() throws Exception {
        logger.debug("Start testGetList");
        List<Employee> employees1 = employeeDao.getList(1L);
        Assert.assertTrue(employees1.size() > 0);
        List<Employee> employees2 = employeeDao.getList(2L);
        Assert.assertTrue(employees2.size() == 0);
        logger.debug("Finish testGetList");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testInsert() throws Exception {
        logger.debug("Start testInsert");
        Employee employee = new Employee();
        employee.setDepartmentId(2L);
        employee.setFullName("New Employee");
        employee.setBirthDate(ConvertToSQLDate("1989-02-10"));
        employee.setSalary(228.0);

        List<Employee> employees1 = employeeDao.getList(2L);
        int sizeList = employees1.size();
        employeeDao.insert(employee);
        List<Employee> employees2 = employeeDao.getList(2L);
        Assert.assertTrue(employees2.size() > sizeList);
        logger.debug("Finish testInsert");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testDelete() throws Exception {
        logger.debug("Start testDelete");
        Employee employee1 = employeeDao.get(2L);
        Assert.assertNotNull(employee1.getId());
        employeeDao.delete(2L);
        Employee employee2 = employeeDao.get(2L);
        Assert.assertNull(employee2.getId());
        logger.debug("Finish testDelete");
    }

    @Test
    @Transactional
    public void testGet() throws Exception {
        logger.debug("Start testGet");
        Employee employee = employeeDao.get(3L);
        Assert.assertNotNull(employee.getId());
        logger.debug("Finish testGet");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdate() throws Exception {
        logger.debug("Start testUpdate");
        List<Employee> employees1 = employeeDao.getList(2L);
        Assert.assertEquals(0, employees1.size());

        Employee employee = employeeDao.get(4L);
        Department department = departmentDao.get(2L);
        employee.setDepartmentId(2L);
        employee.setFullName("Update Employee");
        employee.setBirthDate(ConvertToSQLDate("1989-02-10"));
        employee.setSalary(100.0);

        employeeDao.update(employee);
        List<Employee> employees2 = employeeDao.getList(2L);
        Assert.assertTrue(employees2.size() > 0);
        Assert.assertEquals(employees2.get(0).getDepartmentName(), department.getDepartmentName());
        Assert.assertEquals(employees2.get(0).getId(), employee.getId());
        logger.debug("Finish testUpdate");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testFindBetween() throws Exception {
        logger.debug("Start testFindBetween");
        java.util.Date firstBirthDate = ConvertToUtilDate("1982-11-10");
        java.util.Date lastBirthDate = ConvertToUtilDate("1982-11-10");
        List<Employee> employees1 = employeeDao.findBetween(firstBirthDate, lastBirthDate);
        Assert.assertTrue(employees1.size() == 0);

        Employee employee = new Employee();
        employee.setDepartmentId(2L);
        employee.setFullName("Find Employee");
        employee.setBirthDate(ConvertToSQLDate("1982-11-10"));
        employee.setSalary(352.0);
        employeeDao.insert(employee);
        List<Employee> employees2 = employeeDao.findBetween(firstBirthDate, lastBirthDate);
        Assert.assertTrue(employees2.size() == 1);
        employeeDao.delete(employee.getId());

        firstBirthDate = ConvertToUtilDate("1985-01-01");
        lastBirthDate = ConvertToUtilDate("2015-12-31");
        List<Employee> employees3 = employeeDao.findBetween(firstBirthDate, lastBirthDate);
        Assert.assertTrue(employees3.size() > 1);
        logger.debug("Finish testFindBetween");
    }
}