/**
 * 
 */
package lm.com.brainhoney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lm.com.brainhoney.dao.UserDAO;
import lm.com.brainhoney.model.User;

/**
 * @author mithun.mondal
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
    private UserDAO userDAO;
	/**
	 * 
	 */
	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see lm.com.brainhoney.service.UserService#addUser(lm.com.brainhoney.model.User)
	 */
	@Transactional
	public void addUser(User user) {
		userDAO.addUser(user);
	}

	/* (non-Javadoc)
	 * @see lm.com.brainhoney.service.UserService#listUser()
	 */
	public List<User> listUser() {
		return userDAO.listUser();
	}

	/* (non-Javadoc)
	 * @see lm.com.brainhoney.service.UserService#removeUser(java.lang.Integer)
	 */
	public void removeUser(Integer id) {
		// TODO Auto-generated method stub

	}

}
