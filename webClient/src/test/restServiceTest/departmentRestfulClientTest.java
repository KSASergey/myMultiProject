package restServiceTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import client.model.Department;
import client.model.Departments;

import java.util.HashMap;
import java.util.Map;

public class departmentRestfulClientTest {

    private static final String URL_SERVER = "http://localhost";

    private static final String URL_GET_ALL_DepartmentS =
            URL_SERVER + "/restService/restful/department/listdata";
    private static final String URL_GET_Department_BY_ID =
            URL_SERVER + "/restService/restful/department/{id}";
    private static final String URL_CREATE_Department =
            URL_SERVER + "/restService/restful/department/";
    private static final String URL_UPDATE_Department =
            URL_SERVER + "/restService/restful/department/{id}";
    private static final String URL_DELETE_Department =
            URL_SERVER + "/restService/restful/department/{id}";

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/restful-client-app-context.xml");
        ctx.refresh();

        Department department;
        RestTemplate restTemplate = ctx.getBean("restTemplate", RestTemplate.class);

        System.out.println("Testing retrieve all departments:");
        Departments departments = restTemplate.getForObject(URL_GET_ALL_DepartmentS, Departments.class);
        listDepartments(departments);

        System.out.println("Testing retrieve a department by id :");
        department = restTemplate.getForObject(URL_GET_Department_BY_ID, Department.class, 1);
        System.out.println(department);
        System.out.println("");

        department = restTemplate.getForObject(URL_GET_Department_BY_ID, Department.class, 1);
        department.setDepartmentName("Test Department");
        System.out.println("Testing update department by id :");
        restTemplate.put(URL_UPDATE_Department, department, 1);
        System.out.println("Department update successfully: " + department);
        System.out.println("");

        restTemplate.delete(URL_DELETE_Department, 3);
        System.out.println("Testing delete department by id :");
        departments = restTemplate.getForObject(URL_GET_ALL_DepartmentS, Departments.class);
        listDepartments(departments);

        System.out.println("Testing create department :");
        Department departmentNew = new Department();
        departmentNew.setDepartmentName("New test Department");
        departmentNew = restTemplate.postForObject(URL_CREATE_Department, departmentNew, Department.class);
        System.out.println("Department created successfully: " + departmentNew);
    }

    private static void listDepartments(Departments departments) {
        for (Department department: departments.getDepartments()) {
            System.out.println(department);
        }

        System.out.println("");
    }
}
