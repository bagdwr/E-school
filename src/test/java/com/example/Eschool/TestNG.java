package com.example.Eschool;

import com.example.Eschool.Service.StudentService;
import com.example.Eschool.Service.StudentServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ESchoolApplication.class)
public class TestNG extends AbstractTestNGSpringContextTests {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private StudentService studentService;

    private MockMvc mockMvc;

    @BeforeClass
    public void setup() {
        System.out.println("BeforeClass");
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @BeforeMethod
    public void checkServer() throws Exception {
        System.out.println("checkServer is OK");
//        this.mockMvc.perform(get("/getStudents")).andDo(print()).andExpect(status().isOk());

    }
}
