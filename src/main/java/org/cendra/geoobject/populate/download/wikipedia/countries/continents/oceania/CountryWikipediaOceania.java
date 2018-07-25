package org.cendra.geoobject.populate.download.wikipedia.countries.continents.oceania;

import org.cendra.geoobject.populate.model.Entity;

public class CountryWikipediaOceania extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3865502692785474938L;

	private String iso3166Alpha3;
	private Boolean recognisedState;
	private String locationURL;
	private String locationPath;

	public String getIso3166Alpha3() {
		iso3166Alpha3 = this.formatValue(iso3166Alpha3);
		return iso3166Alpha3;
	}

	public void setIso3166Alpha3(String iso3166Alpha3) {
		iso3166Alpha3 = this.formatValue(iso3166Alpha3);
		this.iso3166Alpha3 = iso3166Alpha3;
	}

	public Boolean getRecognisedState() {
		recognisedState = this.nullIsFalse(recognisedState);
		return recognisedState;
	}

	public void setRecognisedState(Boolean recognisedState) {
		recognisedState = this.nullIsFalse(recognisedState);
		this.recognisedState = recognisedState;
	}

	public String getLocationURL() {
		locationURL = this.formatValue(locationURL);
		return locationURL;
	}

	public void setLocationURL(String locationURL) {
		locationURL = this.formatValue(locationURL);
		this.locationURL = locationURL;
	}
	
	public String getLocationPath() {
		return locationPath;
	}

	public void setLocationPath(String locationPath) {
		this.locationPath = locationPath;
	}


	public String toString() {
		return iso3166Alpha3;
	}

}
