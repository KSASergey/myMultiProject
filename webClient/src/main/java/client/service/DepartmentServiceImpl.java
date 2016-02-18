package client.service;

import client.model.Department;
import client.model.Departments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service employee for connect restService
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Autowired
    RestTemplate restTemplate;

    private static final String URL_SERVER = "http://localhost";

    private static final String URL_GET_ALL_DepartmentS =
            URL_SERVER + "/restService/restful/department/listdata";
    private static final String URL_GET_Department_BY_ID =
            URL_SERVER + "/restService/restful/department/{id}";
    private static final String URL_CREATE_Department =
            URL_SERVER + "/restService/restful/department/";
    private static final String URL_UPDATE_Department =
            URL_SERVER + "/restService/restful/department/{id}";
    private static final String URL_DELETE_Department =
            URL_SERVER + "/restService/restful/department/{id}";

    /**
     * insert a new department
     * @param department a new department
     * @return a new department
     */
    public Department insert(Department department) {
        logger.debug("create department :");
        department = restTemplate.postForObject(URL_CREATE_Department, department, Department.class);
        logger.debug("Department created successfully: {}", department);
        return department;
    }

    /**
     * Retrieves a single department
     * @param id the id of the existing department
     * @return the existing department
     */
    public Department get(Long id) {
        logger.debug("retrieve a department by id : {}", id);
        Department department = restTemplate.getForObject(URL_GET_Department_BY_ID, Department.class, id);
        logger.debug("department: {}", department);
        return department;
    }

    /**
     * Retrieves all department
     * @return a list of department
     */
    public Departments getList() {
        logger.debug("retrieve all departments:");
        Departments departments = restTemplate.getForObject(URL_GET_ALL_DepartmentS, Departments.class);
        for (Department department : departments.getDepartments()) {
            logger.debug("department: {}", department);
        }
        return departments;
    }

    /**
     * update an existing department
     * @param department the editing department
     */
    public void update(Department department) {
        logger.debug("update department by id :");
        restTemplate.put(URL_UPDATE_Department, department, department.getId());
        logger.debug("Department update successfully: {}", department);
    }

    /**
     * Deletes an existing department
     * @param id the id of the existing department
     */
    public void delete(Long id) {
        logger.debug("delete department by id : {}", id);
        restTemplate.delete(URL_DELETE_Department, id);
    }
}
