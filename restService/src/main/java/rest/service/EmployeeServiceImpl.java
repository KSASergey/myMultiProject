package rest.service;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rest.dao.EmployeeDao;
import rest.model.Employee;
import static rest.service.MyDateUtils.*;
import java.util.Date;
import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeDao employeeDao;

    /**
     * insert a new employee
     * @param employee - the a new employee
     * @return a new employee
     */
    @Transactional
    public Employee insert(Employee employee) {
        logger.debug("Testing create employee :");
        return employeeDao.insert(employee);
    }

    /**
     * Retrieves a single employee
     * @param id the id of the existing employee
     * @return the existing employee
     */
    @Transactional(readOnly = true)
    public Employee get(Long id) {
        logger.debug("retrieve a employee by id : {}", id);
        return employeeDao.get(id);
    }

    /**
     * Retrieves all employee for the selected department
     * @param departmentId the id of the selected department
     * @return a list of employee
     */
    @Transactional(readOnly = true)
    public List<Employee> getList(Long departmentId) {
        logger.debug("Testing retrieve all employees: {}", departmentId);
        return Lists.newArrayList(employeeDao.getList(departmentId));
    }

    /**
     * update an existing employee
     * @param employee the editing employee
     */
    @Transactional
    public Employee update(Employee employee) {
        logger.debug("update employee by id :");
        return employeeDao.update(employee);
    }

    /**
     * Deletes an existing employee
     * @param id the id of the existing employee
     */
    @Transactional
    public void delete(Long id) {
        logger.debug("delete employee by id : {}", id);
        employeeDao.delete(id);
    }

    /**
     * Seach employee between the two dates including their
     * @param firstBirthDate the start date
     * @param lastBirthDate the end Date
     * @return a list of employee is responsible search criteria
     */
    @Transactional(readOnly = true)
    public List<Employee> findBetween(Date firstBirthDate, Date lastBirthDate) {
        logger.debug("find employee range : {}", SearshRange(firstBirthDate, lastBirthDate));
        return employeeDao.findBetween(firstBirthDate, lastBirthDate);
    }
}
