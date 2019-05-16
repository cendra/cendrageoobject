package org.cendra.geoobject.populate.download.wikipedia.countries.subdivision;

import org.cendra.geoobject.populate.model.EntityOld;

public class SubdivisionsFormWikipedia extends EntityOld {



	/**
	 * 
	 */
	private static final long serialVersionUID = -2031240862183933203L;
	
	private String iso31662;
	private String country;
	private String flagURL;
	private String coatOfArmsURL;
	private String orthographicProjectionURL;

	public String getIso31662() {
		iso31662 = this.formatValue(iso31662);
		return iso31662;
	}

	public void setIso31662(String iso31662) {
		iso31662 = this.formatValue(iso31662);
		this.iso31662 = iso31662;
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

	public String toString() {
		return iso31662;
	}

}
