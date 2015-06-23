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
@Table(name="USERS")
public class User implements Serializable, Models {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	
	@Column(name = "domain_id")
	private long domainId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="prefix_name")
	private String prefixName;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_password")
	private String userPassword;
	
	@Transient
	private String baseName;
	@Transient
	private String omittedJsonList;
	
	/**
	 * 
	 */
	public User() {}
	
	public User(String baseName, String omittedJsonList) {
		this.baseName = baseName;
		this.omittedJsonList = omittedJsonList;
	}
	
	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public String getPrefixName() {
		return prefixName;
	}
	
	public void setPrefixName(String prefixName) {
		this.prefixName = prefixName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public String getOmittedJsonList() {
		return omittedJsonList;
	}
	
	public void setOmittedJsonList(String omittedJsonList) {
		this.omittedJsonList = omittedJsonList;
	}

	public long getDomainId() {
		return domainId;
	}

	public void setDomainId(long domainId) {
		this.domainId = domainId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
}
