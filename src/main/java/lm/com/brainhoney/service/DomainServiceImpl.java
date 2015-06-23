/**
 * 
 */
package lm.com.brainhoney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lm.com.brainhoney.dao.DomainDAO;
import lm.com.brainhoney.model.Domain;


/**
 * @author mithun.mondal
 *
 */
@Service("domainService")
@Transactional
public class DomainServiceImpl implements DomainService {

	@Autowired
    private DomainDAO domainDAO;
	
	/**
	 * 
	 */
	public DomainServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see lm.com.brainhoney.service.DomainService#addDomain(lm.com.brainhoney.model.Domain)
	 */
	@Transactional
	public void addDomain(Domain domain) {
		domainDAO.addDomain(domain);
	}

	/* (non-Javadoc)
	 * @see lm.com.brainhoney.service.DomainService#listDomain()
	 */
	@Transactional
	public List<Domain> listDomain() {
		return domainDAO.listDomain();
	}

	/* (non-Javadoc)
	 * @see lm.com.brainhoney.service.DomainService#removeDomain(java.lang.Integer)
	 */
	@Transactional
	public void removeDomain(Integer id) {
		// TODO Auto-generated method stub

	}

	public Domain findById(Long id) {		
		return domainDAO.findById(id);
	}

}
