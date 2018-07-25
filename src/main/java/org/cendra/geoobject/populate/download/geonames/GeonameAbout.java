package org.cendra.geoobject.populate.download.geonames;

import java.util.ArrayList;
import java.util.List;

import org.cendra.geoobject.populate.download.geonames.countries.alternatenames.NameAbout;
import org.cendra.geoobject.populate.model.Entity;

public class GeonameAbout extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6720342133333990961L;

	private String code;
	private List<NameAbout> names = new ArrayList<NameAbout>();
	private Double lat;
	private Double lng;
	private String dbpediaURL;

	public String getCode() {
		code = this.formatValue(code);
		return code;
	}

	public void setCode(String code) {
		code = this.formatValue(code);
		this.code = code;
	}

	@SuppressWarnings("unchecked")
	public List<NameAbout> getNames() {
		names = formatValues(names);
		return names;
	}

	@SuppressWarnings("unchecked")
	public void setNames(List<NameAbout> names) {
		names = formatValues(names);
		this.names = names;
	}

	@SuppressWarnings("unchecked")
	public void addName(NameAbout alternateName) {
		names = formatValues(names);
		this.names.add(alternateName);
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public String getDbpediaURL() {
		dbpediaURL = this.formatValue(dbpediaURL);
		return dbpediaURL;
	}

	public void setDbpediaURL(String dbpediaURL) {
		dbpediaURL = this.formatValue(dbpediaURL);
		this.dbpediaURL = dbpediaURL;
	}

	public String toString() {
		return code;
	}

}
