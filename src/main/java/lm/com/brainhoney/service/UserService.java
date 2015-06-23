/**
 * 
 */
package lm.com.brainhoney.service;

import java.util.List;

import lm.com.brainhoney.model.User;

/**
 * @author mithun.mondal
 *
 */
public interface UserService {

	public void addUser(User user);
	
    public List<User> listUser();
    
    public void removeUser(Integer id);
}
