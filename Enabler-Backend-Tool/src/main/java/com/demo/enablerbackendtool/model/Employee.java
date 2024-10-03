package com.demo.enablerbackendtool.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Sl_No")
	private int id;
	@Column(name="Department_Name")
    private String department_name;
	@Column(name="Department_ID")
    private String department_id;
	@Column(name="Team_Name")
    private String team_name;
	@Column(name="Team_ID")
    private String team_id;
	@Column(name="Project_Name")
    private String project_name;
	@Column(name="Project_ID")
    private String project_id;
	@Column(name="Employee_Name")
    private String employee_name;
    @Column(name="Employee_ID", unique = true) // Specify uniqueness here
    private String employee_id;
    @Column(name="Employee_Status")
    private int employee_status; // 0 for inactive, 1 for active
    @Column(name="Country")
    private String country;
    @Column(name="Region")
    private String region;
    @Column(name="Division")
    private String division;
    @Column(name="Entity_Name")
    private String entity_name;
    @Column(name="Entity_ID")
    private String entity_id;
    @Column(name="Category")
    private String category;
    @Column(name="Fiscal_Year_Start")
    private String fiscal_year_start;
    @Column(name="Fiscal_Year_End")
    private String fiscal_year_end;
    @Column(name="Employee_Position")
    private String employee_position;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}
	public String getTeam_name() {
		return team_name;
	}
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	public String getTeam_id() {
		return team_id;
	}
	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public int getEmployee_status() {
		return employee_status;
	}
	public void setEmployee_status(int employee_status) {
		this.employee_status = employee_status;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getEntity_name() {
		return entity_name;
	}
	public void setEntity_name(String entity_name) {
		this.entity_name = entity_name;
	}
	public String getEntity_id() {
		return entity_id;
	}
	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getFiscal_year_start() {
		return fiscal_year_start;
	}
	public void setFiscal_year_start(String fiscal_year_start) {
		this.fiscal_year_start = fiscal_year_start;
	}
	public String getFiscal_year_end() {
		return fiscal_year_end;
	}
	public void setFiscal_year_end(String fiscal_year_end) {
		this.fiscal_year_end = fiscal_year_end;
	}
	public String getEmployee_position() {
		return employee_position;
	}
	public void setEmployee_position(String employee_position) {
		this.employee_position = employee_position;
	}
}
