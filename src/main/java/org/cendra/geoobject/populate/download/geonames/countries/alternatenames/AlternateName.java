package org.cendra.geoobject.populate.download.geonames.countries.alternatenames;

import org.cendra.common.model.Entity;

public class AlternateName extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7463337292590536756L;

	private String alternateName;
	private String languageCode;

	public String getAlternateName() {
		alternateName = this.formatValue(alternateName);
		return alternateName;
	}

	public void setAlternateName(String alternateName) {
		alternateName = this.formatValue(alternateName);
		this.alternateName = alternateName;
	}

	public String getLanguageCode() {
		languageCode = this.formatValue(languageCode);
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		languageCode = this.formatValue(languageCode);
		this.languageCode = languageCode;
	}

	@Override
	public String toString() {

		return alternateName + " (" + languageCode + ")";
	}

}
