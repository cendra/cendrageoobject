package org.cendra.geoobject.populate.download.geonames.countries.subdivisions;

import org.cendra.geoobject.populate.model.Entity;

public class Admin1 extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6552812744637450577L;

	private String iso3166Alpha2;
	private String iso3166Alpha2Fips;
	private String fips;
	private String name;
	private String nameB;
	private Long geonameId;

	public String getIso3166Alpha2() {
		iso3166Alpha2 = this.formatValue(iso3166Alpha2);
		return iso3166Alpha2;
	}

	public void setIso3166Alpha2(String iso3166Alpha2) {
		iso3166Alpha2 = this.formatValue(iso3166Alpha2);
		this.iso3166Alpha2 = iso3166Alpha2;
	}

	public String getIso3166Alpha2Fips() {
		iso3166Alpha2Fips = this.formatValue(iso3166Alpha2Fips);
		return iso3166Alpha2Fips;
	}

	public void setIso3166Alpha2Fips(String iso3166Alpha2Fips) {
		iso3166Alpha2Fips = this.formatValue(iso3166Alpha2Fips);
		this.iso3166Alpha2Fips = iso3166Alpha2Fips;
	}

	public String getFips() {
		fips = this.formatValue(fips);
		return fips;
	}

	public void setFips(String fips) {
		fips = this.formatValue(fips);
		this.fips = fips;
	}

	public String getName() {
		name = this.formatValue(name);
		return name;
	}

	public void setName(String name) {
		name = this.formatValue(name);
		this.name = name;
	}
	
	public String getNameB() {
		nameB = this.formatValue(nameB);
		return nameB;
	}

	public void setNameB(String nameB) {
		nameB = this.formatValue(nameB);
		this.nameB = nameB;
	}

	public Long getGeonameId() {
		return geonameId;
	}

	public void setGeonameId(Long geonameId) {
		this.geonameId = geonameId;
	}

	public String toString() {
		return iso3166Alpha2Fips;
	}

}
