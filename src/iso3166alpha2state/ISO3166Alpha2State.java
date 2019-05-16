package org.geo.model.country.iso3166alpha2state;

import org.geo.model.Entity;

public class ISO3166Alpha2State extends Entity implements Cloneable,
		Comparable<ISO3166Alpha2State> {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		name = (name != null) ? name.trim() : null;
		this.name = (name != null && name.length() == 0) ? null : name;
	}

	@Override
	public String toString() {

		if (this.getId() != null && this.getName() != null) {
			return "(" + this.getId() + ") " + this.getName();
		}

		if (this.getId() != null && this.getName() == null) {
			return "(" + this.getId() + ") ";
		}

		if (this.getId() == null && this.getName() != null) {
			return this.getName();
		}

		return null;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {

		ISO3166Alpha2State other = new ISO3166Alpha2State();

		other.setId(this.getId());		
		other.setName(this.getName());

		return other;
	}

	@Override
	public int compareTo(ISO3166Alpha2State obj) {

		if (obj == null) {
			return -1;
		}

		ISO3166Alpha2State other = (ISO3166Alpha2State) obj;

		if (this.getId() != null && other.getId() == null) {
			return -1;
		}

		if (this.getId() == null && other.getId() != null) {
			return 1;
		}

		return this.getId().compareTo(other.getId());
	}

}
