package rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee implements Serializable {

    private Long id;

    private Long departmentId;

    private String departmentName;

    private String fullName;

    private Date birthDate;

    private Double salary;

    public Employee() {
    }

    public Employee (Long id, Long departmentId, String fullName, Date birthDate, Double salary) {
        this.id = id;
        this.departmentId = departmentId;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.salary = salary;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public  Long getId() {
        return this.id;
    }

    public void setDepartmentId (Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long  getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentName (String departmentName) {
        this.departmentName = departmentName;
    }

    public String  getDepartmentName() {
        return this.departmentName;
    }

    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getFullName() { return this.fullName; }

    public void setBirthDate(Date BirthDate) {
        this.birthDate = BirthDate;
    }

    public Date  getBirthDate() {
        return this.birthDate;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double  getSalary() {
        return this.salary;
    }

    public String toString() {
            return "Employee - Id:  " + id + ",  departmentId:  " + departmentId + ",  departmentName:  " +
                    departmentName + ", fullName: " + fullName + ", birthDate: " + birthDate + ", salary: " + salary;
    }
}
