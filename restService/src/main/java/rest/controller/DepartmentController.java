package rest.controller;

//import rest.dao.DepartmentDao;
//import rest.model.Department;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import rest.model.Departments;
import rest.service.DepartmentService;

import java.util.List;

/**
 *  Class controler for spring MVC
 */
@Controller
@RequestMapping(value="/department")
public class DepartmentController {

//    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    // for processing department in datebase
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/listdata", method = RequestMethod.GET)
    @ResponseBody
    public Departments listData() {
        return new Departments(departmentService.getList());
    }
}
