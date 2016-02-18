package rest.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import rest.model.Employee;
import rest.model.Employees;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static rest.service.MyDateUtils.ConvertToSQLDate;
import static rest.service.MyDateUtils.ConvertToUtilDate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml",
        "classpath:testRest-context.xml"})
@WebAppConfiguration
public class EmployeeControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeControllerTest.class);

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    protected MockHttpSession mockSession;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        mockSession = new MockHttpSession(wac.getServletContext(), UUID.randomUUID().toString());
    }

    @Test
    public void testListData() throws Exception {
        logger.debug("Start testListData");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/employee/listdata/1");
        ResultActions result = mockMvc.perform(request);
        result.andDo(print());
        result.andExpect(content().contentType("application/json;charset=UTF-8"));
        result.andExpect(status().isOk());

        String json = result.andReturn().getResponse().getContentAsString();
        Employees employees = gson.fromJson(json, Employees.class);
        assertTrue(employees.getEmployees().size() > 1);
        logger.debug("Finish testListData");
    }

    @Test
    public void testFind() throws Exception {
        logger.debug("Start testFind");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/employee/1");
        ResultActions result = mockMvc.perform(request);
        result.andDo(print());
        result.andExpect(content().contentType("application/json;charset=UTF-8"));
        result.andExpect(status().isOk());

        String json = result.andReturn().getResponse().getContentAsString();
        Employee employee = gson.fromJson(json, Employee.class);
        assertTrue(employee.getId() == 1L);
        assertTrue(employee.getDepartmentId() == 1L);
        assertEquals(employee.getDepartmentName(),"Department1");
        assertEquals(employee.getFullName(),"Petr");
        assertTrue(employee.getBirthDate().getTime() == ConvertToSQLDate("1985-08-21").getTime());
        assertTrue(employee.getSalary() == 1992.0);
        logger.debug("Finish testFind");
    }

    @Test
    public void testCreate() throws Exception {
        logger.debug("Start testCreate");
        Employee employee = new Employee();
        employee.setDepartmentId(1L);
        employee.setFullName("creat name");
        employee.setBirthDate(ConvertToSQLDate("1989-02-10"));
        employee.setSalary(222.0);
        String json = gson.toJson(employee);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/employee/");
        request.contentType(APPLICATION_JSON_UTF8);
        request.content(json);
        ResultActions result = mockMvc.perform(request);
        result.andDo(print());
        result.andExpect(content().contentType("application/json;charset=UTF-8"));
        result.andExpect(status().isOk());

        json = result.andReturn().getResponse().getContentAsString();
        employee = gson.fromJson(json, Employee.class);
        assertTrue(employee.getId() == 6L);
        assertTrue(employee.getDepartmentId() == 1L);
        assertEquals(employee.getDepartmentName(),"Department1");
        assertEquals(employee.getFullName(),"creat name");
        assertTrue(employee.getBirthDate().getTime() == ConvertToSQLDate("1989-02-10").getTime());
        assertTrue(employee.getSalary() == 222.0);
        logger.debug("Finish testCreate");
    }

    @Test
    public void testUpdate() throws Exception {
        logger.debug("Start testUpdate");
        Employee employee = new Employee();
        employee.setDepartmentId(1L);
        employee.setFullName("update name");
        employee.setBirthDate(ConvertToSQLDate("1999-11-11"));
        employee.setSalary(444.0);
        String json = gson.toJson(employee);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/employee/2");
        request.contentType(APPLICATION_JSON_UTF8);
        request.content(json);
        ResultActions result = mockMvc.perform(request);
        result.andDo(print());
        result.andExpect(content().contentType("application/json;charset=UTF-8"));
        result.andExpect(status().isOk());

        json = result.andReturn().getResponse().getContentAsString();
        employee = gson.fromJson(json, Employee.class);
        assertTrue(employee.getId() == 2L);
        assertTrue(employee.getDepartmentId() == 1L);
        assertEquals(employee.getDepartmentName(),"Department1");
        assertEquals(employee.getFullName(),"update name");
        assertTrue(employee.getBirthDate().getTime() == ConvertToSQLDate("1999-11-11").getTime());
        assertTrue(employee.getSalary() == 444.0);
        logger.debug("Finish testUpdate");
    }

    @Test
    public void testDelete() throws Exception {
        logger.debug("Start testDelete");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/employee/5");
        ResultActions result = mockMvc.perform(request);
        result.andDo(print());
        result.andExpect(status().isOk());
        logger.debug("Finish testDelete");
    }

    @Test
    public void testListBetween() throws Exception {
        logger.debug("Start testListBetween");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/employee/listdata/birthDate");
        request.param("firstBirthDate", "1985-01-29");
        request.param("lastBirthDate", "2007-11-11");
        ResultActions result = mockMvc.perform(request);
        result.andDo(print());
        result.andExpect(content().contentType("application/json;charset=UTF-8"));
        result.andExpect(status().isOk());

        String json = result.andReturn().getResponse().getContentAsString();
        Employees employees = gson.fromJson(json, Employees.class);
        assertTrue(employees.getEmployees().size() > 0);
        logger.debug("Finish testListBetween");
    }
}