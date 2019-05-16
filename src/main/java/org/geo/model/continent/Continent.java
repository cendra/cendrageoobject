package org.geo.model.continent;

import java.util.ArrayList;
import java.util.List;

import org.geo.model.GeographicalArea;
import org.geo.model.country.Country;

public class Continent extends GeographicalArea implements Cloneable, Comparable<Continent> {

	private String code;
	private String shortName;
	private String wikipediaURL;
	private String orthographicProjection;
	private String orthographicProjectionURL;
	private List<Country> countries = new ArrayList<Country>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		code = (code != null) ? code.trim() : null;
		this.code = (code != null && code.length() == 0) ? null : code;
	}

	public String getShortName() {		
		return shortName;
	}

	public void setShortName(String shortName) {
		shortName = (shortName != null) ? shortName.trim() : null;
		this.shortName = (shortName != null && shortName.length() == 0) ? null : shortName;		
	}

	public String getWikipediaURL() {		
		return wikipediaURL;
	}

	public void setWikipediaURL(String wikipediaURL) {
		wikipediaURL = (wikipediaURL != null) ? wikipediaURL.trim() : null;
		this.wikipediaURL = (wikipediaURL != null && wikipediaURL.length() == 0) ? null : wikipediaURL;		
	}

	public String getOrthographicProjection() {
		return orthographicProjection;
	}

	public void setOrthographicProjection(String orthographicProjection) {
		orthographicProjection = (orthographicProjection != null) ? orthographicProjection.trim() : null;
		this.orthographicProjection = (orthographicProjection != null && orthographicProjection.length() == 0) ? null : orthographicProjection;		
	}

	public String getOrthographicProjectionURL() {
		return orthographicProjectionURL;
	}

	public void setOrthographicProjectionURL(String orthographicProjectionURL) {				
		orthographicProjectionURL = (orthographicProjectionURL != null) ? orthographicProjectionURL.trim() : null;
		this.orthographicProjectionURL = (orthographicProjectionURL != null && orthographicProjectionURL.length() == 0) ? null : orthographicProjectionURL;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		
		List<Integer> listIndex = new ArrayList<Integer>();
		
		for(int i = 0; i < countries.size(); i++){
			
			Country country = countries.get(i);
			
			if(country == null){
				listIndex.add(i);
			}			
		}
		
		for(int removeIndex : listIndex){
			countries.remove(removeIndex);
		}
		
		this.countries = countries;
	}

	public boolean addCountry(Country country) {
		
		if(country == null){
			return false;
		}				
		
		if(countries == null){
			countries = new ArrayList<Country>();
		}
		
		return countries.add(country);
	}

	@Override
	public String toString() {

		if (this.getCode() != null && this.getShortName() != null) {
			return "(" + this.getCode() + ") " + this.getShortName();
		}

		if (this.getCode() != null && this.getShortName() == null) {
			return "(" + this.getCode() + ") ";
		}

		if (this.getCode() == null && this.getShortName() != null) {
			return this.getShortName();
		}

		return null;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		
		Continent other = new Continent();
		
		other.setId(this.getId());
		other.setCode(this.getCode());
		other.setShortName(this.getShortName());
		other.setWikipediaURL(this.getWikipediaURL());
		other.setOrthographicProjection(this.getOrthographicProjection());
		other.setOrthographicProjectionURL(this.getOrthographicProjectionURL());
		if(this.getCountries() != null){
			for(Country country : this.getCountries()){
				other.addCountry((Country) country.clone());
			}
		}
		
		return other;
	}
	
	@Override
	public int compareTo(Continent obj) {

		if (obj == null) {
			return -1;
		}

		Continent other = (Continent) obj;

		if (this.getCode() != null && other.getCode() == null) {
			return -1;
		}

		if (this.getCode() == null && other.getCode() != null) {
			return 1;
		}

		return this.getCode().compareTo(other.getCode());
	}

}
