/**
 * 
 */
package lm.com.brainhoney.service;

import java.util.List;

import lm.com.brainhoney.model.Domain;


/**
 * @author mithun.mondal
 *
 */
public interface DomainService {

	public void addDomain(Domain domain);
	
    public List<Domain> listDomain();
    
    public void removeDomain(Integer id);
    
    public Domain findById(Long id);
}
