package org.cendra.geoobject.populate.download.wikipedia.countries;

import org.cendra.geoobject.populate.model.Entity;

public class CountryFormWikipedia extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2386341880736695971L;

	private String iso3166Alpha2;
	private String country;

	private String flagURL;
	private String flagPath;
	private String coatOfArmsURL;
	private String coatOfArmsPath;
	private String orthographicProjectionURL;
	private String orthographicProjectionPath;

	public String getIso3166Alpha2() {
		iso3166Alpha2 = this.formatValue(iso3166Alpha2);
		return iso3166Alpha2;
	}

	public void setIso3166Alpha2(String iso3166Alpha2) {
		iso3166Alpha2 = this.formatValue(iso3166Alpha2);
		this.iso3166Alpha2 = iso3166Alpha2;
	}

	public String getCountry() {
		country = this.formatValue(country);
		return country;
	}

	public void setCountry(String country) {
		country = this.formatValue(country);
		this.country = country;
	}

	public String getFlagURL() {
		flagURL = this.formatValue(flagURL);
		return flagURL;
	}

	public void setFlagURL(String flagURL) {
		flagURL = this.formatValue(flagURL);
		this.flagURL = flagURL;
	}

	public String getCoatOfArmsURL() {
		coatOfArmsURL = this.formatValue(coatOfArmsURL);
		return coatOfArmsURL;
	}

	public void setCoatOfArmsURL(String coatOfArmsURL) {
		coatOfArmsURL = this.formatValue(coatOfArmsURL);
		this.coatOfArmsURL = coatOfArmsURL;
	}

	public String getOrthographicProjectionURL() {
		orthographicProjectionURL = this.formatValue(orthographicProjectionURL);
		return orthographicProjectionURL;
	}

	public void setOrthographicProjectionURL(String orthographicProjectionURL) {
		orthographicProjectionURL = this.formatValue(orthographicProjectionURL);
		this.orthographicProjectionURL = orthographicProjectionURL;
	}

	public String getFlagPath() {
		return flagPath;
	}

	public void setFlagPath(String flagPath) {
		this.flagPath = flagPath;
	}

	public String getCoatOfArmsPath() {
		return coatOfArmsPath;
	}

	public void setCoatOfArmsPath(String coatOfArmsPath) {
		this.coatOfArmsPath = coatOfArmsPath;
	}

	public String getOrthographicProjectionPath() {
		return orthographicProjectionPath;
	}

	public void setOrthographicProjectionPath(String orthographicProjectionPath) {
		this.orthographicProjectionPath = orthographicProjectionPath;
	}

	public String toString() {
		return iso3166Alpha2;
	}

}
