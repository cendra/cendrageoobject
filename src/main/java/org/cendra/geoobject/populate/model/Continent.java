package org.cendra.geoobject.populate.model;

import java.util.ArrayList;
import java.util.List;

public class Continent {

	private Integer geonameId;
	private String code;
	private String shortName;
	private String wikipediaURL;
	private String orthographicProjection;
	private String orthographicProjectionURL;
	private List<Country> countries = new ArrayList<Country>();

	public Integer getGeonameId() {
		return geonameId;
	}

	public void setGeonameId(Integer geonameId) {
		this.geonameId = geonameId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getWikipediaURL() {
		return wikipediaURL;
	}

	public void setWikipediaURL(String wikipediaURL) {
		this.wikipediaURL = wikipediaURL;
	}

	public String getOrthographicProjection() {
		return orthographicProjection;
	}

	public void setOrthographicProjection(String orthographicProjection) {
		this.orthographicProjection = orthographicProjection;
	}

	public String getOrthographicProjectionURL() {
		return orthographicProjectionURL;
	}

	public void setOrthographicProjectionURL(String orthographicProjectionURL) {
		this.orthographicProjectionURL = orthographicProjectionURL;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public boolean addCountry(Country country) {
		return countries.add(country);
	}

	@Override
	public String toString() {
		return "Continent [id=" + geonameId + ", code=" + code + ", shortName="
				+ shortName + "]";
	}

}
