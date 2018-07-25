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

public class DownloadWikiCountries extends Download {

	public static final String FILE_NAME = "iso_3166_1.html";
	public static String FULL_PATH = "downloads/wikipedia/countries";
	private String urlString = "https://en.wikipedia.org/wiki/ISO_3166-1";

	public DownloadWikiCountries(HTMLDocumentUtil htmlDocumentUtil,
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

		List<CountryWikipedia> countries = parse(path);

		// -------------------------------------------------------------

		File json = new File(countriesFile.getAbsolutePath()
				+ File.separatorChar + FILE_NAME + ".json");

		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(json, countries);

		return json;
	}

	@SuppressWarnings("unchecked")
	public List<CountryWikipedia> parse(String path) throws Exception {

		List<CountryWikipedia> countries = new ArrayList<CountryWikipedia>();

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

		c = 0;

		for (int i = 0; i < elements.size(); i++) {
			Element row = elements.get(i);

			if (i > 0) {
				CountryWikipedia country = getCountry(row);
				countries.add(country);
			}

		}

		return countries;
	}

	private CountryWikipedia getCountry(Element row) throws Exception {

		@SuppressWarnings("unchecked")
		List<Element> columns = row.getChildren();

		Element column = columns.get(1);
		String iso2 = this.getHtmlDocumentUtil()
				.getFirstElementByElementName(column, "span").getValue();

		column = columns.get(2);
		String iso3 = this.getHtmlDocumentUtil()
				.getFirstElementByElementName(column, "span").getValue();

		column = columns.get(3);
		String isoNumeric = this.getHtmlDocumentUtil()
				.getFirstElementByElementName(column, "span").getValue();				

		column = columns.get(5);
		String independent = column.getValue();

		column = columns.get(0);

		Element a = this.getHtmlDocumentUtil().getFirstElementByElementName(
				column, "a");

		String urlWikipedia2 = "https://en.wikipedia.org"
				+ a.getAttributeValue("href");

		CountryWikipedia country = new CountryWikipedia();

		country.setIso3166Alpha2(iso2);
		country.setIso3166Alpha3(iso3);
		country.setIso3166Numeric(isoNumeric);
		country.setIndependent(independent.equalsIgnoreCase("yes"));
		country.setWikipediaURL(urlWikipedia2);
		country.setWikipediaISO31662URL("https://en.wikipedia.org/wiki/ISO_3166-2:" + country.getIso3166Alpha2());

		return country;

	}

}
