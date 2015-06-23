/**
 * 
 */
package lm.com.brainhoney.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lm.com.brainhoney.model.Domain;
import lm.com.brainhoney.model.User;

/**
 * @author mithun.mondal
 *
 */
@Repository("userDAO")
public class UserDAOImpl extends AbstractDao implements UserDAO {

	@Autowired
    private SessionFactory sessionFactory;
	
	/**
	 * 
	 */
	public UserDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see lm.com.brainhoney.dao.UserDAO#addUser(lm.com.brainhoney.model.User)
	 */
	public void addUser(User user) {
		persist(user);

	}

	/* (non-Javadoc)
	 * @see lm.com.brainhoney.dao.UserDAO#listUser()
	 */
	@SuppressWarnings("unchecked")
	public List<User> listUser() {
		return (List<User>) getSession().createCriteria(User.class).list();
	}

	/* (non-Javadoc)
	 * @see lm.com.brainhoney.dao.UserDAO#removeUser(java.lang.Integer)
	 */
	public void removeUser(Integer id) {
		// TODO Auto-generated method stub

	}

}
