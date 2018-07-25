package org.cendra.geoobject.populate.download.geonames.countries.alternatenames;

import org.cendra.geoobject.populate.model.Entity;

public class AlternateNameFull extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5531656347264133188L;

	private String alternateNameId;
	private Long geonameId;
	private String languageCode;
	private String alternateName;
	private Boolean isPreferredName;
	private Boolean isShortName;
	private Boolean isColloquial;
	private Boolean isHistoric;

	public String getAlternateNameId() {
		alternateNameId = this.formatValue(alternateNameId);
		return alternateNameId;
	}

	public void setAlternateNameId(String alternateNameId) {
		alternateNameId = this.formatValue(alternateNameId);
		this.alternateNameId = alternateNameId;
	}

	public Long getGeonameId() {
		return geonameId;
	}

	public void setGeonameId(Long geonameId) {
		this.geonameId = geonameId;
	}

	public String getLanguageCode() {
		languageCode = this.formatValue(languageCode);
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		languageCode = this.formatValue(languageCode);
		this.languageCode = languageCode;
	}

	public String getAlternateName() {
		alternateName = this.formatValue(alternateName);
		return alternateName;
	}

	public void setAlternateName(String alternateName) {
		alternateName = this.formatValue(alternateName);
		this.alternateName = alternateName;
	}

	public Boolean getIsPreferredName() {
		isPreferredName = this.nullIsFalse(isPreferredName);
		return isPreferredName;
	}

	public void setIsPreferredName(Boolean isPreferredName) {
		isPreferredName = this.nullIsFalse(isPreferredName);
		this.isPreferredName = isPreferredName;
	}

	public Boolean getIsShortName() {
		isShortName = this.nullIsFalse(isShortName);
		return isShortName;
	}

	public void setIsShortName(Boolean isShortName) {
		isShortName = this.nullIsFalse(isShortName);
		this.isShortName = isShortName;
	}

	public Boolean getIsColloquial() {
		isColloquial = this.nullIsFalse(isColloquial);
		return isColloquial;
	}

	public void setIsColloquial(Boolean isColloquial) {
		isColloquial = this.nullIsFalse(isColloquial);
		this.isColloquial = isColloquial;
	}

	public Boolean getIsHistoric() {
		isHistoric = this.nullIsFalse(isHistoric);
		return isHistoric;
	}

	public void setIsHistoric(Boolean isHistoric) {
		isHistoric = this.nullIsFalse(isHistoric);
		this.isHistoric = isHistoric;
	}

	@Override
	public String toString() {

		return alternateName + " (" + languageCode + ")";
	}

}
