package org.cendra.geoobject.populate.model;

class CountryNeighbour {

	private ISO31661Neighbour iso31661 = new ISO31661Neighbour();

	public ISO31661Neighbour getIso31661() {
		return iso31661;
	}

	public void setIso31661(ISO31661Neighbour iso31661) {
		this.iso31661 = iso31661;
	}

	@Override
	public String toString() {
		return "CountryNeighbour [iso31661=" + iso31661 + "]";
	}

}
