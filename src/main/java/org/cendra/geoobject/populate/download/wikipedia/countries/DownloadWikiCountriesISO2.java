package org.cendra.geoobject.populate.download.wikipedia.countries;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.cendra.geoobject.populate.PopulateProperties;
import org.cendra.geoobject.populate.download.Download;
import org.cendra.geoobject.populate.download.HTMLDocumentUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.jdom.Document;
import org.jdom.Element;

public class DownloadWikiCountriesISO2 extends Download {

	public static final String FILE_NAME = "iso_3166-1_alpha-2_officially_codes.html";
	public static String FULL_PATH = "downloads/wikipedia/countries";
	private String urlString = "https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2#Officially_assigned_code_elements";

	public DownloadWikiCountriesISO2(HTMLDocumentUtil htmlDocumentUtil,
			PopulateProperties populateProperties) {
		super(htmlDocumentUtil, populateProperties);
	}

	public static File getFullPathFileDownload(String pathHome) {
		return new File(pathHome + File.separatorChar
				+ replaceSeparatorChar(FULL_PATH) + File.separatorChar
				+ FILE_NAME + ".json");
	}

	public File download() throws Exception {

		File countriesFile = this.getFileFullPath(FULL_PATH, true);

		String path = countriesFile.getAbsolutePath() + File.separatorChar
				+ FILE_NAME;

		// -------------------------------------------------------------

		List<CountryWikipediaISO2> countries = parse(path);

		// -------------------------------------------------------------

		File json = new File(countriesFile.getAbsolutePath()
				+ File.separatorChar + FILE_NAME + ".json");

		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(json, countries);

		return json;
	}

	@SuppressWarnings("unchecked")
	public List<CountryWikipediaISO2> parse(String path) throws Exception {

		List<CountryWikipediaISO2> countries = new ArrayList<CountryWikipediaISO2>();

		String f = urlString;
		String[] ff = f.split("[/]");
		f = ff[ff.length - 1].toLowerCase().replace(":", "_");

		if (f.endsWith(".html")) {
			f = f.replaceAll(".html", "");
		}

		f += "_wikipedia.html";

		Document doc = this.getHtmlDocumentUtil().getDocument(urlString, true,
				path);

		Element e = this.getHtmlDocumentUtil().getElementById(
				doc.getRootElement(), "mw-content-text");

		e = (Element) e.getChildren().get(0);

		List<Element> elements = e.getChildren();

		int c = 0;

		for (Element ec : elements) {
			if (ec.getName().equals("table")) {
				if (c == 1) {
					e = ec;
					break;
				}
				c++;
			}
		}

		elements = e.getChildren();

		for (int i = 0; i < elements.size(); i++) {

			Element row = elements.get(i);

			if (i > 0 && i < 27) {

				List<Element> columns = row.getChildren();

				for (Element column : columns) {
					String iso2 = column.getValue();
					String iso2State = column.getAttributeValue("style");

					if (iso2State.toUpperCase().contains("90FF90")) { // OFfICIALLY
						iso2State = CountryWikipediaISO2.OFfICIALLY;

					} else if (iso2State.toUpperCase().contains("5FC")) { // USER_ASSIGNED
						iso2State = CountryWikipediaISO2.USER_ASSIGNED;

					} else if (iso2State.toUpperCase().contains("FC8")) { // EXCEPTIONALLY
						iso2State = CountryWikipediaISO2.EXCEPTIONALLY;

					} else if (iso2State.toUpperCase().contains("FA8072")) {// INDETERMINATELY
						iso2State = CountryWikipediaISO2.INDETERMINATELY;

					} else if (iso2State.toUpperCase().contains("FED")) { // TRANSITIONALLY
						iso2State = CountryWikipediaISO2.TRANSITIONALLY;

					} else if (iso2State.toUpperCase().contains("EDF")) { // DELETED
						iso2State = CountryWikipediaISO2.DELETED;

					} else if (iso2State.toUpperCase().contains(
							"ececec".toUpperCase())) { // UNASSIGNED
						iso2State = CountryWikipediaISO2.UNASSIGNED;
					}

					CountryWikipediaISO2 country = new CountryWikipediaISO2();
					country.setIso3166Alpha2(iso2);
					country.setIso3166Alpha2State(iso2State);

					countries.add(country);

				}

			}
		}

		return countries;
	}

}
