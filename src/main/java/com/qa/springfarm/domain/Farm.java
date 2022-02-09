package com.qa.springfarm.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table (name = "Farm")
public class Farm {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long Id;
	
	@NotNull (message = "Please enter a name")
	@Column
	private String farm_name;
	
	@NotNull (message = "Enter Farm address")
	@Column
	private String address;
	
	@NotNull (message = "Enter email address")
	@Column
	private String email;
	
	@NotNull (message = "Enter landline or mobile number")
	@Column
	private Long telephone;

public Farm() {
	}

public Farm(String farm_name, String address, String email, Long telephone) {
	this.farm_name = farm_name;
	this.address = address;
	this.email = email;
	this.telephone = telephone;
	}

public Farm(long id, String farm_name, String address, String email, Long telephone) {
	this.Id = id;
	this.farm_name = farm_name;
	this.address = address;
	this.email = email;
	this.telephone = telephone;
	}

public long getId() {
	return Id;
}

public void setId(long id) {
	Id = id;
}

public String getFarm_name() {
	return farm_name;
}

public void setFarm_name(String farm_name) {
	this.farm_name = farm_name;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public Long getTelephone() {
	return telephone;
}

public void setTelephone(Long telephone) {
	this.telephone = telephone;
}

@Override
public int hashCode() {
	return Objects.hash(Id, address, email, farm_name, telephone);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Farm other = (Farm) obj;
	return Id == other.Id && Objects.equals(address, other.address) && Objects.equals(email, other.email)
			&& Objects.equals(farm_name, other.farm_name) && Objects.equals(telephone, other.telephone);
}

@Override
public String toString() {
	return "Farm [Id=" + Id + ", farm_name=" + farm_name + ", address=" + address + ", email=" + email + ", telephone="
			+ telephone + "]";
	}

}