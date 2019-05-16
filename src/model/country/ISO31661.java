package org.geo.model.country;

import org.geo.model.country.iso3166alpha2state.ISO3166Alpha2State;


public class ISO31661 implements Cloneable, Comparable<ISO31661> {

	private String alpha2;
	private String alpha3;
	private String numeric;
	private ISO3166Alpha2State iso3166Alpha2State;
	private Boolean independent;

	public String getAlpha2() {
		return alpha2;
	}

	public void setAlpha2(String alpha2) {
		alpha2 = (alpha2 != null) ? alpha2.trim() : null;
		this.alpha2 = (alpha2 != null && alpha2.length() == 0) ? null : alpha2;
	}

	public String getAlpha3() {
		return alpha3;
	}

	public void setAlpha3(String alpha3) {
		alpha3 = (alpha3 != null) ? alpha3.trim() : null;
		this.alpha3 = (alpha3 != null && alpha3.length() == 0) ? null : alpha3;
	}

	public String getNumeric() {
		return numeric;
	}

	public void setNumeric(String numeric) {
		numeric = (numeric != null) ? numeric.trim() : null;
		this.numeric = (numeric != null && numeric.length() == 0) ? null : numeric;
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
		this.independent = (independent == null) ? false : independent;
	}
	
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (this.getClass() != obj.getClass()) {
			return false;
		}

		ISO31661 other = (ISO31661) obj;

		if (this.getAlpha2() != null && other.getAlpha2() == null) {
			return false;
		}

		if (this.getAlpha2() == null && other.getAlpha2() != null) {
			return false;
		}

		return this.getAlpha2().equals(other.getAlpha2());
	}

	@Override
	public String toString() {

		if (this.getAlpha2() != null) {
			return this.getAlpha2();
		}
		
		if (this.getAlpha3() != null) {
			return this.getAlpha3();
		}
		
		if (this.getNumeric() != null) {
			return this.getNumeric();
		}

		return null;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {

		ISO31661 other = new ISO31661();

		other.setAlpha2(this.getAlpha2());
		other.setAlpha3(this.getAlpha3());
		other.setNumeric(this.getNumeric());
		if(this.getIso3166Alpha2State() != null){
			other.setIso3166Alpha2State(this.getIso3166Alpha2State());
		}else {
			other.setIso3166Alpha2State(null);
		}
		other.setIndependent(this.getIndependent());

		return other;
	}
	
	@Override
	public int compareTo(ISO31661 obj) {
		
		if (obj == null) {
			return -1;
		}		

		ISO31661 other = (ISO31661) obj;

		if (this.getAlpha2() != null && other.getAlpha2() == null) {
			return -1;
		}

		if (this.getAlpha2() == null && other.getAlpha2() != null) {
			return 1;
		}		
		
		return this.getAlpha2().compareTo(other.getAlpha2());
	}

}
