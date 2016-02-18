package rest.controller;

import com.google.gson.Gson;
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
import rest.model.Department;
import rest.model.Departments;

import java.nio.charset.Charset;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml",
                                   "classpath:testRest-context.xml"})
@WebAppConfiguration
public class DepartmentControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentControllerTest.class);

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

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
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/department/listdata");
        ResultActions result = mockMvc.perform(request);
        result.andExpect(content().contentType("application/json;charset=UTF-8"));
        result.andDo(print());
        result.andExpect(status().isOk());

        Gson gson = new Gson();
        String json = result.andReturn().getResponse().getContentAsString();
        Departments departments = gson.fromJson(json, Departments.class);
        Department department = departments.getDepartments().get(0);
        assertTrue(department.getId() == 1L);
        assertEquals(department.getDepartmentName(),"Department1");
        assertTrue(department.getAverageSalary() == 1996.0);
        logger.debug("Finish testListData");
    }

    @Test
    public void testFind() throws Exception {
        logger.debug("Start testFind");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/department/1");
        ResultActions result = mockMvc.perform(request);
        result.andExpect(content().contentType("application/json;charset=UTF-8"));
        result.andDo(print());
        result.andExpect(status().isOk());

        Gson gson = new Gson();
        String json = result.andReturn().getResponse().getContentAsString();
        Department department = gson.fromJson(json, Department.class);
        assertTrue(department.getId() == 1L);
        assertEquals(department.getDepartmentName(),"Department1");
        assertTrue(department.getAverageSalary() == 1996.0);
        logger.debug("Finish testFind");
    }

    @Test
    public void testCreate() throws Exception {
        logger.debug("Start testCreate");
        Department department = new Department();
        department.setDepartmentName("creat name");
        Gson gson = new Gson();
        String json = gson.toJson(department);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/department/");
        request.contentType(APPLICATION_JSON_UTF8);
        request.content(json);
        ResultActions result = mockMvc.perform(request);
        result.andExpect(content().contentType("application/json;charset=UTF-8"));
        result.andDo(print());
        result.andExpect(status().isOk());

        json = result.andReturn().getResponse().getContentAsString();
        department = gson.fromJson(json, Department.class);
        assertTrue(department.getId() == 4L);
        assertEquals(department.getDepartmentName(),"creat name");
        assertTrue(department.getAverageSalary() == 0.0);
        logger.debug("Finish testCreate");
    }

    @Test
    public void testUpdate() throws Exception {
        logger.debug("Start testUpdate");
        Department department = new Department();
//        department.setId(2L);
        department.setDepartmentName("update name");
        Gson gson = new Gson();
        String json = gson.toJson(department);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/department/2");
        request.contentType(APPLICATION_JSON_UTF8);
        request.content(json);
        ResultActions result = mockMvc.perform(request);
        result.andExpect(content().contentType("application/json;charset=UTF-8"));
        result.andDo(print());
        result.andExpect(status().isOk());

        json = result.andReturn().getResponse().getContentAsString();
        department = gson.fromJson(json, Department.class);
        assertTrue(department.getId() == 2L);
        assertEquals(department.getDepartmentName(),"update name");
        assertTrue(department.getAverageSalary() == 0.0);
        logger.debug("Finish testUpdate");
    }

    @Test
    public void testDelete() throws Exception {
        logger.debug("Start testDelete");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/department/3");
        ResultActions result = mockMvc.perform(request);
        result.andDo(print());
        result.andExpect(status().isOk());
        logger.debug("Finish testDelete");
    }
}