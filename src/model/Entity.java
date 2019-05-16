package org.geo.model;

public abstract class Entity {

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if ((obj instanceof Entity) == false) {
			return false;
		}

		Entity other = (Entity) obj;

		if (this.getId() != null && other.getId() == null) {
			return false;
		}

		if (this.getId() == null && other.getId() != null) {
			return false;
		}

		return this.getId().equals(other.getId());
	}

	@Override
	public String toString() {

		if (this.getId() != null) {
			return this.getId().toString();
		}

		return null;
	}

}
