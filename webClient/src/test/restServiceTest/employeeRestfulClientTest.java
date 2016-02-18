package restServiceTest;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.client.RestTemplate;
import client.model.Employee;
import client.model.Employees;
import java.text.ParseException;
import static client.service.MyDateUtils.ConvertToSQLDate;

public class employeeRestfulClientTest {

    private static final String URL_SERVER = "http://localhost";

    private static final String URL_GET_ALL_EmployeeS =
            URL_SERVER + "/restService/restful/employee/listdata/{id}";
    private static final String URL_GET_Employee_BY_ID =
            URL_SERVER + "/restService/restful/employee/{id}";
    private static final String URL_CREATE_Employee =
            URL_SERVER + "/restService/restful/employee/";
    private static final String URL_UPDATE_Employee =
            URL_SERVER + "/restService/restful/employee/{id}";
    private static final String URL_DELETE_Employee =
            URL_SERVER + "/restService/restful/employee/{id}";
    private static final String URL_FIND_BETWEEN_EmployeeS =
            URL_SERVER + "/restService/restful/employee/listdata/birthDate";

    public static void main(String[] args) throws ParseException {

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/restful-client-app-context.xml");
        ctx.refresh();

        Employee employee;
        RestTemplate restTemplate = ctx.getBean("restTemplate", RestTemplate.class);

        System.out.println("Testing retrieve all employees:");
        Employees employees = restTemplate.getForObject(URL_GET_ALL_EmployeeS, Employees.class, 1);
        listEmployees(employees);

        Long id = 1L;
        employees = restTemplate.getForObject(URL_GET_ALL_EmployeeS, Employees.class, id);
        listEmployees(employees);


        System.out.println("Testing retrieve a employee by id :");
        employee = restTemplate.getForObject(URL_GET_Employee_BY_ID, Employee.class, 1);
        System.out.println(employee);
        System.out.println("");

        employee = restTemplate.getForObject(URL_GET_Employee_BY_ID, Employee.class, 7);
        employee.setDepartmentId(2L);
        employee.setFullName("Update test Employee");
        employee.setBirthDate(ConvertToSQLDate("1982-11-10"));
        employee.setSalary(255.0);
        System.out.println("Testing update employee by id :");
        restTemplate.put(URL_UPDATE_Employee, employee, 1);
        System.out.println("Employee update successfully: " + employee);
        System.out.println("");

        restTemplate.delete(URL_DELETE_Employee, 3);
        System.out.println("Testing delete employee by id :");
        employees = restTemplate.getForObject(URL_GET_ALL_EmployeeS, Employees.class, 2);
        listEmployees(employees);

        System.out.println("Testing create employee :");
        Employee employeeNew = new Employee();
        employeeNew.setDepartmentId(2L);
        employeeNew.setFullName("New test Employee");
        employeeNew.setBirthDate(ConvertToSQLDate("1982-02-13"));
        employeeNew.setSalary(378.0);
        employeeNew = restTemplate.postForObject(URL_CREATE_Employee, employeeNew, Employee.class);
        System.out.println("Employee created successfully: " + employeeNew);

        String params = "?firstBirthDate=1987-02-13&lastBirthDate=2015-12-29";
        employees = restTemplate.getForObject(URL_FIND_BETWEEN_EmployeeS + params, Employees.class);
        listEmployees(employees);
    }

    private static void listEmployees(Employees employees) {
        for (Employee employee: employees.getEmployees()) {
            System.out.println(employee);
        }

        System.out.println("");
    }
}
