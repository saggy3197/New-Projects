package com.isg.hr.newEmployee.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity

@Table(name="employee")
public class Employee {
    @Id
    String empcode;

    int postinid;

    int onsiteothercountery;

    int onsiteothercounterydesc;

    Date probetionstartdate;
    
    String probation;

    String  status;

    String role;

    int reportingto;

    int designationid;

    int division;

    int bandid;

    String createdby;

    public String getEmpcode() {
        return empcode;
    }

    public int getPostinid() {
        return postinid;
    }

    public int getOnsiteothercountery() {
        return onsiteothercountery;
    }

    public int getOnsiteothercounterydesc() {
        return onsiteothercounterydesc;
    }

    public Date getProbetionstartdate() {
        return probetionstartdate;
    }

    public String getProbation() {
        return probation;
    }

    public String getStatus() {
        return status;
    }

    public String getRole() {
        return role;
    }

    public int getReportingto() {
        return reportingto;
    }

    public int getDesignationid() {
        return designationid;
    }

    public int getDivision() {
        return division;
    }

    public int getBandid() {
        return bandid;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setEmpcode(String empcode) {
        this.empcode = empcode;
    }

    public void setPostinid(int postinid) {
        this.postinid = postinid;
    }

    public void setOnsiteothercountery(int onsiteothercountery) {
        this.onsiteothercountery = onsiteothercountery;
    }

    public void setOnsiteothercounterydesc(int onsiteothercounterydesc) {
        this.onsiteothercounterydesc = onsiteothercounterydesc;
    }

    public void setProbetionstartdate(Date probetionstartdate) {
        this.probetionstartdate = probetionstartdate;
    }

    public void setProbation(String probation) {
        this.probation = probation;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setReportingto(int reportingto) {
        this.reportingto = reportingto;
    }

    public void setDesignationid(int designationid) {
        this.designationid = designationid;
    }

    public void setDivision(int division) {
        this.division = division;
    }

    public void setBandid(int bandid) {
        this.bandid = bandid;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Employee(String empcode, int postinid, int onsiteothercountery, int onsiteothercounterydesc,
            Date probetionstartdate, String probation, String status, String role, int reportingto, int designationid,
            int division, int bandid, String createdby) {
        this.empcode = empcode;
        this.postinid = postinid;
        this.onsiteothercountery = onsiteothercountery;
        this.onsiteothercounterydesc = onsiteothercounterydesc;
        this.probetionstartdate = probetionstartdate;
        this.probation = probation;
        this.status = status;
        this.role = role;
        this.reportingto = reportingto;
        this.designationid = designationid;
        this.division = division;
        this.bandid = bandid;
        this.createdby = createdby;
    }

    public Employee() {
    }

    
    

}
