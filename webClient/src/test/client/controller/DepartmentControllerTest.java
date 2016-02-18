package client.controller;

import client.model.Department;
import org.junit.Assert;
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

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml",
                                   "classpath:META-INF/spring/restful-client-app-context.xml"})
@WebAppConfiguration
public class DepartmentControllerTest {

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
    public void testGetDepartmentLIst() throws Exception {
        logger.debug("Start testGetDepartmentLIst");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/department/table_Department");
        ResultActions result = mockMvc.perform(request);
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(forwardedUrl("/WEB-INF/views/department/tableDepartment.jsp"));
        result.andExpect(view().name("department/tableDepartment"));
        result.andExpect(model().attributeExists("modelDepartmentList"));
        ModelAndView modelAndView = result.andReturn().getModelAndView();

        List<Department> departmentList = (List<Department>) modelAndView.getModel().get("modelDepartmentList");
        Assert.assertTrue(departmentList.size() > 3);
        logger.debug("Finish testGetDepartmentLIst");
    }

    @Test
    public void testAddDepartment() throws Exception {
        logger.debug("Start testAddDepartment");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/department/add_Department");
        ResultActions result = mockMvc.perform(request);
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(forwardedUrl("/WEB-INF/views/department/addDepartment.jsp"));
        result.andExpect(view().name("department/addDepartment"));
        result.andExpect(model().attributeExists("modelDepartment"));
        logger.debug("Finish testAddDepartment");
    }

    @Test
    public void testDeleteDepartment() throws Exception {
        logger.debug("Start testGetEmployeeLIst");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/department/delete_Department")
                .param("id","3");
        ResultActions result = mockMvc.perform(request);
        result.andDo(print());
        result.andExpect(status().is3xxRedirection());
        result.andExpect(view().name("redirect:/client/department/table_Department"));
        result.andExpect(redirectedUrl("/client/department/table_Department"));
        logger.debug("Finish testGetEmployeeLIst");
    }

    @Test
    public void testInsertDepartment() throws Exception {
        logger.debug("Start testInsertDepartment");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/department/insert_Department")
                .param("departmentName", "Department99");
        ResultActions result = mockMvc.perform(request);
        result.andDo(print());
        result.andExpect(status().is3xxRedirection());
        result.andExpect(view().name("redirect:/client/department/table_Department"));
        result.andExpect(redirectedUrl("/client/department/table_Department"));
        result.andExpect(model().attributeExists("modelDepartment"));

        Department department = (Department) result.andReturn().getModelAndView().getModel().get("modelDepartment");
        Assert.assertTrue(department.getId() > 0);
        Assert.assertEquals(department.getDepartmentName(), "Department99");
        logger.debug("Finish testInsertDepartment");
    }

    @Test
    public void testEditDepartment() throws Exception {
        logger.debug("Start testEditDepartment");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/department/edit_Department")
                .param("id", "1");
        ResultActions result = mockMvc.perform(request);
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(forwardedUrl("/WEB-INF/views/department/editDepartment.jsp"));
        result.andExpect(view().name("department/editDepartment"));
        result.andExpect(model().attributeExists("modelDepartment"));
        logger.debug("Finish testEditDepartment");
    }

    @Test
    public void testUpdateDepartment() throws Exception {
        logger.debug("Start testUpdateDepartment");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/department/update_Department")
                .param("id", "1")
                .param("departmentName", "Department19");
        ResultActions result = mockMvc.perform(request);
        result.andDo(print());
        result.andExpect(status().is3xxRedirection());
        result.andExpect(view().name("redirect:/client/department/table_Department"));
        result.andExpect(redirectedUrl("/client/department/table_Department"));
        result.andExpect(model().attributeExists("modelDepartment"));

        Department department = (Department) result.andReturn().getModelAndView().getModel().get("modelDepartment");
        Assert.assertTrue(department.getId() == 1);
        Assert.assertEquals(department.getDepartmentName(), "Department19");
        logger.debug("Finish testUpdateDepartment");
    }
}