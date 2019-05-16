package org.geo.model.country;

public class Currency implements Cloneable, Comparable<Currency> {

	private String code;
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		code = (code != null) ? code.trim() : null;
		this.code = (code != null && code.length() == 0) ? null : code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		name = (name != null) ? name.trim() : null;
		this.name = (name != null && name.length() == 0) ? null : name;
	}

	@Override
	public String toString() {

		if (this.getCode() != null && this.getName() != null) {
			return "(" + this.getCode() + ") " + this.getName();
		}

		if (this.getCode() != null && this.getName() == null) {
			return "(" + this.getCode() + ") ";
		}

		if (this.getCode() == null && this.getName() != null) {
			return this.getName();
		}

		return null;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {

		Currency other = new Currency();

		other.setCode(this.getCode());
		other.setName(this.getName());

		return other;
	}

	@Override
	public int compareTo(Currency obj) {

		if (obj == null) {
			return -1;
		}

		Currency other = (Currency) obj;

		if (this.getCode() != null && other.getCode() == null) {
			return -1;
		}

		if (this.getCode() == null && other.getCode() != null) {
			return 1;
		}

		return this.getCode().compareTo(other.getCode());
	}

}
