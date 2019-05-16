package org.cendra.geoobject.populate.download.wikipedia.countries.subdivision;

import java.util.ArrayList;
import java.util.List;

import org.cendra.geoobject.populate.model.EntityOld;

public class CountrySubdivisionsWikipedia extends EntityOld {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2056999527411083425L;

	private String iso3166Alpha2;
	private List<SubdivisionsFormWikipedia> subdivisions = new ArrayList<SubdivisionsFormWikipedia>();

	public String getIso3166Alpha2() {
		iso3166Alpha2 = this.formatValue(iso3166Alpha2);
		return iso3166Alpha2;
	}

	public void setIso3166Alpha2(String iso3166Alpha2) {
		iso3166Alpha2 = this.formatValue(iso3166Alpha2);
		this.iso3166Alpha2 = iso3166Alpha2;
	}

	@SuppressWarnings("unchecked")
	public List<SubdivisionsFormWikipedia> getSubdivisions() {
		subdivisions = formatValues(subdivisions);
		return subdivisions;
	}

	@SuppressWarnings("unchecked")
	public void setSubdivisions(List<SubdivisionsFormWikipedia> subdivisions) {
		subdivisions = formatValues(subdivisions);
		this.subdivisions = subdivisions;
	}

	@SuppressWarnings("unchecked")
	public void addSubdivisions(SubdivisionsFormWikipedia subdivision) {
		subdivisions = formatValues(subdivisions);
		this.subdivisions.add(subdivision);
	}

	public String toString() {
		return iso3166Alpha2;
	}

}
