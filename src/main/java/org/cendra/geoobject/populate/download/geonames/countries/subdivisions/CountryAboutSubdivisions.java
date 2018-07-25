package org.cendra.geoobject.populate.download.geonames.countries.subdivisions;

import java.util.ArrayList;
import java.util.List;

import org.cendra.geoobject.populate.download.geonames.GeonameAbout;

public class CountryAboutSubdivisions extends GeonameAbout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2892185303255432051L;

	private List<GeonameAbout> subdivisions = new ArrayList<GeonameAbout>();

	@SuppressWarnings("unchecked")
	public List<GeonameAbout> getSubdivisions() {
		subdivisions = formatValues(subdivisions);
		return subdivisions;
	}

	@SuppressWarnings("unchecked")
	public void setSubdivisions(List<GeonameAbout> subdivisions) {
		subdivisions = formatValues(subdivisions);
		this.subdivisions = subdivisions;
	}

	@SuppressWarnings("unchecked")
	public void addSubdivision(GeonameAbout subdivision) {
		subdivisions = formatValues(subdivisions);
		this.subdivisions.add(subdivision);
	}

}
