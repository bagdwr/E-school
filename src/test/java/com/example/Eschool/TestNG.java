package com.example.Eschool;

import com.example.Eschool.Service.StudentService;
import com.example.Eschool.Service.StudentServiceImpl;
import com.example.Eschool.Service.UserService;
import com.example.Eschool.Service.UserServiceImpl;
import org.apache.catalina.core.ApplicationContext;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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

    private MockMvc mockMvc;

    @BeforeClass
    public void setup() {
        System.out.println("BeforeClass");
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @BeforeMethod
    public void checkServer() throws Exception {
        System.out.println("checkServer is OK");
    }

    @Test
    public void checkStudentService(){
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(ESchoolApplication.class);
        StudentService studentService=context.getBean(StudentService.class);
        Assert.assertNotNull(studentService.getStudentById(1L));
    }

    @Test
    public void userService(){
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(ESchoolApplication.class);
        UserService userService=context.getBean(UserService.class);
        Assert.assertNotNull(userService.getUserByUsername("user"));
    }

}
