/**
 * 
 */
package lm.com.brainhoney.dao;

import java.util.List;

import lm.com.brainhoney.model.User;

/**
 * @author mithun.mondal
 *
 */
public interface UserDAO {

	public void addUser(User user);
	 
	public List<User> listUser();
	 
	public void removeUser(Integer id);
}
