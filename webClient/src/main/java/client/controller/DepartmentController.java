package client.controller;

//import rest.dao.DepartmentDao;
//import Department;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import client.model.Department;
import client.model.Departments;
import client.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 *  Class controler for spring MVC
 */
@Controller
@RequestMapping(value="/department")
public class DepartmentController {

//    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/listdata", method = RequestMethod.GET)
    @ResponseBody
    public Departments listData() {
        List<Department> departmentList = new ArrayList<Department>();
        departmentList.add(new Department(1L, "Dep1", 234.0));
        departmentList.add(new Department(2L, "Dep3", 2000.0));
        departmentList.add(new Department(3L, "Dep3", 120.0));
        return new Departments(departmentList);
    }

    @RequestMapping(value = "/table_Department", method = RequestMethod.GET)
    public ModelAndView getDepartmentLIst() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tableDepartment");
        List<Department> departmentList = new ArrayList<Department>();
        departmentList.add(new Department(1L, "Dep1", 234.0));
        departmentList.add(new Department(2L, "Dep3", 2000.0));
        departmentList.add(new Department(3L, "Dep3", 120.0));
        modelAndView.addObject("modelDepartmentList", departmentList);
        Departments depList = departmentService.getList();
        modelAndView.addObject("modelDepartmentList", depList.getDepartments());
        return modelAndView;
    }
}
