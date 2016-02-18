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

@ContextConfiguration(locations = "classpath:testApplicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DepartmentDaoImplTest {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentDaoImplTest.class);

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    @Transactional
    public void testGetList() throws Exception {
        logger.debug("Start testGetList");
        List<Department> departments = departmentDao.getList();
        Assert.assertNotNull(departments);
        logger.debug("Finish testGetList");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testInsert() throws Exception {
        logger.debug("Start testInsert");
        Department department = new Department();
        department.setDepartmentName("New test");

        List<Department> departments1 = departmentDao.getList();
        int sizeList = departments1.size();
        departmentDao.insert(department);

        List<Department> departments2 = departmentDao.getList();
        Assert.assertTrue(departments2.size() > sizeList);
        logger.debug("Finish testInsert");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testDelete() throws Exception {
        logger.debug("Start testDelete");
        Department department1 = departmentDao.get(1L);
        Assert.assertNotNull(department1);

        List<Employee> employees1 = employeeDao.getList(1L);
        Assert.assertTrue(employees1.size() > 0);

        departmentDao.delete(1L);
        Department department2 = departmentDao.get(1L);
        Assert.assertNull(department2.getId());

        List<Employee> employees2 = employeeDao.getList(1L);
        Assert.assertTrue(employees2.size() == 0);
        logger.debug("Finish testDelete");
    }

    @Test
    @Transactional
    public void testGet() throws Exception {
        logger.debug("Start testGet");
        Department department1 = departmentDao.get(2L);
        Assert.assertEquals(department1.getAverageSalary(), 0,0);

        Department department2 = departmentDao.get(1L);
        Assert.assertEquals(department2.getAverageSalary(), 1996,0);
        logger.debug("Finish testGet");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdate() throws Exception {
        logger.debug("Start testUpdate");
        Department department1 = departmentDao.get(2L);
        String nameDepartment = department1.getDepartmentName();
        department1.setDepartmentName("New name");

        departmentDao.update(department1);
        Department department2 = departmentDao.get(2L);
        Assert.assertNotEquals(department2.getDepartmentName(), nameDepartment);
        Assert.assertEquals("New name", department2.getDepartmentName());
        logger.debug("Finish testUpdate");
    }
}