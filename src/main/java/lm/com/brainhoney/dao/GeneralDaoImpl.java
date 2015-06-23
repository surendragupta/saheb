package lm.com.brainhoney.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("GeneralDao")
public class GeneralDaoImpl implements GeneralDao {

//	@Autowired
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	/*public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}*/
	
	public void insertUser(String ... param) {
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
		String sql = "";
		Object[] args = new Object[param.length];
		if (param.length == 3) {
			sql = "insert into \"user\" (prefix_name, user_name, user_password) VALUES (?,?,?)";			
		}
		int i = 0;
		for (String item : param) {
			args[i] = item;
			i++;
		}
		try {
			int out = jdbcTemplate.update(sql, args);
			if(out !=0){
	            System.out.println("User saved...");
	        }else System.out.println("User save failed....");
		} catch (DataAccessException e) {
			System.out.println("DataAccessException " + e.getMessage());
		}
	}

}
