package rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rest.model.Employee;
import rest.model.Employees;
import rest.service.EmployeeService;
import java.util.Date;

/**
 * Class controler for spring MVC
 */
@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    // for processing employee in datebase
    @Autowired
    private EmployeeService employeeService;

    /**
     * Get full list all employee for the selected department
     * @param id - the id of the selected department
     * @return full list employee
     */
    @RequestMapping(value = "/listdata/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Employees listData(@PathVariable Long id) {
        logger.info("get full list all employee for the selected departmentId = {}", id);
        return new Employees(employeeService.getList(id));
    }

    /**
     * Get employee from id
     * @param id - the id existing employee
     * @return employee from id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Employee find(@PathVariable Long id) {
        logger.info("Get employee from id = {}", id);
        return employeeService.get(id);
    }

    /**
     * Create a new employee
     * @param employee the a new employee
     * @return creating employee
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Employee create(@RequestBody Employee employee) {
        logger.info("Creating Employee: " + employee);
        employee = employeeService.insert(employee);
        logger.info("Employee created successfully wi th info: " + employee);
        return employee;
    }

    /**
     * Update existing employee
     * @param id - the id editing existing employee
     * @param employee the editing existing employee
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Employee update(@RequestBody Employee employee, @PathVariable Long id) {
        logger.info("Updating Employee: {}", employee);
        employee.setId(id);
        employee = employeeService.update(employee);
        logger.info("Employee updated successfully wi th info: {}", employee);
        return employee;
    }

    /**
     * Delete existing employee
     * @param id - the id deleting existing employee
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable Long id) {
        logger.info("Deleting Employee with {}: ", id);
        employeeService.delete(id);
        logger.info("Employee deleted successfully");
    }

    /**
     * Get list employee between the two dates
     * @param firstBirthDate - the start date
     * @param lastBirthDate - the end Date
     * @return a list of employee is responsible search criteria
     */
    @RequestMapping(value = "/listdata/birthDate", method = RequestMethod.GET)
    @ResponseBody
    public Employees listBetween(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date firstBirthDate,
                                               @DateTimeFormat(pattern = "yyyy-MM-dd") Date lastBirthDate) {
        logger.info("get full list all employee between the two dates: {} - {}", firstBirthDate, lastBirthDate);
        return new Employees(employeeService.findBetween(firstBirthDate, lastBirthDate));
    }
}
