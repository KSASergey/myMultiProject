package rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Department implements Serializable {

    private Long id;

    private String departmentName;

    private Double averageSalary;

    public Department() {
    }

    public Department (Long id, String departmentName, Double averageSalary) {
        this.id = id;
        this.departmentName = departmentName;
        this.averageSalary = averageSalary;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() { return this.id;}

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() { return this.departmentName; }

    public void setAverageSalary(Double averageSalary) {
        this.averageSalary = averageSalary;
    }

    public Double getAverageSalary() { return this.averageSalary; }

    @Override
    public String toString() {
        return "Department - Id:  " + id + ",  departmentName:  " + departmentName + ", average salary:  " + averageSalary;
    }
}
