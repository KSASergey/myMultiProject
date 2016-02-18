package client.controller;

import client.model.Department;
import client.model.Employee;
import client.model.Employees;
import client.service.DepartmentService;
import client.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

import static client.service.MyDateUtils.SearshRange;

/**
 *  Class controler for spring MVC
 */
@Controller
@RequestMapping(value="/employee")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private DepartmentService departmentService;
    
    @Autowired
    private EmployeeService employeeService;

    /**
     * Metod View table to show all employee in selecting department
     * @param department the selecting department (only id depatment)
     * @return list employee - view tableEmployee.jsp
     */
    @RequestMapping(value = "/table_Employee", method = RequestMethod.GET)
    public ModelAndView getEmployeeLIst(@ModelAttribute("modelDepartment") Department department) {
        logger.info("View table to show all employee in selecting department");
        Long idDepartment = department.getId();
        department = departmentService.get(idDepartment);
        Employees employees = employeeService.getList(idDepartment);
        List<Employee> employeeList = employees.getEmployees();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("modelDepartment", department);
        modelAndView.addObject("modelEmployeeList", employeeList);
        modelAndView.setViewName("employee/tableEmployee");
        return modelAndView;
    }

    /**
     * Metod View form to add new employee in selecting department
     * @param department the selecting department (only id depatment)
     * @return form add new addEmployee - view addEmployee.jsp
     */
    @RequestMapping(value = "/add_Employee", method = RequestMethod.GET)
    public ModelAndView addEmployee(@ModelAttribute("modelDepartment") Department department){
        logger.info("View form add new employee in selecting department");
        Long idDepartment = department.getId();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tempDepartment", departmentService.get(idDepartment));
        modelAndView.addObject("modelEmployee", new Employee());
        modelAndView.setViewName("employee/addEmployee");
        return modelAndView;
    }

    /**
     * Metod insert new employee in selecting department to datebase
     * @param employee the new employee
     * @return redirict for maping '/table_Employee' + param 'id' - selecting department
     */
    @RequestMapping(value = "/insert_Employee", method = RequestMethod.POST)
    public ModelAndView insertEmployee(@ModelAttribute("modelEmployee") Employee employee) {
        logger.info("Insert new employee in selecting department");
        employee = employeeService.insert(employee);
        Long idDepartment = employee.getDepartmentId();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("modelEmployee", employee);
        modelAndView.setViewName("redirect:/client/employee/table_Employee?id=" + idDepartment);
        return modelAndView;
    }

    /**
     * Metod delete an existing employee
     * @param employee deleting employee
     * @return redirict for maping '/table_Employee' + param 'id' - selecting department
     */
    @RequestMapping(value = "/delete_Employee", method = RequestMethod.POST)
    public ModelAndView deleteEmployee(@ModelAttribute("modelEmployee") Employee employee) {
        logger.info("Delete an existing employee in selecting department");
        Long idEmployee = employee.getId();
        Long idDepartment = employee.getDepartmentId();
        employeeService.delete(idEmployee);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/client/employee/table_Employee?id=" + idDepartment);
        return modelAndView;
    }

    /**
     * Metod view form edits an existing employee
     * @param employee the editing employee
     * @return form edit existing employee - view editEmployee.jsp
     */
    @RequestMapping(value = "/edit_Employee", method = RequestMethod.POST)
    public ModelAndView editEmployee(@ModelAttribute("modelEmployee") Employee employee) {
        logger.info("View form edit an existing employee in selecting department");
        ModelAndView modelAndView = new ModelAndView();
        Long idEmployee = employee.getId();
        employee = employeeService.get(idEmployee);
        modelAndView.addObject("modelEmployee", employee);
        modelAndView.setViewName("employee/editEmployee");
        return modelAndView;
    }

    /**
     * Metod Update the editing existing employee to datebase
     * @param employee the editing employee
     * @return redirict for maping '/table_Employee' + param 'id' - selecting department
     */
    @RequestMapping(value = "/update_Employee", method = RequestMethod.POST)
    public ModelAndView updateEmployee(@ModelAttribute("modelEmployee") Employee employee){
        logger.info("Update the editing existing employee in selecting department");
        employeeService.update(employee);
        employee = employeeService.get(employee.getId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/client/employee/table_Employee?id=" + employee.getDepartmentId());
        return modelAndView;
    }

    /**
     * Metod seach employee between the two dates including their
     * @param firstBirthDate - the start date
     * @param lastBirthDate - the end Date
     * @return form add new addEmployee - view addEmployee.jsp
     */
    @RequestMapping(value = "/find_Employee", method = RequestMethod.GET)
    public ModelAndView findEmployee(@RequestParam() @DateTimeFormat(pattern = "yyyy-MM-dd") Date firstBirthDate,
                                                     @DateTimeFormat(pattern = "yyyy-MM-dd") Date lastBirthDate) {

        logger.info("View table to show all employee in selecting department");
        String searshRange = SearshRange(firstBirthDate, lastBirthDate);
        ModelAndView modelAndView = new ModelAndView();
        Employees employees = employeeService.findBetween(firstBirthDate, lastBirthDate);
        List<Employee> employeeList = employees.getEmployees();
        modelAndView.addObject("modelEmployeeList", employeeList);
        modelAndView.addObject("searshRange", searshRange);
        modelAndView.setViewName("employee/findEmployee");
        return modelAndView;
    }

}
