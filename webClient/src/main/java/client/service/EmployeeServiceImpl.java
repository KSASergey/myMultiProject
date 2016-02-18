package client.service;

import client.model.Employee;
import client.model.Employees;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static client.service.MyDateUtils.SearshRange;

/**
 * Service employee for connect restService
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    RestTemplate restTemplate;

    private static final String URL_SERVER = "http://localhost";

    private static final String URL_GET_ALL_EmployeeS =
            URL_SERVER + "/restService/restful/employee/listdata/{id}";
    private static final String URL_GET_Employee_BY_ID =
            URL_SERVER + "/restService/restful/employee/{id}";
    private static final String URL_CREATE_Employee =
            URL_SERVER + "/restService/restful/employee/";
    private static final String URL_UPDATE_Employee =
            URL_SERVER + "/restService/restful/employee/{id}";
    private static final String URL_DELETE_Employee =
            URL_SERVER + "/restService/restful/employee/{id}";
    private static final String URL_FIND_BETWEEN_EmployeeS =
            URL_SERVER + "/restService/restful/employee/listdata/birthDate";

    /**
     * insert a new employee
     * @param employee - the a new employee
     * @return a new employee
     */
    public Employee insert(Employee employee) {
        logger.debug("create employee :");
        employee = restTemplate.postForObject(URL_CREATE_Employee, employee, Employee.class);
        logger.debug("Employee created successfully: {}", employee);
        return employee;
    }

    /**
     * Retrieves a single employee
     * @param id the id of the existing employee
     * @return the existing employee
     */
    public Employee get(Long id) {
        logger.debug("retrieve a employee by id : {}", id);
        Employee employee = restTemplate.getForObject(URL_GET_Employee_BY_ID, Employee.class, id);
        logger.debug("employee: {}", employee);
        return employee;
    }

    /**
     * Retrieves all employee for the selected department
     * @param departmentId the id of the selected department
     * @return a list of employee
     */
    public Employees getList(Long departmentId) {
        logger.debug("Testing retrieve all employees: {}", departmentId);
        Employees employees = restTemplate.getForObject(URL_GET_ALL_EmployeeS, Employees.class, departmentId);
        for (Employee employee : employees.getEmployees()) {
            logger.debug("employee: {}", employee);
        }
        return employees;
    }

    /**
     * update an existing employee
     * @param employee the editing employee
     */
    public void update(Employee employee) {
        logger.debug("update employee by id :");
        restTemplate.put(URL_UPDATE_Employee, employee, employee.getId());
        logger.debug("employee: {}", employee);
    }

    /**
     * Deletes an existing employee
     * @param id the id of the existing employee
     */
    public void delete(Long id) {
        logger.debug("delete employee by id : {}", id);
        restTemplate.delete(URL_DELETE_Employee, id);
    }

    /**
     * Seach employee between the two dates including their
     * @param firstBirthDate the start date
     * @param lastBirthDate the end Date
     * @return a list of employee is responsible search criteria
     */
    public Employees findBetween(Date firstBirthDate, Date lastBirthDate){
        logger.debug("find employee range : {}", SearshRange(firstBirthDate, lastBirthDate));
        DateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (lastBirthDate == null) { lastBirthDate = firstBirthDate; }
        String params = "?firstBirthDate=" + myDateFormat.format(firstBirthDate) +
                        "&lastBirthDate=" + myDateFormat.format(lastBirthDate);
        Employees employees = restTemplate.getForObject(URL_FIND_BETWEEN_EmployeeS + params, Employees.class);
        for (Employee employee : employees.getEmployees()) {
            logger.debug("employee: {}", employee);
        }
        return employees;
    }

}
