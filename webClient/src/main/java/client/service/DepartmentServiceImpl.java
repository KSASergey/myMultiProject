package client.service;

import client.model.Department;
import client.model.Departments;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("departmentService")
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    RestTemplate restTemplate;

    private static final String URL_GET_ALL_CONTACTS =
            "http://localhost/restService/restful/department/listdata";

    public void insert(Department department) {
//        departmentDao.insert(department);
    }

    public Department get(Long id) {
//        return departmentDao.get(id);
        return null;
    }

    public Departments getList() {
//        return Lists.newArrayList(departmentDao.getList());

        System.out.println("Testing retrieve all contacts:");
        Departments departments =
                restTemplate.getForObject(URL_GET_ALL_CONTACTS, Departments.class);
        return departments;
    }

    public void update(Department department) {
//        departmentDao.update(department);
    }

    public void delete(Long id) {
//        departmentDao.delete(id);
    }
}
