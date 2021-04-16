package com.jbpark.webstore.domain;

import java.io.Serializable;

import com.jbpark.webstore.domain.Customer;

public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6826767593342468039L;
	private boolean wrongId = false;
	private Long customerIdLong;
	private String name;
	private Address billingAddress;
	private String phoneNumber;
	private int noOfOrdersMade;

	public int getNoOfOrdersMade() {
		return noOfOrdersMade;
	}
	public void setNoOfOrdersMade(int noOfOrdersMade) {
		this.noOfOrdersMade = noOfOrdersMade;
	}
	public Long getCustomerIdLong() {
		return customerIdLong;
	}
	public void setWrongId(boolean wrongId) {
		this.wrongId = wrongId;
	}
	public void setCustomerIdLong(Long customerIdLong) {
		this.customerIdLong = customerIdLong;
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
		this.customerIdLong = customerId;
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerIdLong == null) ? 0 : customerIdLong.hashCode());
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
		if (customerIdLong == null) {
			if (other.customerIdLong != null)
				return false;
		} else if (!customerIdLong.equals(other.customerIdLong))
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

}
