package org.cendra.geoobject.populate.download.geonames.countries.subdivisions;

import java.util.ArrayList;
import java.util.List;

import org.cendra.common.model.Entity;

public class Subdivision extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9037690016735001607L;

	private String iso31662;
	private String fips;
	private String gn;
	private String name;
	private String geonamesMapURL;
	private String wikipediaURL;
	private String type;
	private String capital;
	private String geonamesMapCapitalURL;
	private List<String> languages = new ArrayList<String>();
	private String continent;

	public String getIso31662() {
		iso31662 = this.formatValue(iso31662);
		return iso31662;
	}

	public void setIso31662(String iso31662) {
		iso31662 = this.formatValue(iso31662);
		this.iso31662 = iso31662;
	}

	public String getFips() {
		fips = this.formatValue(fips);
		return fips;
	}

	public void setFips(String fips) {
		fips = this.formatValue(fips);
		this.fips = fips;
	}

	public String getGn() {
		gn = this.formatValue(gn);
		return gn;
	}

	public void setGn(String gn) {
		gn = this.formatValue(gn);
		this.gn = gn;
	}

	public String getName() {
		name = this.formatValue(name);
		return name;
	}

	public void setName(String name) {
		name = this.formatValue(name);
		this.name = name;
	}

	public String getGeonamesMapURL() {
		geonamesMapURL = this.formatValue(geonamesMapURL);
		return geonamesMapURL;
	}

	public void setGeonamesMapURL(String geonamesMapURL) {
		geonamesMapURL = this.formatValue(geonamesMapURL);
		this.geonamesMapURL = geonamesMapURL;
	}

	public String getWikipediaURL() {
		wikipediaURL = this.formatValue(wikipediaURL);
		return wikipediaURL;
	}

	public void setWikipediaURL(String wikipediaURL) {
		wikipediaURL = this.formatValue(wikipediaURL);
		this.wikipediaURL = wikipediaURL;
	}

	public String getType() {
		type = this.formatValue(type);
		return type;
	}

	public void setType(String type) {
		type = this.formatValue(type);
		this.type = type;
	}

	public String getCapital() {
		capital = this.formatValue(capital);
		return capital;
	}

	public void setCapital(String capital) {
		capital = this.formatValue(capital);
		this.capital = capital;
	}

	public String getGeonamesMapCapitalURL() {
		geonamesMapCapitalURL = this.formatValue(geonamesMapCapitalURL);
		return geonamesMapCapitalURL;
	}

	public void setGeonamesMapCapitalURL(String geonamesMapCapitalURL) {
		geonamesMapCapitalURL = this.formatValue(geonamesMapCapitalURL);
		this.geonamesMapCapitalURL = geonamesMapCapitalURL;
	}

	@SuppressWarnings("unchecked")
	public List<String> getLanguages() {
		languages = formatValues(languages);
		return languages;
	}

	@SuppressWarnings("unchecked")
	public void setLanguages(List<String> languages) {
		languages = formatValues(languages);
		this.languages = languages;
	}

	@SuppressWarnings("unchecked")
	public void addLanguage(String language) {
		languages = formatValues(languages);
		language = this.formatValue(language);
		this.languages.add(language);
	}

	public String getContinent() {
		continent = this.formatValue(continent);
		return continent;
	}

	public void setContinent(String continent) {
		continent = this.formatValue(continent);
		this.continent = continent;
	}

	public String toString() {
		return iso31662;
	}

}
