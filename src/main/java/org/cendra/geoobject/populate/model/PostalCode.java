package org.cendra.geoobject.populate.model;

public class PostalCode {

	private String format;
	private String regex;

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	@Override
	public String toString() {
		return "PostalCode [format=" + format + ", regex=" + regex + "]";
	}

}
