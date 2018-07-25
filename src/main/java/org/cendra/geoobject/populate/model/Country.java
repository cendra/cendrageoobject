package org.cendra.geoobject.populate.model;

import java.util.ArrayList;
import java.util.List;

public class Country {

	private ContinentCode continent;
	private Integer geonameId;
	private ISO31661 iso31661 = new ISO31661();
	private FIPS fips = new FIPS();
//	private Boolean recognisedState;
	private String tld;
	private String phone;
	private String name;
	private String name2;
	private String capital;
	private Double area;
	private Long population;
	private PostalCode postalCode;
	private Currency currency;
	private List<CountryNeighbour> neighbours = new ArrayList<CountryNeighbour>();
	private List<String> languages = new ArrayList<String>();
	private CountryFiles files = new CountryFiles();
	private CountryExternalLinks externalLinks = new CountryExternalLinks();

	public ContinentCode getContinent() {
		return continent;
	}

	public void setContinent(ContinentCode continent) {
		this.continent = continent;
	}

	public Integer getGeonameId() {
		return geonameId;
	}

	public void setGeonameId(Integer geonameId) {
		this.geonameId = geonameId;
	}

	public ISO31661 getIso31661() {
		return iso31661;
	}

	public void setIso31661(ISO31661 iso31661) {
		this.iso31661 = iso31661;
	}

	public FIPS getFips() {
		return fips;
	}

	public void setFips(FIPS fips) {
		this.fips = fips;
	}

//	public Boolean getRecognisedState() {
//		return recognisedState;
//	}
//
//	public void setRecognisedState(Boolean recognisedState) {
//		this.recognisedState = recognisedState;
//	}

	public String getTld() {
		return tld;
	}

	public void setTld(String tld) {
		this.tld = tld;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
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

	public PostalCode getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(PostalCode postalCode) {
		this.postalCode = postalCode;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public List<CountryNeighbour> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(List<CountryNeighbour> neighbours) {
		this.neighbours = neighbours;
	}

	public void addNeighbour(CountryNeighbour country) {
		neighbours.add(country);
	}

	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public boolean addLanguage(String language) {
		return languages.add(language);
	}

	public CountryFiles getFiles() {
		return files;
	}

	public void setFiles(CountryFiles files) {
		this.files = files;
	}

	public CountryExternalLinks getExternalLinks() {
		return externalLinks;
	}

	public void setExternalLinks(CountryExternalLinks externalLinks) {
		this.externalLinks = externalLinks;
	}

	@Override
	public String toString() {
		return "Country [continent=" + continent + ", iso31661=" + iso31661
				+ "]";
	}

}
