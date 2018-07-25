package org.cendra.geoobject.populate.download.geonames.timezones;

import org.cendra.geoobject.populate.model.Entity;

public class TimeZoneGeoname extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8404454361045132221L;

	private String countryCode;
	private String timeZoneId;
	private String gmtOffset;
	private String dstOffset;
	private String rawOffsetIndependantOfDST;

	public String getCountryCode() {
		countryCode = this.formatValue(countryCode);
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		countryCode = this.formatValue(countryCode);
		this.countryCode = countryCode;
	}

	public String getTimeZoneId() {
		timeZoneId = this.formatValue(timeZoneId);
		return timeZoneId;
	}

	public void setTimeZoneId(String timeZoneId) {
		timeZoneId = this.formatValue(timeZoneId);
		this.timeZoneId = timeZoneId;
	}

	public String getGmtOffset() {
		gmtOffset = this.formatValue(gmtOffset);
		return gmtOffset;
	}

	public void setGmtOffset(String gmtOffset) {
		gmtOffset = this.formatValue(gmtOffset);
		this.gmtOffset = gmtOffset;
	}

	public String getDstOffset() {
		dstOffset = this.formatValue(dstOffset);
		return dstOffset;
	}

	public void setDstOffset(String dstOffset) {
		dstOffset = this.formatValue(dstOffset);
		this.dstOffset = dstOffset;
	}

	public String getRawOffsetIndependantOfDST() {
		rawOffsetIndependantOfDST = this.formatValue(rawOffsetIndependantOfDST);
		return rawOffsetIndependantOfDST;
	}

	public void setRawOffsetIndependantOfDST(String rawOffsetIndependantOfDST) {
		rawOffsetIndependantOfDST = this.formatValue(rawOffsetIndependantOfDST);
		this.rawOffsetIndependantOfDST = rawOffsetIndependantOfDST;
	}

	@Override
	public String toString() {

		return timeZoneId;
	}

}
