package org.cendra.geoobject.populate.download.geonames.languajes;

import org.cendra.geoobject.populate.model.EntityOld;

public class LanguageGeoname extends EntityOld {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8324806268259948116L;

	private Boolean macrolanguage = false;
	private String iso639_3;
	private String iso639_2;
	private String iso639_1;
	private String languageName;

	public Boolean getMacrolanguage() {
		macrolanguage = this.nullIsFalse(macrolanguage);
		return macrolanguage;
	}

	public void setMacrolanguage(Boolean macrolanguage) {
		macrolanguage = this.nullIsFalse(macrolanguage);
		this.macrolanguage = macrolanguage;
	}

	public String getIso639_3() {
		iso639_3 = this.formatValue(iso639_3);
		return iso639_3;
	}

	public void setIso639_3(String iso639_3) {
		iso639_3 = this.formatValue(iso639_3);
		this.iso639_3 = iso639_3;
	}

	public String getIso639_2() {
		iso639_2 = this.formatValue(iso639_2);
		return iso639_2;
	}

	public void setIso639_2(String iso639_2) {
		iso639_2 = this.formatValue(iso639_2);
		this.iso639_2 = iso639_2;
	}

	public String getIso639_1() {
		iso639_1 = this.formatValue(iso639_1);
		return iso639_1;
	}

	public void setIso639_1(String iso639_1) {
		iso639_1 = this.formatValue(iso639_1);
		this.iso639_1 = iso639_1;
	}

	public String getLanguageName() {
		languageName = this.formatValue(languageName);
		return languageName;
	}

	public void setLanguageName(String languageName) {
		languageName = this.formatValue(languageName);
		this.languageName = languageName;
	}

	@Override
	public String toString() {

		return this.getIso639_3();
	}

}
