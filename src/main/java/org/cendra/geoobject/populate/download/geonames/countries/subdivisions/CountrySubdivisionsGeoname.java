package org.cendra.geoobject.populate.download.geonames.countries.subdivisions;

import java.util.ArrayList;
import java.util.List;

import org.cendra.common.model.Entity;

public class CountrySubdivisionsGeoname extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1329510076588812223L;

	private String iso3166Alpha2;
	private List<Subdivision> subdivisions = new ArrayList<Subdivision>();

	public String getIso3166Alpha2() {
		iso3166Alpha2 = this.formatValue(iso3166Alpha2);
		return iso3166Alpha2;
	}

	public void setIso3166Alpha2(String iso3166Alpha2) {
		iso3166Alpha2 = this.formatValue(iso3166Alpha2);
		this.iso3166Alpha2 = iso3166Alpha2;
	}

	@SuppressWarnings("unchecked")
	public List<Subdivision> getSubdivisions() {
		subdivisions = formatValues(subdivisions);
		return subdivisions;
	}

	@SuppressWarnings("unchecked")
	public void setSubdivisions(List<Subdivision> subdivisions) {
		subdivisions = formatValues(subdivisions);
		this.subdivisions = subdivisions;
	}

	@SuppressWarnings("unchecked")
	public void addSubdivisions(Subdivision subdivision) {
		subdivisions = formatValues(subdivisions);
		this.subdivisions.add(subdivision);
	}

	public String toString() {
		return iso3166Alpha2;
	}

}
