package org.geo.model.country;

import java.util.ArrayList;
import java.util.List;

import org.geo.model.GeographicalArea;
import org.geo.model.continent.Continent;

public class Country extends GeographicalArea implements Cloneable, Comparable<Country> {

	private Continent continent;
	private ISO31661 iso31661 = new ISO31661();
	private FIPS fips = new FIPS();
	// private Boolean recognisedState;
	private String tld;
	private String phone;
	private String name;
	private String name2;
	private String capital;
	private Double area;
	private Long population;
	private PostalCode postalCode;
	private Currency currency;
	private List<Country> neighbours = new ArrayList<Country>();
	private List<String> languages = new ArrayList<String>();
	private CountryFiles files = new CountryFiles();
	private CountryExternalLinks externalLinks = new CountryExternalLinks();

	public Continent getContinent() {
		return continent;
	}

	public void setContinent(Continent continent) {
		this.continent = continent;
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

	// public Boolean getRecognisedState() {
	// return recognisedState;
	// }
	//
	// public void setRecognisedState(Boolean recognisedState) {
	// this.recognisedState = recognisedState;
	// }

	public String getTld() {
		return tld;
	}

	public void setTld(String tld) {
		tld = (tld != null) ? tld.trim() : null;
		this.tld = (tld != null && tld.length() == 0) ? null : tld;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		phone = (phone != null) ? phone.trim() : null;
		this.phone = (phone != null && phone.length() == 0) ? null : phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		name = (name != null) ? name.trim() : null;
		this.name = (name != null && name.length() == 0) ? null : name;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		name2 = (name2 != null) ? name2.trim() : null;
		this.name2 = (name2 != null && name2.length() == 0) ? null : name2;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		capital = (capital != null) ? capital.trim() : null;
		this.capital = (capital != null && capital.length() == 0) ? null
				: capital;
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

	public List<Country> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(List<Country> neighbours) {

		List<Integer> listIndex = new ArrayList<Integer>();

		for (int i = 0; i < neighbours.size(); i++) {

			Country country = neighbours.get(i);

			if (country == null) {
				listIndex.add(i);
			}
		}

		for (int removeIndex : listIndex) {
			neighbours.remove(removeIndex);
		}

		this.neighbours = neighbours;
	}

	public boolean addNeighbour(Country country) {

		if (country == null) {
			return false;
		}

		if (neighbours == null) {
			neighbours = new ArrayList<Country>();
		}

		return neighbours.add(country);
	}

	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {

		List<Integer> listIndex = new ArrayList<Integer>();

		for (int i = 0; i < languages.size(); i++) {

			String language = languages.get(i);

			language = (language != null) ? language.trim() : null;
			language = (language != null && language.length() == 0) ? null
					: language;

			if (language == null) {
				listIndex.add(i);
			}
		}

		for (int removeIndex : listIndex) {
			languages.remove(removeIndex);
		}

		this.languages = languages;
	}

	public boolean addLanguage(String language) {

		language = (language != null) ? language.trim() : null;
		language = (language != null && language.length() == 0) ? null
				: language;

		if (language == null) {
			return false;
		}

		if (languages == null) {
			languages = new ArrayList<String>();
		}

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

		if (this.getIso31661() != null && this.getName() != null) {
			return "(" + this.getIso31661() + ") " + this.getName();
		}

		if (this.getIso31661() != null && this.getName() == null) {
			return "(" + this.getIso31661() + ") ";
		}

		if (this.getIso31661() == null && this.getName() != null) {
			return this.getName();
		}

		return null;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {

		Country other = new Country();

		other.setId(this.getId());
		if (this.getContinent() != null) {
			other.setContinent((Continent) this.getContinent().clone());
		} else {
			other.setContinent(null);
		}
		if (this.getIso31661() != null) {
			other.setIso31661((ISO31661) this.getIso31661().clone());
		} else {
			other.setIso31661(null);
		}
		if (this.getFips() != null) {
			other.setFips((FIPS) this.getFips().clone());
		} else {
			other.setFips(null);
		}
		other.setTld(this.getTld());
		other.setPhone(this.getPhone());
		other.setName(this.getName());
		other.setName2(this.getName2());
		other.setCapital(this.getCapital());
		other.setArea(this.getArea());
		other.setPopulation(this.getPopulation());
		if (this.getPostalCode() != null) {
			other.setPostalCode((PostalCode) this.getPostalCode().clone());
		} else {
			other.setPostalCode(null);
		}
		if (this.getCurrency() != null) {
			other.setCurrency((Currency) this.getCurrency().clone());
		} else {
			other.setCurrency(null);
		}
		if (this.getNeighbours() != null) {
			for (Country country : this.getNeighbours()) {
				other.addNeighbour((Country) country.clone());
			}
		}
		if (this.getNeighbours() != null) {
			for (String language : this.getLanguages()) {
				other.addLanguage(language);
			}
		}
		if (this.getFiles() != null) {
			other.setFiles((CountryFiles) this.getFiles().clone());
		} else {
			other.setFiles(null);
		}
		if (this.getExternalLinks() != null) {
			other.setExternalLinks((CountryExternalLinks) this
					.getExternalLinks().clone());
		} else {
			other.setExternalLinks(null);
		}

		return other;
	}

	@Override
	public int compareTo(Country obj) {

		if (obj == null) {
			return -1;
		}

		Country other = (Country) obj;

		String alpha2 = null;
		String alpha2Other = null;

		if (this.getIso31661() != null
				&& this.getIso31661().getAlpha2() != null) {
			alpha2 = this.getIso31661().getAlpha2();
		}

		if (other.getIso31661() != null
				&& other.getIso31661().getAlpha2() != null) {
			alpha2Other = this.getIso31661().getAlpha2();
		}

		if (alpha2 != null && alpha2Other == null) {
			return -1;
		}

		if (alpha2 == null && alpha2Other != null) {
			return 1;
		}

		return alpha2.compareTo(alpha2Other);
	}

}
