package com.jbpark.webstore.domain;

import java.io.Serializable;

import com.jbpark.webstore.domain.Customer;

public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6826767593342468039L;
	private Long customerId;
	private String name;
	private String address;
	private int noOfOrdersMade;
	private Address billingAddress;
	private String phoneNumber;
	private boolean wrongId = false;

	public int getNoOfOrdersMade() {
		return noOfOrdersMade;
	}
	public void setNoOfOrdersMade(int noOfOrdersMade) {
		this.noOfOrdersMade = noOfOrdersMade;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setWrongId(boolean wrongId) {
		this.wrongId = wrongId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Boolean getWrongId() {
		return wrongId;
	}

	public void setWrongId(Boolean wrongId) {
		this.wrongId = wrongId;
	}

	public Customer() {
		super();
		this.billingAddress = new Address();
	}
	
	public Customer(Long customerId, String name) {
		this();
		this.customerId = customerId;
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Customer other = (Customer) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;		
		
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
