package rest.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rest.dao.DepartmentDao;
import rest.model.Department;

import java.util.List;

@Service("departmentService")
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    public void insert(Department department) {departmentDao.insert(department);}

    @Transactional(readOnly=true)
    public Department get(Long id) {
        return departmentDao.get(id);
    }

    @Transactional(readOnly=true)
    public List<Department> getList() {
        return Lists.newArrayList(departmentDao.getList());
    }

    public void update(Department department) {
        departmentDao.update(department);
    }

    public void delete(Long id) {
        departmentDao.delete(id);
    }
}
