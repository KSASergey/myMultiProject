package rest.model;

//import javax.persistence.*;
import java.io.Serializable;

//@Entity
//@Table(name = "department")
public class Department implements Serializable {

//    @Id
//    @Column(name = "ID")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name="DepartmentName")
    private String departmentName;

    private Double averageSalary;

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
