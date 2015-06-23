/**
 * 
 */
package lm.com.brainhoney.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author mithun.mondal
 *
 */
@Entity
@Table(name="DOMAIN")
public class Domain implements Serializable, Models {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="domain_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long domainId;
	
	@Column(name="reference_name")
	private String reference;
	
	@Column(name="domain_name")
	private String domainName;
	
	@Column(name="user_space")
	private String userSpace;
	
	@Column(name="parent_id")
	private long parentId;
	
	@Transient
	private String baseName;
	@Transient
	private String omittedJsonList;

	/**
	 * 
	 */
	public Domain() {}
	
	public Domain(String baseName, String omittedJsonList) {
		this.baseName = baseName;
		this.omittedJsonList = omittedJsonList;
	}

	
	public long getDomainId() {
		return domainId;
	}


	public void setDomainId(long domainId) {
		this.domainId = domainId;
	}


	public String getReference() {
		return reference;
	}


	public void setReference(String reference) {
		this.reference = reference;
	}


	public String getDomainName() {
		return domainName;
	}


	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}


	public String getUserSpace() {
		return userSpace;
	}


	public void setUserSpace(String userSpace) {
		this.userSpace = userSpace;
	}


	public long getParentId() {
		return parentId;
	}


	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	
	public String getBaseName() {
		return baseName;
	}


	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}


	public void setOmittedJsonList(String omittedJsonList) {
		this.omittedJsonList = omittedJsonList;
	}


	public String getOmittedJsonList() {		
		return omittedJsonList;
	}

}
