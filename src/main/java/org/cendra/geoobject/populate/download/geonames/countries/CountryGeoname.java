package org.cendra.geoobject.populate.download.geonames.countries;

import org.cendra.geoobject.populate.model.EntityOld;

public class CountryGeoname extends EntityOld {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7392809103212485581L;

	private String iso3166Alpha2;
	private String iso3166Alpha3;
	private String iso3166Numeric;
	private String fips;
	private String country;
	private String capital;
	private String continent;
	private String geonamesURL;

	public String getIso3166Alpha2() {
		iso3166Alpha2 = this.formatValue(iso3166Alpha2);
		return iso3166Alpha2;
	}

	public void setIso3166Alpha2(String iso3166Alpha2) {
		iso3166Alpha2 = this.formatValue(iso3166Alpha2);
		this.iso3166Alpha2 = iso3166Alpha2;
	}

	public String getIso3166Alpha3() {
		iso3166Alpha3 = this.formatValue(iso3166Alpha3);
		return iso3166Alpha3;
	}

	public void setIso3166Alpha3(String iso3166Alpha3) {
		iso3166Alpha3 = this.formatValue(iso3166Alpha3);
		this.iso3166Alpha3 = iso3166Alpha3;
	}

	public String getIso3166Numeric() {
		iso3166Numeric = this.formatValue(iso3166Numeric);
		return iso3166Numeric;
	}

	public void setIso3166Numeric(String iso3166Numeric) {
		iso3166Numeric = this.formatValue(iso3166Numeric);
		this.iso3166Numeric = iso3166Numeric;
	}

	public String getFips() {
		fips = this.formatValue(fips);
		return fips;
	}

	public void setFips(String fips) {
		fips = this.formatValue(fips);
		this.fips = fips;
	}

	public String getCountry() {
		country = this.formatValue(country);
		return country;
	}

	public void setCountry(String country) {
		country = this.formatValue(country);
		this.country = country;
	}

	public String getCapital() {
		capital = this.formatValue(capital);
		return capital;
	}

	public void setCapital(String capital) {
		capital = this.formatValue(capital);
		this.capital = capital;
	}

	public String getContinent() {
		continent = this.formatValue(continent);
		return continent;
	}

	public void setContinent(String continent) {
		continent = this.formatValue(continent);
		this.continent = continent;
	}

	public String getGeonamesURL() {
		geonamesURL = this.formatValue(geonamesURL);
		return geonamesURL;
	}

	public void setGeonamesURL(String geonamesURL) {
		geonamesURL = this.formatValue(geonamesURL);
		this.geonamesURL = geonamesURL;
	}

	public String toString() {
		return country;
	}

}
