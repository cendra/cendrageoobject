package org.cendra.geoobject.populate.download.geonames.countries;

import java.util.ArrayList;
import java.util.List;

import org.cendra.geoobject.populate.model.Entity;

public class CountryFormGeoname extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6191762385518142271L;

	private String iso3166Alpha2;
	private String wikipediaURL;
	private String mapURL;
	private String mapPath;
	private String flagURL;
	private String flagPath;
	private String geonamesMapURL;
	private String otherCountryNamesURL;
	private String administrativeDivisionURL;
	private List<String> currencies = new ArrayList<String>();
	private List<String> languages = new ArrayList<String>();

	public String getIso3166Alpha2() {
		iso3166Alpha2 = this.formatValue(iso3166Alpha2);
		return iso3166Alpha2;
	}

	public void setIso3166Alpha2(String iso3166Alpha2) {
		iso3166Alpha2 = this.formatValue(iso3166Alpha2);
		this.iso3166Alpha2 = iso3166Alpha2;
	}

	public String getWikipediaURL() {
		wikipediaURL = this.formatValue(wikipediaURL);
		return wikipediaURL;
	}

	public void setWikipediaURL(String wikipediaURL) {
		wikipediaURL = this.formatValue(wikipediaURL);
		this.wikipediaURL = wikipediaURL;
	}

	public String getMapURL() {
		mapURL = this.formatValue(mapURL);
		return mapURL;
	}

	public void setMapURL(String mapURL) {
		mapURL = this.formatValue(mapURL);
		this.mapURL = mapURL;
	}

	public String getMapPath() {
		return mapPath;
	}

	public void setMapPath(String mapPath) {
		this.mapPath = mapPath;
	}

	public String getFlagURL() {
		flagURL = this.formatValue(flagURL);
		return flagURL;
	}

	public void setFlagURL(String flagURL) {
		flagURL = this.formatValue(flagURL);
		this.flagURL = flagURL;
	}

	public String getFlagPath() {
		return flagPath;
	}

	public void setFlagPath(String flagPath) {
		this.flagPath = flagPath;
	}

	public String getGeonamesMapURL() {
		geonamesMapURL = this.formatValue(geonamesMapURL);
		return geonamesMapURL;
	}

	public void setGeonamesMapURL(String geonamesMapURL) {
		geonamesMapURL = this.formatValue(geonamesMapURL);
		this.geonamesMapURL = geonamesMapURL;
	}

	public String getOtherCountryNamesURL() {
		otherCountryNamesURL = this.formatValue(otherCountryNamesURL);
		return otherCountryNamesURL;
	}

	public void setOtherCountryNamesURL(String otherCountryNamesURL) {
		otherCountryNamesURL = this.formatValue(otherCountryNamesURL);
		this.otherCountryNamesURL = otherCountryNamesURL;
	}

	public String getAdministrativeDivisionURL() {
		administrativeDivisionURL = this.formatValue(administrativeDivisionURL);
		return administrativeDivisionURL;
	}

	public void setAdministrativeDivisionURL(String administrativeDivisionURL) {
		administrativeDivisionURL = this.formatValue(administrativeDivisionURL);
		this.administrativeDivisionURL = administrativeDivisionURL;
	}

	@SuppressWarnings("unchecked")
	public List<String> getCurrencies() {
		currencies = formatValues(currencies);
		return currencies;
	}

	@SuppressWarnings("unchecked")
	public void setCurrencies(List<String> currencies) {
		currencies = formatValues(currencies);
		this.currencies = currencies;
	}

	@SuppressWarnings("unchecked")
	public void addCurrency(String currency) {
		currencies = formatValues(currencies);
		currency = this.formatValue(currency);
		this.currencies.add(currency);
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

	public String toString() {
		return iso3166Alpha2;
	}

}
