package rest.model;

import java.io.Serializable;
import java.util.List;

public class Departments implements Serializable {

    private List<Department> departments;

    public Departments() {}

    public Departments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
