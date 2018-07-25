package org.cendra.geoobject.populate.model;

public class FIPS {

	private String fips;
	private String equivalentFipsCode;

	public String getFips() {
		return fips;
	}

	public void setFips(String fips) {
		this.fips = fips;
	}

	public String getEquivalentFipsCode() {
		return equivalentFipsCode;
	}

	public void setEquivalentFipsCode(String equivalentFipsCode) {
		this.equivalentFipsCode = equivalentFipsCode;
	}

	@Override
	public String toString() {
		return "FIPS [fips=" + fips + "]";
	}

}
