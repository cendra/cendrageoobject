package org.cendra.geoobject.populate.download.geonames.countries.subdivisions;

import java.util.ArrayList;
import java.util.List;

import org.cendra.geoobject.populate.download.geonames.GeonameAbout;

public class ContinentAboutCountrySubdivisions extends GeonameAbout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4243368742677021019L;

	private List<CountryAboutSubdivisions> countries = new ArrayList<CountryAboutSubdivisions>();

	@SuppressWarnings("unchecked")
	public List<CountryAboutSubdivisions> getCountries() {
		countries = formatValues(countries);
		return countries;
	}

	@SuppressWarnings("unchecked")
	public void setCountries(List<CountryAboutSubdivisions> countries) {
		countries = formatValues(countries);
		this.countries = countries;
	}

	@SuppressWarnings("unchecked")
	public void addCountry(CountryAboutSubdivisions country) {
		countries = formatValues(countries);
		this.countries.add(country);
	}

}
