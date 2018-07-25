package org.cendra.geoobject.populate.download.wikipedia.countries;

import org.cendra.geoobject.populate.model.Entity;

public class CountryWikipedia extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5112651541143899754L;

	private String iso3166Alpha2;
	private String iso3166Alpha3;
	private String iso3166Numeric;
	private Boolean independent;
	private String wikipediaURL;
	private String wikipediaISO31662URL;

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

	public Boolean getIndependent() {
		independent = this.nullIsFalse(independent);
		return independent;
	}

	public void setIndependent(Boolean independent) {
		independent = this.nullIsFalse(independent);
		this.independent = independent;
	}

	public String getWikipediaURL() {
		wikipediaURL = this.formatValue(wikipediaURL);
		return wikipediaURL;
	}

	public void setWikipediaURL(String wikipediaURL) {
		wikipediaURL = this.formatValue(wikipediaURL);
		this.wikipediaURL = wikipediaURL;
	}

	public String getWikipediaISO31662URL() {
		return wikipediaISO31662URL;
	}

	public void setWikipediaISO31662URL(String wikipediaISO31662URL) {
		this.wikipediaISO31662URL = wikipediaISO31662URL;
	}

	public String toString() {
		return iso3166Alpha3;
	}

}
