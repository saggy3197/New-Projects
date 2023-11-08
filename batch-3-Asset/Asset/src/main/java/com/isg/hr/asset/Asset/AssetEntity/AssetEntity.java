package com.isg.hr.asset.Asset.AssetEntity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="asset")
public class AssetEntity {
@Id
int id;
String name;
String type;
String createdby;
LocalDateTime  createddate;
String modifiedby;
LocalDateTime modifieddate;
String empcode;
public AssetEntity() {
}
public AssetEntity(int id, String name, String type, String createdby, LocalDateTime createddate, String modifiedby,
        LocalDateTime modifieddate, String empcode) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.createdby = createdby;
    this.createddate = createddate;
    this.modifiedby = modifiedby;
    this.modifieddate = modifieddate;
    this.empcode = empcode;
}
public void setId(int id) {
    this.id = id;
}
public void setName(String name) {
    this.name = name;
}
public void setType(String type) {
    this.type = type;
}
public void setCreatedby(String createdby) {
    this.createdby = createdby;
}
public void setCreateddate(LocalDateTime createddate) {
    this.createddate = createddate;
}
public void setModifiedby(String modifiedby) {
    this.modifiedby = modifiedby;
}
public void setModifieddate(LocalDateTime modifieddate) {
    this.modifieddate = modifieddate;
}
public void setEmpcode(String empcode) {
    this.empcode = empcode;
}
public int getId() {
    return id;
}
public String getName() {
    return name;
}
public String getType() {
    return type;
}
public String getCreatedby() {
    return createdby;
}
public LocalDateTime getCreateddate() {
    return createddate;
}
public String getModifiedby() {
    return modifiedby;
}
public LocalDateTime getModifieddate() {
    return modifieddate;
}
public String getEmpcode() {
    return empcode;
}
@Override
public String toString() {
    return "AssetEntity [id=" + id + ", name=" + name + ", type=" + type + ", createdby=" + createdby + ", createddate="
            + createddate + ", modifiedby=" + modifiedby + ", modifieddate=" + modifieddate + ", empcode=" + empcode
            + "]";
}




}

