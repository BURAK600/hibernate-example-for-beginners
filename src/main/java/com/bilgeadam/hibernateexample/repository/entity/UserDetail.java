package com.bilgeadam.hibernateexample.repository.entity;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)

	private long id;

	@Enumerated(EnumType.STRING)
	private EGenger gender;

	@Embedded
	private Name name;

	@Column(nullable = true)
	private int postNumber;

	public int getPostNumber() {
		return postNumber;
	}

	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
	}

	@ElementCollection
	Map<EAddressType, Address> address;

	@ElementCollection
	List<String> areasOfInterest;

	public UserDetail() {
		super();
	}

	public UserDetail(long id, EGenger gender, Name name, int postNumber, Map<EAddressType, Address> address,
			List<String> areasOfInterest) {
		super();
		this.id = id;
		this.gender = gender;
		this.name = name;
		this.postNumber = postNumber;
		this.address = address;
		this.areasOfInterest = areasOfInterest;
	}

	public UserDetail(EGenger gender, Name name, int postNumber, Map<EAddressType, Address> address,
			List<String> areasOfInterest) {
		super();
		this.gender = gender;
		this.name = name;
		this.postNumber = postNumber;
		this.address = address;
		this.areasOfInterest = areasOfInterest;
	}

	@Override
	public String toString() {
		return "UserDetail [id=" + id + ", gender=" + gender + ", name=" + name + ", postNumber=" + postNumber
				+ ", address=" + address + ", areasOfInterest=" + areasOfInterest + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EGenger getGender() {
		return gender;
	}

	public void setGender(EGenger gender) {
		this.gender = gender;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Map<EAddressType, Address> getAddress() {
		return address;
	}

	public void setAddress(Map<EAddressType, Address> address) {
		this.address = address;
	}

	public List<String> getAreasOfInterest() {
		return areasOfInterest;
	}

	public void setAreasOfInterest(List<String> areasOfInterest) {
		this.areasOfInterest = areasOfInterest;
	}

}
