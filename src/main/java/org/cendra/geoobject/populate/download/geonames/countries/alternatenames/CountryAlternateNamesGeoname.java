package org.cendra.geoobject.populate.download.geonames.countries.alternatenames;

import java.util.ArrayList;
import java.util.List;

import org.cendra.geoobject.populate.model.EntityOld;

public class CountryAlternateNamesGeoname extends EntityOld {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8301236949492368952L;

	private String iso3166Alpha2;
	private List<AlternateName> alternateNames = new ArrayList<AlternateName>();

	public String getIso3166Alpha2() {
		iso3166Alpha2 = this.formatValue(iso3166Alpha2);
		return iso3166Alpha2;
	}

	public void setIso3166Alpha2(String iso3166Alpha2) {
		iso3166Alpha2 = this.formatValue(iso3166Alpha2);
		this.iso3166Alpha2 = iso3166Alpha2;
	}

	@SuppressWarnings("unchecked")
	public List<AlternateName> getAlternateNames() {
		alternateNames = formatValues(alternateNames);
		return alternateNames;
	}

	@SuppressWarnings("unchecked")
	public void setAlternateNames(List<AlternateName> alternateNames) {
		alternateNames = formatValues(alternateNames);
		this.alternateNames = alternateNames;
	}

	@SuppressWarnings("unchecked")
	public void addAlternateName(AlternateName alternateName) {
		alternateNames = formatValues(alternateNames);
		this.alternateNames.add(alternateName);
	}

	public String toString() {
		return iso3166Alpha2;
	}

}
