package client.service;

import client.model.Department;
import client.model.Departments;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml",
                                   "classpath:META-INF/spring/restful-client-app-context.xml"})
public class DepartmentServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImplTest.class);

    @Autowired
    DepartmentServiceImpl departmentServiceImpl;
    
    @Test
    public void testInsert() throws Exception {
        logger.debug("Start testInsert");
        Department departmentNew = new Department();
        departmentNew.setDepartmentName("New test Department");
        departmentNew = departmentServiceImpl.insert(departmentNew);
        assertNotNull(departmentNew.getId());
        assertEquals(departmentNew.getDepartmentName(), "New test Department");
        logger.debug("Finish testInsert");
    }

    @Test
    public void testGet() throws Exception {
        logger.debug("Start testGet");
        Department department = departmentServiceImpl.get(1L);
        assertTrue(department.getId() == 1L);
        logger.debug("Finish testGet");
    }

    @Test
    public void testGetList() throws Exception {
        logger.debug("Start testGetList");
        Departments departments = departmentServiceImpl.getList();
        assertTrue(departments.getDepartments().size() > 0);
        logger.debug("Finish testGetList");
    }

    @Test
    public void testUpdate() throws Exception {
        logger.debug("Start testUpdate");
        Department department = departmentServiceImpl.get(2L);
        department.setDepartmentName("Test Department");
        departmentServiceImpl.update(department);
        logger.debug("Finish testUpdate");
    }

    @Test
    public void testDelete() throws Exception {
        logger.debug("Start testDelete");
        departmentServiceImpl.delete(3L);
        logger.debug("Finish testDelete");
    }
}