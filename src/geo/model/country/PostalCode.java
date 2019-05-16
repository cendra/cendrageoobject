package org.geo.model.country;

public class PostalCode implements Cloneable, Comparable<PostalCode> {

	private String format;
	private String regex;

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		format = (format != null) ? format.trim() : null;
		this.format = (format != null && format.length() == 0) ? null : format;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		regex = (regex != null) ? regex.trim() : null;
		this.regex = (regex != null && regex.length() == 0) ? null : regex;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (this.getClass() != obj.getClass()) {
			return false;
		}

		PostalCode other = (PostalCode) obj;

		if (this.getFormat() != null && other.getFormat() == null) {
			return false;
		}

		if (this.getFormat() == null && other.getFormat() != null) {
			return false;
		}

		return this.getFormat().equals(other.getFormat());
	}

	@Override
	public String toString() {

		if (this.getFormat() != null) {
			return this.getFormat();
		}

		return null;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {

		PostalCode other = new PostalCode();

		other.setFormat(this.getFormat());
		other.setRegex(this.getRegex());

		return other;
	}

	@Override
	public int compareTo(PostalCode obj) {

		if (obj == null) {
			return -1;
		}

		PostalCode other = (PostalCode) obj;

		if (this.getFormat() != null && other.getFormat() == null) {
			return -1;
		}

		if (this.getFormat() == null && other.getFormat() != null) {
			return 1;
		}

		return this.getFormat().compareTo(other.getFormat());
	}

}
