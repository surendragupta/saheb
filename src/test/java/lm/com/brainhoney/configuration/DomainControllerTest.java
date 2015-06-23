package lm.com.brainhoney.configuration;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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

public class DomainControllerTest {

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
	public void findAllDomain() throws Exception {
		mockMvc.perform(get("/findAllDomain"))
		.andExpect(status().isOk())
		.andExpect(view().name("actions"))
        .andExpect(forwardedUrl("/WEB-INF/views/jsp/actions.jsp"))
        .andExpect(model().attribute("domains", hasSize(2)))
        .andExpect(model().attribute("domains", hasItem(
        		allOf(
        				hasProperty("domainName", is("sinet-lm-dev")),
        				hasProperty("userSpace", is("saheb"))        				
        		)
        )))
        .andExpect(model().attribute("domains", hasItem(
        		allOf(
        				hasProperty("domainName", is("sinet-lm-dev2")),
        				hasProperty("userSpace", is("Mithun"))        				
        		)
        )));

	}
	
	@Test
	@DatabaseSetup(value = "/userData.xml")
    public void findById() throws Exception {
        mockMvc.perform(get("/domain/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("actions"))
                .andExpect(forwardedUrl("/WEB-INF/views/jsp/actions.jsp"))
                .andExpect(model().attribute("domain", hasProperty("domainId", is(1L))))
                .andExpect(model().attribute("domain", hasProperty("domainName", is("sinet-lm-dev"))))
                .andExpect(model().attribute("domain", hasProperty("userSpace", is("saheb"))));
    }

}
