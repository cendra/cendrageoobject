package org.cendra.geoobject.populate.download.wikipedia.countries;

import org.cendra.common.model.Entity;

public class CountryWikipediaISO2 extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8214967614516040895L;

	/* assigned to a country, territory, or area of geographical interest */
	public static final String OFfICIALLY = "Officially assigned";

	/* free for assignment at the disposal of users */
	public static final String USER_ASSIGNED = "User-assigned";

	/* reserved on request for restricted use */
	public static final String EXCEPTIONALLY = "Exceptionally reserved";

	/* used in coding systems associated with ISO 3166-1 */
	public static final String INDETERMINATELY = "Indeterminately reserved";

	/* deleted from ISO 3166-1 but reserved transitionally */
	public static final String TRANSITIONALLY = "Transitionally reserved";

	/* deleted and free for reassignment */
	public static final String DELETED = "Deleted";

	/* free for assignment by the ISO 3166/MA only */
	public static final String UNASSIGNED = "Unassigned";

	private String iso3166Alpha2;
	private String iso3166Alpha2State;

	public String getIso3166Alpha2() {
		iso3166Alpha2 = this.formatValue(iso3166Alpha2);
		return iso3166Alpha2;
	}

	public void setIso3166Alpha2(String iso3166Alpha2) {
		iso3166Alpha2 = this.formatValue(iso3166Alpha2);
		this.iso3166Alpha2 = iso3166Alpha2;
	}

	public String getIso3166Alpha2State() {
		iso3166Alpha2State = this.formatValue(iso3166Alpha2State);
		return iso3166Alpha2State;
	}

	public void setIso3166Alpha2State(String iso3166Alpha2State) {
		iso3166Alpha2State = this.formatValue(iso3166Alpha2State);

		if (iso3166Alpha2State.equals(OFfICIALLY) == false
				&& iso3166Alpha2State.equals(USER_ASSIGNED) == false
				&& iso3166Alpha2State.equals(EXCEPTIONALLY) == false
				&& iso3166Alpha2State.equals(INDETERMINATELY) == false
				&& iso3166Alpha2State.equals(TRANSITIONALLY) == false
				&& iso3166Alpha2State.equals(DELETED) == false
				&& iso3166Alpha2State.equals(UNASSIGNED) == false) {
			throw new RuntimeException("Invalid " + iso3166Alpha2State);
		}

		this.iso3166Alpha2State = iso3166Alpha2State;
	}

	public String toString() {
		return iso3166Alpha2;
	}

}
