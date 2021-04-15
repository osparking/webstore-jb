package com.jbpark.webstore.domain;

import java.io.Serializable;

public class Address implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4251096879977976119L;
	private Long id;
	private String unitNo; // 동호수(상세주소)
	private String zipCode; // 우편번호
	private String wideCiDo; // 광역시도
	private String ciGoonGu; // 시군구
	private String streetName; // 도로명
	private String buildingNo; // 건물번호

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Address other = (Address) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUnitNo() {
		return unitNo;
	}

	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getWideCiDo() {
		return wideCiDo;
	}

	public void setWideCiDo(String wideCiDo) {
		this.wideCiDo = wideCiDo;
	}

	public String getCiGoonGu() {
		return ciGoonGu;
	}

	public void setCiGoonGu(String ciGoonGu) {
		this.ciGoonGu = ciGoonGu;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}

}
