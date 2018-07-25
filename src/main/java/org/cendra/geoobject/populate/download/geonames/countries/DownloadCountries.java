package org.cendra.geoobject.populate.download.geonames.countries;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.cendra.geoobject.populate.PopulateProperties;
import org.cendra.geoobject.populate.download.Download;
import org.cendra.geoobject.populate.download.HTMLDocumentUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.jdom.Document;
import org.jdom.Element;

public class DownloadCountries extends Download {

	public static final String FILE_NAME = "countries.html";

	public static String FULL_PATH = "downloads/geonames/countries";

	private String urlString = "http://www.geonames.org/countries/";

	public DownloadCountries(HTMLDocumentUtil htmlDocumentUtil,
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

		String f = urlString;
		String[] ff = f.split("[/]");
		f = ff[ff.length - 1].toLowerCase().replace(":", "_");

		if (f.endsWith(".html")) {
			f = f.replaceAll(".html", "");
		}

		f += "_geonames.html";

		Document doc = this.getHtmlDocumentUtil().getDocument(urlString, true,
				path);

		Element e = this.getHtmlDocumentUtil().getElementById(
				doc.getRootElement(), "countries");

		@SuppressWarnings("unchecked")
		List<Element> elements = e.getChildren();

		List<CountryGeoname> countries = new ArrayList<CountryGeoname>();

		for (int i = 0; i < elements.size(); i++) {
			Element row = elements.get(i);

			if (i > 0) {
				CountryGeoname country = getCountry(row);
				countries.add(country);
			}

		}

		// -------------------------------------------------------------

		File json = new File(countriesFile.getAbsolutePath()
				+ File.separatorChar + FILE_NAME + ".json");

		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(json, countries);

		return json;
	}

	private CountryGeoname getCountry(Element row) throws Exception {

		@SuppressWarnings("unchecked")
		List<Element> columns = row.getChildren();

		String iso2 = columns.get(0).getValue();
		String iso3 = columns.get(1).getValue();
		String isoNumeric = columns.get(2).getValue();
		String fips = columns.get(3).getValue();

		Element a = this.getHtmlDocumentUtil().getFirstElementByElementName(
				columns.get(4), "a");
		String url = "http://www.geonames.org" + a.getAttributeValue("href");
		String shortName = a.getValue();

		String capital = columns.get(5).getValue();
		String continentCode = columns.get(8).getValue();

		CountryGeoname country = new CountryGeoname();

		country.setIso3166Alpha2(iso2);
		country.setIso3166Alpha3(iso3);
		country.setIso3166Numeric(isoNumeric);
		country.setFips(fips);
		country.setGeonamesURL(url);
		country.setCountry(shortName);
		country.setCapital(capital);
		country.setContinent(continentCode);

		return country;

	}

}
