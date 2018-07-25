package org.cendra.geoobject.populate.download.wikipedia.continents;

import org.cendra.common.model.Entity;

public class ContinentWikipedia extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1141843492085037436L;

	private String code;
	private String shortName;
	private String orthographicProjectionURL;

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

	public String getOrthographicProjectionURL() {
		orthographicProjectionURL = this.formatValue(orthographicProjectionURL);
		return orthographicProjectionURL;
	}

	public void setOrthographicProjectionURL(String orthographicProjectionURL) {
		orthographicProjectionURL = this.formatValue(orthographicProjectionURL);
		this.orthographicProjectionURL = orthographicProjectionURL;
	}

	@Override
	public String toString() {
		return shortName;
	}

}
