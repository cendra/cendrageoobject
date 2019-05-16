package org.geo.model.country;

public class FIPS implements Cloneable, Comparable<FIPS> {

	private String fips;
	private String equivalentFipsCode;

	public String getFips() {
		return fips;
	}

	public void setFips(String fips) {
		fips = (fips != null) ? fips.trim() : null;
		this.fips = (fips != null && fips.length() == 0) ? null : fips;
	}

	public String getEquivalentFipsCode() {
		return equivalentFipsCode;
	}

	public void setEquivalentFipsCode(String equivalentFipsCode) {
		equivalentFipsCode = (equivalentFipsCode != null) ? equivalentFipsCode
				.trim() : null;
		this.equivalentFipsCode = (equivalentFipsCode != null && equivalentFipsCode
				.length() == 0) ? null : equivalentFipsCode;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (this.getClass() != obj.getClass()) {
			return false;
		}

		FIPS other = (FIPS) obj;

		if (this.getFips() != null && other.getFips() == null) {
			return false;
		}

		if (this.getFips() == null && other.getFips() != null) {
			return false;
		}

		return this.getFips().equals(other.getFips());
	}

	@Override
	public String toString() {

		if (this.getFips() != null) {
			return this.getFips();
		}

		return null;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {

		FIPS other = new FIPS();

		other.setFips(this.getFips());
		other.setEquivalentFipsCode(this.getEquivalentFipsCode());

		return other;
	}

	@Override
	public int compareTo(FIPS obj) {

		if (obj == null) {
			return -1;
		}

		FIPS other = (FIPS) obj;

		if (this.getFips() != null && other.getFips() == null) {
			return -1;
		}

		if (this.getFips() == null && other.getFips() != null) {
			return 1;
		}

		return this.getFips().compareTo(other.getFips());
	}

}
