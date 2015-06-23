package lm.com.brainhoney.configuration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = { HibernateConfiguration.class })
@TestExecutionListeners({ 
	DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class
})
//@DbUnitConfiguration(databaseConnection = "dbUnitDatabaseConnection")
public class BHControllerTest {

	@Resource
    private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }
		
	@Test
	@DatabaseSetup(value = "/userData.xml")	
	public void findAllUser() throws Exception {
		mockMvc.perform(get("/findAllUser"))
		.andExpect(status().isOk())
		.andExpect(view().name("actions"))
        .andExpect(forwardedUrl("/WEB-INF/views/jsp/actions.jsp"))
        .andExpect(model().attribute("users", hasSize(2)))
        .andExpect(model().attribute("users", hasItem(
        		allOf(
        				hasProperty("userName", is("mithun.mondal")),
        				hasProperty("userPassword", is("1234")),
        				hasProperty("domainId", is(0L))
        		)
        )))
        .andExpect(model().attribute("users", hasItem(
        		allOf(
        				hasProperty("userName", is("saheb.mondal")),
        				hasProperty("userPassword", is("0987")),
        				hasProperty("domainId", is(0L))        				
        		)
        )));

	}

}
