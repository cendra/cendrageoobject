package org.cendra.geoobject.populate.model;

public class ISO31661 {

	private String alpha2;
	private String alpha3;
	private String numeric;
	private ISO3166Alpha2State iso3166Alpha2State;
	private Boolean independent;

	public String getAlpha2() {
		return alpha2;
	}

	public void setAlpha2(String alpha2) {
		this.alpha2 = alpha2;
	}

	public String getAlpha3() {
		return alpha3;
	}

	public void setAlpha3(String alpha3) {
		this.alpha3 = alpha3;
	}

	public String getNumeric() {
		return numeric;
	}

	public void setNumeric(String numeric) {
		this.numeric = numeric;
	}

	public ISO3166Alpha2State getIso3166Alpha2State() {
		return iso3166Alpha2State;
	}

	public void setIso3166Alpha2State(ISO3166Alpha2State iso3166Alpha2State) {
		this.iso3166Alpha2State = iso3166Alpha2State;
	}

	public Boolean getIndependent() {
		return independent;
	}

	public void setIndependent(Boolean independent) {
		this.independent = independent;
	}

	@Override
	public String toString() {
		return "ISO31661 [alpha2=" + alpha2 + ", alpha3=" + alpha3
				+ ", numeric=" + numeric + ", iso3166Alpha2State="
				+ iso3166Alpha2State + ", independent=" + independent + "]";
	}

}
