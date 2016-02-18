package rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import rest.model.Department;
import rest.model.Departments;
import rest.service.DepartmentService;

/**
 * Class controler for spring MVC
 */
@Controller
@RequestMapping(value = "/department")
public class DepartmentController {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    // for processing department in datebase
    @Autowired
    private DepartmentService departmentService;

    /**
     * Get full list all department
     * @return full list department
     */
    @RequestMapping(value = "/listdata", method = RequestMethod.GET)
    @ResponseBody
    public Departments listData() {
        logger.info("get full list all department");
        return new Departments(departmentService.getList());
    }

    /**
     * Get department from id
     * @param id - the id existing department
     * @return department from id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Department find(@PathVariable Long id) {
        logger.info("Get department from id = {}", id);
        return departmentService.get(id);
    }

    /**
     * Create a new department
     * @param department the a new department
     * @return creating department
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Department create(@RequestBody Department department) {
        logger.info("Creating Department: " + department);
        department = departmentService.insert(department);
        logger.info("Department created successfully wi th info: " + department);
        return department;
    }

    /**
     * Update existing department
     * @param id - the id editing existing department
     * @param department the editing existing department
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Department update(@RequestBody Department department, @PathVariable Long id) {
        logger.info("Updating Department: {}", department);
        department.setId(id);
        department = departmentService.update(department);
        logger.info("Department updated successfully wi th info: {}", department);
        return department;
    }

    /**
     * Delete existing department
     * @param id - the id deleting existing department
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable Long id) {
        logger.info("Deleting Department with {}: ", id);
        departmentService.delete(id);
        logger.info("Department deleted successfully");
    }
}
