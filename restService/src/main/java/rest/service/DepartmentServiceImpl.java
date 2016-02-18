package rest.service;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rest.dao.DepartmentDao;
import rest.model.Department;

import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Autowired
    private DepartmentDao departmentDao;

    /**
     * insert a new department
     * @param department a new department
     * @return a new department
     */
    @Transactional
    public Department insert(Department department) {
        logger.debug("create department :");
        return departmentDao.insert(department);
    }

    /**
     * Retrieves a single department
     * @param id the id of the existing department
     * @return the existing department
     */
    @Transactional(readOnly = true)
    public Department get(Long id) {
        logger.debug("retrieve a department by id : {}", id);
        return departmentDao.get(id);
    }

    /**
     * Retrieves all department
     * @return a list of department
     */
    @Transactional(readOnly = true)
    public List<Department> getList() {
        logger.debug("retrieve all departments:");
        return Lists.newArrayList(departmentDao.getList());
    }

    /**
     * update an existing department
     * @param department the editing department
     */
    @Transactional
    public Department update(Department department) {
        logger.debug("update department by id :");
        return departmentDao.update(department);
    }

    /**
     * Deletes an existing department
     * @param id the id of the existing department
     */
    @Transactional
    public void delete(Long id) {
        logger.debug("delete department by id : {}", id);
        departmentDao.delete(id);
    }
}
