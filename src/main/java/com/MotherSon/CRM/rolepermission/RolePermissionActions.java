package com.MotherSon.CRM.rolepermission;

//import javax.persistence.*;

import com.MotherSon.CRM.models.RolePermission;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "role_permission_actions")
public class RolePermissionActions { // Changed to camel case
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    
    @Column(nullable = false)
    private String action;
    
    
    @ManyToOne
    @JoinColumn(name = "role_permission_id",referencedColumnName="ID")
    @JsonBackReference
    private RolePermission rolePermission; // Changed to camel case
 // Constructor that accepts a String argument
    
    public RolePermissionActions() {
     
    }
    
    public RolePermissionActions(String action) {
        this.action = action;
    }
	
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RolePermission getRolePermission() {
		return rolePermission;
	}

	public void setRolePermission(RolePermission rolePermission) {
		this.rolePermission = rolePermission;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
   

}
