package client.model;

import java.io.Serializable;
import java.util.List;

public class Employees implements Serializable {

    private List<Employee> employees;

    public Employees() {}

    public Employees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
