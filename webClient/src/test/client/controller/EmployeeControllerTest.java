package client.controller;

import client.model.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import static client.service.MyDateUtils.ConvertToSQLDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml",
                                   "classpath:META-INF/spring/restful-client-app-context.xml"})
@WebAppConfiguration
public class EmployeeControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeControllerTest.class);

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    protected MockHttpSession mockSession;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        mockSession = new MockHttpSession(wac.getServletContext(), UUID.randomUUID().toString());
    }

    @Test
    public void testGetEmployeeLIst() throws Exception {
        logger.debug("Start testGetEmployeeLIst");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/employee/table_Employee")
                .param("id", "2");
        ResultActions result = mockMvc.perform(request);
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(view().name("employee/tableEmployee"));
        result.andExpect(forwardedUrl("/WEB-INF/views/employee/tableEmployee.jsp"));
        result.andExpect(model().attributeExists("modelDepartment"));
        result.andExpect(model().attributeExists("modelEmployeeList"));
        ModelAndView modelAndView = result.andReturn().getModelAndView();
        List<Employee> employeeList = (List<Employee>) modelAndView.getModel().get("modelEmployeeList");
        assertTrue(employeeList.size() > 0);
        logger.debug("Finish testGetEmployeeLIst");
    }

    @Test
    public void testAddEmployee() throws Exception {
        logger.debug("Start testAddEmployee");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/employee/add_Employee")
                .param("id", "1");
        ResultActions result = mockMvc.perform(request);
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(view().name("employee/addEmployee"));
        result.andExpect(forwardedUrl("/WEB-INF/views/employee/addEmployee.jsp"));
        result.andExpect(model().attributeExists("tempDepartment"));
        result.andExpect(model().attributeExists("modelEmployee"));
        logger.debug("Finish testAddEmployee");
    }

    @Test
    public void testInsertEmployee() throws Exception {
        logger.debug("Start testInsertEmployee");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/employee/insert_Employee")
                .param("departmentId", "1")
                .param("FullName", "Control Name")
                .param("BirthDate", "2009-10-23")
                .param("Salary", "1050");
        ResultActions result = mockMvc.perform(request);
        result.andDo(print());
        result.andExpect(status().is3xxRedirection());
        result.andExpect(view().name("redirect:/client/employee/table_Employee?id=1"));
        result.andExpect(redirectedUrl("/client/employee/table_Employee?id=1"));
        result.andExpect(model().attributeExists("modelEmployee"));

        Employee employee = (Employee) result.andReturn().getModelAndView().getModel().get("modelEmployee");
        assertTrue(employee.getId() > 0);
        assertTrue(employee.getDepartmentId() == 1L);
        DateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals(myDateFormat.format(employee.getBirthDate()), "2009-10-23");
        assertTrue(employee.getSalary() == 1050);
        logger.debug("Finish testInsertEmployee");
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        logger.debug("Start testDeleteEmployee");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/employee/delete_Employee")
                .param("id","2")
                .param("departmentId","1");
        ResultActions result = mockMvc.perform(request);
        result.andDo(print());
        result.andExpect(status().is3xxRedirection());
        result.andExpect(view().name("redirect:/client/employee/table_Employee?id=1"));
        result.andExpect(redirectedUrl("/client/employee/table_Employee?id=1"));
        logger.debug("Finish testDeleteEmployee");
    }

    @Test
    public void testEditEmployee() throws Exception {
        logger.debug("Start testEditEmployee");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/employee/edit_Employee")
                .param("id", "1");
        ResultActions result = mockMvc.perform(request);
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(forwardedUrl("/WEB-INF/views/employee/editEmployee.jsp"));
        result.andExpect(view().name("employee/editEmployee"));
        result.andExpect(model().attributeExists("modelEmployee"));
        logger.debug("Finish testEditEmployee");
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        logger.debug("Start testUpdateEmployee");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/employee/update_Employee")
                .param("id", "1")
                .param("departmentId", "1")
                .param("FullName", "Edit Name")
                .param("BirthDate", "1989-01-17")
                .param("Salary", "150");
        ResultActions result = mockMvc.perform(request);
        result.andDo(print());
        result.andExpect(status().is3xxRedirection());
        result.andExpect(view().name("redirect:/client/employee/table_Employee?id=1"));
        result.andExpect(redirectedUrl("/client/employee/table_Employee?id=1"));
        result.andExpect(model().attributeExists("modelEmployee"));

        Employee employee = (Employee) result.andReturn().getModelAndView().getModel().get("modelEmployee");
        assertTrue(employee.getId() == 1);
        assertEquals(employee.getFullName(), "Edit Name");
        assertEquals(employee.getBirthDate(), ConvertToSQLDate("1989-01-17"));
        assertTrue(employee.getSalary() == 150);
        logger.debug("Finish testUpdateEmployee");
    }

    @Test
    public void testFindEmployee() throws Exception {
        logger.debug("Start testFindEmployee");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/employee/find_Employee")
                .param("firstBirthDate", "1985-08-21")
                .param("lastBirthDate", "1985-08-21");
        ResultActions result = mockMvc.perform(request);
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(forwardedUrl("/WEB-INF/views/employee/findEmployee.jsp"));
        result.andExpect(view().name("employee/findEmployee"));
        result.andExpect(model().attributeExists("modelEmployeeList"));
        result.andExpect(model().attributeExists("searshRange"));
        List<Employee> employeeList = (List<Employee>) result.andReturn().getModelAndView().getModel().get("modelEmployeeList");
        assertTrue(employeeList.size() > 1);

        request = MockMvcRequestBuilders.get("/employee/find_Employee")
                .param("firstBirthDate", "1981-08-21")
                .param("lastBirthDate", "2015-08-21");
        result = mockMvc.perform(request);
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(forwardedUrl("/WEB-INF/views/employee/findEmployee.jsp"));
        result.andExpect(view().name("employee/findEmployee"));
        result.andExpect(model().attributeExists("modelEmployeeList"));
        result.andExpect(model().attributeExists("searshRange"));
        employeeList = (List<Employee>) result.andReturn().getModelAndView().getModel().get("modelEmployeeList");
        assertTrue(employeeList.size() > 3);

        request = MockMvcRequestBuilders.get("/employee/find_Employee")
                .param("firstBirthDate", "1985-08-21");
        result = mockMvc.perform(request);
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(forwardedUrl("/WEB-INF/views/employee/findEmployee.jsp"));
        result.andExpect(view().name("employee/findEmployee"));
        result.andExpect(model().attributeExists("modelEmployeeList"));
        result.andExpect(model().attributeExists("searshRange"));
        employeeList = (List<Employee>) result.andReturn().getModelAndView().getModel().get("modelEmployeeList");
        assertTrue(employeeList.size() > 1);
        logger.debug("Finish testFindEmployee");
    }
}