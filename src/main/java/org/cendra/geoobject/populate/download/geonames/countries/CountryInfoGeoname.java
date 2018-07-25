package org.cendra.geoobject.populate.download.geonames.countries;

import java.util.ArrayList;
import java.util.List;

import org.cendra.common.model.Entity;

public class CountryInfoGeoname extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3029045538833821793L;

	private String iso; 
	private String iso3;
	private String isoNumeric;
	private String fips; 
	private String country;
	private String capital;
	private Double area;
	private Long population;
	private String continent;	
	private String tld;	
	private String currencyCode;
	private String currencyName;	
	private String phone;	
	private String postalCodeFormat;
	private String postalCodeRegex;		
	private List<String> languages = new ArrayList<String>();	
	private Long geonameId;	
	private List<String> neighbours = new ArrayList<String>();	
	private String equivalentFipsCode;

	public String getIso() {
		iso = this.formatValue(iso);
		return iso;
	}

	public void setIso(String iso) {
		iso = this.formatValue(iso);
		this.iso = iso;
	}

	public String getIso3() {
		iso3 = this.formatValue(iso3);
		return iso3;
	}

	public void setIso3(String iso3) {
		iso3 = this.formatValue(iso3);
		this.iso3 = iso3;
	}

	public String getIsoNumeric() {
		isoNumeric = this.formatValue(isoNumeric);
		return isoNumeric;
	}

	public void setIsoNumeric(String isoNumeric) {
		isoNumeric = this.formatValue(isoNumeric);
		this.isoNumeric = isoNumeric;
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

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public Long getPopulation() {
		return population;
	}

	public void setPopulation(Long population) {
		this.population = population;
	}

	public String getContinent() {
		continent = this.formatValue(continent);
		return continent;
	}

	public void setContinent(String continent) {
		continent = this.formatValue(continent);
		this.continent = continent;
	}

	public String getTld() {
		tld = this.formatValue(tld);
		return tld;
	}

	public void setTld(String tld) {
		tld = this.formatValue(tld);
		this.tld = tld;
	}

	public String getCurrencyCode() {
		currencyCode = this.formatValue(currencyCode);
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		currencyCode = this.formatValue(currencyCode);
		this.currencyCode = currencyCode;
	}

	public String getCurrencyName() {
		currencyName = this.formatValue(currencyName);
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		currencyName = this.formatValue(currencyName);
		this.currencyName = currencyName;
	}

	public String getPhone() {
		phone = this.formatValue(phone);
		return phone;
	}

	public void setPhone(String phone) {
		phone = this.formatValue(phone);
		this.phone = phone;
	}

	public String getPostalCodeFormat() {
		postalCodeFormat = this.formatValue(postalCodeFormat);
		return postalCodeFormat;
	}

	public void setPostalCodeFormat(String postalCodeFormat) {
		postalCodeFormat = this.formatValue(postalCodeFormat);
		this.postalCodeFormat = postalCodeFormat;
	}

	public String getPostalCodeRegex() {
		postalCodeRegex = this.formatValue(postalCodeRegex);
		return postalCodeRegex;
	}

	public void setPostalCodeRegex(String postalCodeRegex) {
		postalCodeRegex = this.formatValue(postalCodeRegex);
		this.postalCodeRegex = postalCodeRegex;
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

	public Long getGeonameId() {
		return geonameId;
	}

	public void setGeonameId(Long geonameId) {
		this.geonameId = geonameId;
	}

	@SuppressWarnings("unchecked")
	public List<String> getNeighbours() {
		neighbours = formatValues(neighbours);
		return neighbours;
	}

	@SuppressWarnings("unchecked")
	public void setNeighbours(List<String> neighbours) {
		neighbours = formatValues(neighbours);
		this.neighbours = neighbours;
	}

	@SuppressWarnings("unchecked")
	public void addNeighbour(String neighbour) {
		neighbours = formatValues(neighbours);
		neighbour = this.formatValue(neighbour);
		this.neighbours.add(neighbour);
	}

	public String getEquivalentFipsCode() {
		equivalentFipsCode = this.formatValue(equivalentFipsCode);
		return equivalentFipsCode;
	}

	public void setEquivalentFipsCode(String equivalentFipsCode) {
		equivalentFipsCode = this.formatValue(equivalentFipsCode);
		this.equivalentFipsCode = equivalentFipsCode;
	}

	public String toString() {
		return country;
	}

}
