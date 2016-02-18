package rest.service;

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
import rest.model.Departments;

import static org.junit.Assert.*;

@ContextConfiguration(locations = "classpath:testApplicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DepartmentServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImplTest.class);

    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;

    @Test
    @Transactional
    @Rollback(true)
    public void testInsert() throws Exception {
        logger.debug("Start testInsert");
        Department department = new Department();
        department.setDepartmentName("Test Insert");
        Department insertDepartment = departmentServiceImpl.insert(department);

        assertNotNull(insertDepartment.getId());
        logger.debug("Finish testInsert");
    }

    @Test
    @Transactional
    public void testGet() throws Exception {
        logger.debug("Start testGet");
        Department department = departmentServiceImpl.get(1L);
        assertNotNull(department.getId());
        assertNotNull(department.getId());
        logger.debug("Finish testGet");
    }

    @Test
    @Transactional
    public void testGetList() throws Exception {
        logger.debug("Start testGetList");
        Departments departments = new Departments();
        departments.setDepartments(departmentServiceImpl.getList());
        assertTrue(departments.getDepartments().size() > 0);
        logger.debug("Finish testGetList");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdate() throws Exception {
        logger.debug("Start testUpdate");
        Department department = departmentServiceImpl.get(1L);
        department.setDepartmentName("Test Update");
        departmentServiceImpl.update(department);

        department = departmentServiceImpl.get(1L);
        assertEquals(department.getDepartmentName(), "Test Update");
        logger.debug("Finish testUpdate");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testDelete() throws Exception {
        logger.debug("Start testDelete");
        departmentServiceImpl.delete(1L);
        Department department = departmentServiceImpl.get(1L);
        assertNull(department.getId());
        logger.debug("Finish testDelete");
    }
}