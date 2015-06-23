/**
 * 
 */
package lm.com.brainhoney.configuration;

import java.util.Properties;

import javax.sql.DataSource;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;

import lm.com.brainhoney.model.Session;

import org.dbunit.dataset.datatype.IDataTypeFactory;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;

/**
 * @author mithun.mondal
 *
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({ "lm.com.brainhoney.configuration" })
@PropertySource(value = { "classpath:app.properties" })
public class HibernateConfiguration {

	@Autowired
    private Environment environment;
	
	@Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "lm.com.brainhoney.model" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
     }
	
	@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }
	
	private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.schema_status"));
        return properties;        
    }
	
	@Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
	
	@Bean
	@Autowired
	public Session agilixSession() 
			throws NumberFormatException, 
			TransformerConfigurationException, 
			IllegalStateException, 
			ParserConfigurationException {
		
		Session agilixSession = new Session(
				environment.getRequiredProperty("session.agent"),
				environment.getRequiredProperty("session.server"),
				Integer.valueOf(environment.getRequiredProperty("session.timeout")),
				Boolean.valueOf(environment.getRequiredProperty("session.verbose")));
		
		return agilixSession;
	}
	
	@Bean
	public DatabaseConfigBean dbUnitDatabaseConfig() {
		DatabaseConfigBean dbUnitDatabaseConfig = new DatabaseConfigBean();
//		IDataTypeFactory iDataTypeFactory = 
		dbUnitDatabaseConfig.setDatatypeFactory(new PostgresqlDataTypeFactory());
		dbUnitDatabaseConfig.setCaseSensitiveTableNames(true);;
		dbUnitDatabaseConfig.setQualifiedTableNames(true);
		return dbUnitDatabaseConfig;
	}
	
	@Bean
	public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection() {
		DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection = new DatabaseDataSourceConnectionFactoryBean(dataSource());		
		dbUnitDatabaseConnection.setDatabaseConfig(dbUnitDatabaseConfig());
//		dbUnitDatabaseConnection.setSchema("brainhoney.public");
		
		return dbUnitDatabaseConnection;
	}

}
