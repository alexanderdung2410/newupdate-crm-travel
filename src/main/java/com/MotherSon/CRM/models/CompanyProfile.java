package com.MotherSon.CRM.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "companyprofile_master")
public class CompanyProfile {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@NotNull(message = "Company name must not be null")
    @Size(min = 1, max = 100, message = "Company name must be between 1 and 100 characters")
	private String companyname;
	
	
	@NotNull(message = "Phone number must not be null")
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Phone number must be valid (e.g. +1 123-456-7890 or 1234567890)")
	private String phoneNumber;
	
	
	
	@NotNull(message = "Email must not be null")
    @Email(message = "Email must be a valid email address")
	private String email;
	
	@NotNull(message = "Address must not be null")
    @Size(min = 1, max = 255, message = "Address must be between 1 and 255 characters")
	private String address;
	
	private List<String> websitefooterlogo;
	
	private List<String> websiteheaderlogo;
	
	private List<String> applogo;
	
	private List<String> websitefavicon;
	
	private List<String> loadinggif;
	
	private String ipAddress;
	
	private String createby;
	
	private String modifiedby;
	
	private boolean status;
	
	private boolean isdelete;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime modifiedDate;
	
	
	
	@PrePersist
	protected void onCreate() {
		createdDate = LocalDateTime.now();
		modifiedDate = LocalDateTime.now();
	}
	
	
	@PreUpdate
	protected void onUpdate() {
		modifiedDate = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<String> getWebsitefooterlogo() {
		return websitefooterlogo;
	}

	public void setWebsitefooterlogo(List<String> websitefooterlogo) {
		this.websitefooterlogo = websitefooterlogo;
	}

	public List<String> getWebsiteheaderlogo() {
		return websiteheaderlogo;
	}

	public void setWebsiteheaderlogo(List<String> websiteheaderlogo) {
		this.websiteheaderlogo = websiteheaderlogo;
	}

	public List<String> getApplogo() {
		return applogo;
	}

	public void setApplogo(List<String> applogo) {
		this.applogo = applogo;
	}

	public List<String> getWebsitefavicon() {
		return websitefavicon;
	}

	public void setWebsitefavicon(List<String> websitefavicon) {
		this.websitefavicon = websitefavicon;
	}

	public List<String> getLoadinggif() {
		return loadinggif;
	}

	public void setLoadinggif(List<String> loadinggif) {
		this.loadinggif = loadinggif;
	}


	public String getIpAddress() {
		return ipAddress;
	}


	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}


	public String getCreateby() {
		return createby;
	}


	public void setCreateby(String createby) {
		this.createby = createby;
	}


	public String getModifiedby() {
		return modifiedby;
	}


	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public boolean isIsdelete() {
		return isdelete;
	}


	public void setIsdelete(boolean isdelete) {
		this.isdelete = isdelete;
	}


	public LocalDateTime getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}


	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}


	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
	

}