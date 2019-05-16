package org.geo.model.country;

public class CountryExternalLinks implements Cloneable {

	private String wikipediaURL;
	private String wikipediaURLB;
	private String wikipediaISO31662URL;
	// private String wikipediaLocationURL;
	private String wikipediaFlagURL;
	private String wikipediaCoatOfArmsURL;
	private String wikipediaOrthographicProjectionURL;

	private String geonamesEarthURL;
	private String geonamesURL;
	private String geonamesMapURL;
	private String geonamesFlagURL;

	public String getWikipediaURL() {
		return wikipediaURL;
	}

	public void setWikipediaURL(String wikipediaURL) {
		wikipediaURL = (wikipediaURL != null) ? wikipediaURL.trim() : null;
		this.wikipediaURL = (wikipediaURL != null && wikipediaURL.length() == 0) ? null
				: wikipediaURL;
	}

	public String getWikipediaURLB() {
		return wikipediaURLB;
	}

	public void setWikipediaURLB(String wikipediaURLB) {
		wikipediaURLB = (wikipediaURLB != null) ? wikipediaURLB.trim() : null;
		this.wikipediaURLB = (wikipediaURLB != null && wikipediaURLB.length() == 0) ? null
				: wikipediaURLB;
	}

	public String getWikipediaISO31662URL() {
		return wikipediaISO31662URL;
	}

	public void setWikipediaISO31662URL(String wikipediaISO31662URL) {
		wikipediaISO31662URL = (wikipediaISO31662URL != null) ? wikipediaISO31662URL
				.trim() : null;
		this.wikipediaISO31662URL = (wikipediaISO31662URL != null && wikipediaISO31662URL
				.length() == 0) ? null : wikipediaISO31662URL;
	}

	// public String getWikipediaLocationURL() {
	// return wikipediaLocationURL;
	// }

	// public void setWikipediaLocationURL(String wikipediaLocationURL) {
	// this.wikipediaLocationURL = wikipediaLocationURL;
	// }

	public String getWikipediaFlagURL() {
		return wikipediaFlagURL;
	}

	public void setWikipediaFlagURL(String wikipediaFlagURL) {
		wikipediaFlagURL = (wikipediaFlagURL != null) ? wikipediaFlagURL.trim()
				: null;
		this.wikipediaFlagURL = (wikipediaFlagURL != null && wikipediaFlagURL
				.length() == 0) ? null : wikipediaFlagURL;
	}

	public String getWikipediaCoatOfArmsURL() {
		return wikipediaCoatOfArmsURL;
	}

	public void setWikipediaCoatOfArmsURL(String wikipediaCoatOfArmsURL) {
		wikipediaCoatOfArmsURL = (wikipediaCoatOfArmsURL != null) ? wikipediaCoatOfArmsURL
				.trim() : null;
		this.wikipediaCoatOfArmsURL = (wikipediaCoatOfArmsURL != null && wikipediaCoatOfArmsURL
				.length() == 0) ? null : wikipediaCoatOfArmsURL;
	}

	public String getWikipediaOrthographicProjectionURL() {
		return wikipediaOrthographicProjectionURL;
	}

	public void setWikipediaOrthographicProjectionURL(
			String wikipediaOrthographicProjectionURL) {
		wikipediaOrthographicProjectionURL = (wikipediaOrthographicProjectionURL != null) ? wikipediaOrthographicProjectionURL
				.trim() : null;
		this.wikipediaOrthographicProjectionURL = (wikipediaOrthographicProjectionURL != null && wikipediaOrthographicProjectionURL
				.length() == 0) ? null : wikipediaOrthographicProjectionURL;
	}

	public String getGeonamesEarthURL() {
		return geonamesEarthURL;
	}

	public void setGeonamesEarthURL(String geonamesEarthURL) {
		geonamesEarthURL = (geonamesEarthURL != null) ? geonamesEarthURL.trim()
				: null;
		this.geonamesEarthURL = (geonamesEarthURL != null && geonamesEarthURL
				.length() == 0) ? null : geonamesEarthURL;
	}

	public String getGeonamesURL() {
		return geonamesURL;
	}

	public void setGeonamesURL(String geonamesURL) {
		geonamesURL = (geonamesURL != null) ? geonamesURL.trim() : null;
		this.geonamesURL = (geonamesURL != null && geonamesURL.length() == 0) ? null
				: geonamesURL;
	}

	public String getGeonamesMapURL() {
		return geonamesMapURL;
	}

	public void setGeonamesMapURL(String geonamesMapURL) {
		geonamesMapURL = (geonamesMapURL != null) ? geonamesMapURL.trim()
				: null;
		this.geonamesMapURL = (geonamesMapURL != null && geonamesMapURL
				.length() == 0) ? null : geonamesMapURL;
	}

	public String getGeonamesFlagURL() {
		return geonamesFlagURL;
	}

	public void setGeonamesFlagURL(String geonamesFlagURL) {
		geonamesFlagURL = (geonamesFlagURL != null) ? geonamesFlagURL.trim()
				: null;
		this.geonamesFlagURL = (geonamesFlagURL != null && geonamesFlagURL
				.length() == 0) ? null : geonamesFlagURL;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {

		CountryExternalLinks other = new CountryExternalLinks();

		other.setWikipediaURL(this.getWikipediaURL());
		other.setWikipediaURLB(this.getWikipediaURLB());
		other.setWikipediaISO31662URL(this.getWikipediaISO31662URL());
		other.setWikipediaFlagURL(this.getWikipediaFlagURL());
		other.setWikipediaCoatOfArmsURL(this.getWikipediaCoatOfArmsURL());
		other.setWikipediaOrthographicProjectionURL(this
				.getWikipediaOrthographicProjectionURL());
		other.setGeonamesEarthURL(this.getGeonamesEarthURL());
		other.setGeonamesURL(this.getGeonamesURL());
		other.setGeonamesMapURL(this.getGeonamesMapURL());
		other.setGeonamesFlagURL(this.getGeonamesFlagURL());

		return other;
	}

}
