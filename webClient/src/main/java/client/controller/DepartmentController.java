package client.controller;

import client.model.Department;
import client.model.Departments;
import client.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *  Class controler for spring MVC
 */
@Controller
@RequestMapping(value="/department")
public class DepartmentController {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    /**
     * Metod View table to show all department
     * @return full list department - view tableDepartment.jsp
     */
    @RequestMapping(value = "/table_Department", method = RequestMethod.GET)
    public ModelAndView getDepartmentLIst() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("department/tableDepartment");
        Departments depList = departmentService.getList();
        modelAndView.addObject("modelDepartmentList", depList.getDepartments());
        return modelAndView;
    }

    /**
     * Metod View form to add new department
     * @return form add new department - view addDepartment.jsp
     */
    @RequestMapping(value = "/add_Department", method = RequestMethod.GET)
    public ModelAndView addDepartment() {
        logger.info("View form add new department");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("modelDepartment", new Department());
        modelAndView.setViewName("department/addDepartment");
        return modelAndView;
    }

    /**
     * Metod delete existing department
     * @return redirict for maping '/table_Department'
     */
    @RequestMapping(value = "/delete_Department", method = RequestMethod.POST)
    public ModelAndView deleteDepartment(@ModelAttribute("modelDepartment") Department department) {
        logger.info("Delete department");
        departmentService.delete(department.getId());
        return new ModelAndView("redirect:/client/department/table_Department");
    }

    /**
     * Metod insert new department to datebase
     * @param department the new department
     * @return redirict for maping '/table_Department'
     */
    @RequestMapping(value = "/insert_Department", method = RequestMethod.POST)
    public ModelAndView insertDepartment(@ModelAttribute("modelDepartment") Department department) {
        logger.info("Insert new department");
        department = departmentService.insert(department);
        ModelAndView modelAndView = new ModelAndView("redirect:/client/department/table_Department");
        modelAndView.addObject("modelDepartment", department);
        return modelAndView;
    }

    /**
     * Metod view form edits an existing department
     * @param department the editing department
     * @return form edit existing department - view editDepartment.jsp
     */
    @RequestMapping(value = "/edit_Department", method = RequestMethod.POST)
    public ModelAndView editDepartment(@ModelAttribute("modelDepartment") Department department) {
        logger.info("View form editing new department");
        ModelAndView modelAndView = new ModelAndView();
        Long idDepartment = department.getId();
        department = departmentService.get(idDepartment);
        modelAndView.addObject("modelDepartment", department);
        modelAndView.setViewName("department/editDepartment");
        return modelAndView;
    }

    /**
     * Metod update existing department to datebase
     * @param department the editing existing department
     * @return redirict for maping '/table_Department'
     */
    @RequestMapping(value = "/update_Department", method = RequestMethod.POST)
    public ModelAndView updateDepartment(@ModelAttribute("modelDepartment") Department department) {
        logger.info("Update the editing existing department");
        departmentService.update(department);
        return new ModelAndView("redirect:/client/department/table_Department");
    }

}
