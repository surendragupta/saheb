/**
 * 
 */
package lm.com.brainhoney.dao;

import java.util.List;






import org.hibernate.SessionFactory;
//import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lm.com.brainhoney.model.Domain;

/**
 * @author mithun.mondal
 *
 */

@Repository("domainDAO")
public class DomainDAOImpl extends AbstractDao implements DomainDAO {

	@Autowired
    private SessionFactory sessionFactory;
	
	/**
	 * 
	 */
	public DomainDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see lm.com.brainhoney.dao.DomainDao#addContact(lm.com.brainhoney.model.Domain)
	 */
	public void addDomain(Domain domain) {
		persist(domain);

	}

	/* (non-Javadoc)
	 * @see lm.com.brainhoney.dao.DomainDao#listDomain()
	 */
	@SuppressWarnings("unchecked")
	public List<Domain> listDomain() {
		return (List<Domain>) getSession().createCriteria(Domain.class).list();
	}

	/* (non-Javadoc)
	 * @see lm.com.brainhoney.dao.DomainDao#removeDomain(java.lang.Integer)
	 */
	public void removeDomain(Integer id) {
		// TODO Auto-generated method stub

	}

	public Domain findById(Long id) {
		return (Domain) getSession().get(Domain.class, id);
	}

}
