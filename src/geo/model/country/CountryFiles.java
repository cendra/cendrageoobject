package org.geo.model.country;

public class CountryFiles implements Cloneable {

	private String wikipediaFlag;
	private String wikipediaCoatOfArms;
	private String wikipediaOrthographicProjection;

	private String geonamesMap;
	private String geonamesFlag;

	public String getWikipediaFlag() {
		return wikipediaFlag;
	}

	public void setWikipediaFlag(String wikipediaFlag) {
		wikipediaFlag = (wikipediaFlag != null) ? wikipediaFlag.trim() : null;
		this.wikipediaFlag = (wikipediaFlag != null && wikipediaFlag.length() == 0) ? null
				: wikipediaFlag;
	}

	public String getWikipediaCoatOfArms() {
		return wikipediaCoatOfArms;
	}

	public void setWikipediaCoatOfArms(String wikipediaCoatOfArms) {
		wikipediaCoatOfArms = (wikipediaCoatOfArms != null) ? wikipediaCoatOfArms
				.trim() : null;
		this.wikipediaCoatOfArms = (wikipediaCoatOfArms != null && wikipediaCoatOfArms
				.length() == 0) ? null : wikipediaCoatOfArms;
	}

	public String getWikipediaOrthographicProjection() {
		return wikipediaOrthographicProjection;
	}

	public void setWikipediaOrthographicProjection(
			String wikipediaOrthographicProjection) {
		wikipediaOrthographicProjection = (wikipediaOrthographicProjection != null) ? wikipediaOrthographicProjection
				.trim() : null;
		this.wikipediaOrthographicProjection = (wikipediaOrthographicProjection != null && wikipediaOrthographicProjection
				.length() == 0) ? null : wikipediaOrthographicProjection;
	}

	public String getGeonamesMap() {
		return geonamesMap;
	}

	public void setGeonamesMap(String geonamesMap) {
		geonamesMap = (geonamesMap != null) ? geonamesMap.trim() : null;
		this.geonamesMap = (geonamesMap != null && geonamesMap.length() == 0) ? null
				: geonamesMap;
	}

	public String getGeonamesFlag() {
		return geonamesFlag;
	}

	public void setGeonamesFlag(String geonamesFlag) {
		geonamesFlag = (geonamesFlag != null) ? geonamesFlag.trim() : null;
		this.geonamesFlag = (geonamesFlag != null && geonamesFlag.length() == 0) ? null
				: geonamesFlag;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {

		CountryFiles other = new CountryFiles();

		other.setWikipediaFlag(this.getWikipediaFlag());
		other.setWikipediaCoatOfArms(this.getWikipediaCoatOfArms());
		other.setWikipediaOrthographicProjection(this
				.getWikipediaOrthographicProjection());
		other.setGeonamesMap(this.getGeonamesMap());
		other.setGeonamesFlag(this.getGeonamesFlag());

		return other;
	}

}
