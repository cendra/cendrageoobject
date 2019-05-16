package org.cendra.geoobject.populate.download.geonames.countries.alternatenames;

import org.cendra.geoobject.populate.model.EntityOld;

public class NameAbout extends EntityOld {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3338680319476396240L;

	final public static String NAME = "name";
	final public static String ALTERNATE_NAME = "alternateName";
	final public static String OFFICIAL_NAME = "officialName";
	final public static String SHORT_NAME = "shortName";

	private String name;
	private String languageCode;
	private String type;

	public String getName() {
		name = this.formatValue(name);
		return name;
	}

	public void setName(String name) {
		name = this.formatValue(name);
		this.name = name;
	}

	public String getLanguageCode() {
		languageCode = this.formatValue(languageCode);
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		languageCode = this.formatValue(languageCode);
		this.languageCode = languageCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {

		return name + " (" + languageCode + ")";
	}

}
