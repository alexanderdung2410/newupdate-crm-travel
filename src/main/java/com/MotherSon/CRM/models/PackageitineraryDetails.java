package com.MotherSon.CRM.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "packageitinerary_Details")
@JsonIgnoreProperties(value = { "mealPackages", "activities" },allowSetters=true)
public class PackageitineraryDetails {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	
	@NotBlank(message = "IpAddress is required")
	@Column(name = "ipAddress" , nullable = false)
	private String ipaddress;
	
	//@Column(name = "status" , nullable = false)
	private boolean status;
	
	
	private String category;
	
	
	
	public String getSightseeingid() {
		return sightseeingid;
	}


	public void setSightseeingid(String sightseeingid) {
		this.sightseeingid = sightseeingid;
	}


	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="packitid")
	private PackageItinerary packitid;
	
	public PackageItinerary getPackitid() {
		return packitid;
	}


	public void setPackitid(PackageItinerary packitid) {
		this.packitid = packitid;
	}


	 @Column(name="sightseeingid")
	 private String sightseeingid;
	 
	 @Transient
	 private List<Long> sightseeingIds;
	 
	 
	 public void setSightseeingIds(List<Long> sightseeingIds) {
		    this.sightseeingIds = sightseeingIds;
		    this.sightseeingid = sightseeingIds.stream()
		            .map(String::valueOf)
		            .collect(Collectors.joining(","));
		}

		// Getter for sightseeingIds that converts the comma-separated String back to List<Long>
		public List<Long> getSightseeingIds() {
		    if (sightseeingid == null || sightseeingid.isEmpty()) {
		        return List.of();
		    }
		    return List.of(sightseeingid.split(","))
		            .stream()
		            .map(Long::valueOf)
		            .collect(Collectors.toList());
		}
	
  

	@Column(name = "isdelete", nullable = false)
	private boolean isdelete;
	
	@NotBlank(message = "Created By is required")
	@Column(name = "created_by" , nullable = false)
	private String createdby;
	
	@Column(name = "modified_by" , nullable = false)
	private String modifiedby;
	
	@OneToOne
	@JoinColumn(name = "roomtypesid")
	private RoomTypes roomtypes;
	
	
	
	@ManyToMany
    @JoinTable(
      name = "package_itinerarydetails_activitie", // Join table name
      joinColumns = @JoinColumn(name = "package_itinerarydetails_id"), // Foreign key for this entity
      inverseJoinColumns = @JoinColumn(name = "activities_id") // Foreign key for the related entity
    )
    private Set<Activities> activities = new HashSet<>();
	

	
	
	@ManyToMany
    @JoinTable(
      name = "package_itinerarydetails_meal", // Join table name
      joinColumns = @JoinColumn(name = "package_itinerarydetails_id"), // Foreign key for this entity
      inverseJoinColumns = @JoinColumn(name = "mealspackage_id") // Foreign key for the related entity
    )
    private Set<MealsPackage> mealPackages = new HashSet<>();
	
	
	//@Column(name = "created_Date" , nullable = false)
	private LocalDateTime createdDate;
	
	//@Column(name = "modified_Date", nullable = false)
	private LocalDateTime modifiedDate;
	
	
	@PrePersist
	protected void onCreated() {
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


	public boolean isIsdelete() {
		return isdelete;
	}


	public void setIsdelete(boolean isdelete) {
		this.isdelete = isdelete;
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


	public RoomTypes getRoomtypes() {
		return roomtypes;
	}


	public void setRoomtypes(RoomTypes roomtypes) {
		this.roomtypes = roomtypes;
	}


	public Set<MealsPackage> getMealPackages() {
		return mealPackages;
	}


	public void setMealPackages(Set<MealsPackage> mealPackages) {
		this.mealPackages = mealPackages;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public Set<Activities> getActivities() {
		return activities;
	}


	public void setActivities(Set<Activities> activities) {
		this.activities = activities;
	}


	

}