package org.cendra.geoobject.populate.download.geonames.continents;

import org.cendra.geoobject.populate.model.Entity;

public class ContinentGeoname extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1189173182353397151L;

	private String code;
	private String shortName;
	private String wikipediaURL;
	private Long geonameId;

	public String getCode() {
		code = this.formatValue(code);
		return code;
	}

	public void setCode(String code) {
		code = this.formatValue(code);
		this.code = code;
	}

	public String getShortName() {
		shortName = this.formatValue(shortName);
		return shortName;
	}

	public void setShortName(String shortName) {
		shortName = this.formatValue(shortName);
		this.shortName = shortName;
	}

	public String getWikipediaURL() {
		wikipediaURL = this.formatValue(wikipediaURL);
		return wikipediaURL;
	}

	public void setWikipediaURL(String wikipediaURL) {
		wikipediaURL = this.formatValue(wikipediaURL);
		this.wikipediaURL = wikipediaURL;
	}

	public Long getGeonameId() {
		return geonameId;
	}

	public void setGeonameId(Long geonameId) {
		this.geonameId = geonameId;
	}
	
	public String toString(){
		return shortName;
	}

}
