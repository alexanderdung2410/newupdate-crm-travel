package com.MotherSon.CRM.models;

import java.time.LocalDateTime;
//import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;


@Table(name="campany_master")
@Entity
public class Company {
     
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="companyid")
	private Long id;
	
	@Column(name="company_name")
	private String companyname;
	
	@Column(nullable=true)
	private Long parent_id;
	
	@Column(name="company_address")
	private String companyaddress;
	
	@Column(name="company_email")
	private String companyemail;
	
	@Column(name="company_country_code")
	private String companycountrycode;
	
	@Column(name="company_phone")
	private String companyphone;
	
	@Column(name="company_website")
	private String companywebsite;
	
	@Column(name="company_logo_path")
	private String companylogo;
	
	private String ipaddress;
	
	private boolean status;
	
	private boolean isdelete;
	
	

	@Column(name="created_by")
	private String createdby;
	
	@Column(name="modified_by")
	private String modifiedby;
	
	@Column(name="created_date")
	private LocalDateTime createddate;
	
	@Column(name="modified_date")
	private LocalDateTime modifieddate;
	
	@PrePersist
	protected void onCreate() {
		createddate = LocalDateTime.now();
		modifieddate = LocalDateTime.now();

	}

	
	public LocalDateTime getCreateddate() {
		return createddate;
	}


	public void setCreateddate(LocalDateTime createddate) {
		this.createddate = createddate;
	}


	public LocalDateTime getModifieddate() {
		return modifieddate;
	}


	public void setModifieddate(LocalDateTime modifieddate) {
		this.modifieddate = modifieddate;
	}


	@PreUpdate
	protected void onUpdate() {
		modifieddate = LocalDateTime.now();
	}


	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}

	public String getCompanylogo() {
		return companylogo;
	}

	public void setCompanylogo(String companylogo) {
		this.companylogo = companylogo;
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

	public String getCompanyaddress() {
		return companyaddress;
	}

	public void setCompanyaddress(String companyaddress) {
		this.companyaddress = companyaddress;
	}

	public String getCompanyemail() {
		return companyemail;
	}

	public void setCompanyemail(String companyemail) {
		this.companyemail = companyemail;
	}

	public String getCompanycountrycode() {
		return companycountrycode;
	}

	public void setCompanycountrycode(String companycountrycode) {
		this.companycountrycode = companycountrycode;
	}

	public String getCompanyphone() {
		return companyphone;
	}

	public void setCompanyphone(String companyphone) {
		this.companyphone = companyphone;
	}

	public String getCompanywebsite() {
		return companywebsite;
	}

	public void setCompanywebsite(String companywebsite) {
		this.companywebsite = companywebsite;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}

	public boolean isIsdelete() {
		return isdelete;
	}

	public void setIsdelete(boolean isdelete) {
		this.isdelete = isdelete;
	}

	
	
	
	
	
}
