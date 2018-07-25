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

public class DownloadCountriesFormGeonames extends Download {

	public static final String FILE_NAME = "countries_form";

	public static String FULL_PATH = "downloads/geonames/countries";

	public DownloadCountriesFormGeonames(HTMLDocumentUtil htmlDocumentUtil,
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

		ObjectMapper mapper = new ObjectMapper();

		List<CountryGeoname> countries = mapper.readValue(DownloadCountries
				.getFullPathFileDownload(this.getPopulateProperties()
						.getPathHome()), mapper.getTypeFactory()
				.constructCollectionType(List.class, CountryGeoname.class));

		List<CountryFormGeoname> newCountries = new ArrayList<CountryFormGeoname>();

		for (CountryGeoname country : countries) {
			CountryFormGeoname countryFormGeoname = parse(countriesFile,
					country.getIso3166Alpha2(), country.getGeonamesURL());
			newCountries.add(countryFormGeoname);
		}

		// -------------------------------------------------------------

		File json = new File(countriesFile.getAbsolutePath()
				+ File.separatorChar + FILE_NAME + ".json");

		mapper.writeValue(json, newCountries);

		return json;
	}

	@SuppressWarnings("unchecked")
	public CountryFormGeoname parse(File countriesFile, String iso3166Alpha2,
			String geonamesURL) throws Exception {

		String f = geonamesURL;
		String[] ff = f.split("[/]");
		f = ff[ff.length - 1].toLowerCase().replace(":", "_");

		if (f.endsWith(".html")) {
			f = f.replaceAll(".html", "");
		}

		f += "_geonames.html";

		Document doc = this.getHtmlDocumentUtil().getDocument(
				geonamesURL,
				true,
				countriesFile.getAbsolutePath() + java.io.File.separatorChar
						+ iso3166Alpha2.toLowerCase() + "_" + f);

		Element body = this.getHtmlDocumentUtil().getFirstElementByElementName(
				doc.getRootElement(), "body");

		Element div = (Element) body.getChildren().get(4);

		String wikipediaURL = this
				.getHtmlDocumentUtil()
				.getFirstElementByElementName(
						(Element) div.getChildren().get(0), "a")
				.getAttribute("href").getValue();

		if (wikipediaURL.startsWith("https") == false) {
			wikipediaURL = wikipediaURL.replaceAll("http", "");
			wikipediaURL = "https" + wikipediaURL;
		}

		CountryFormGeoname countryFormGeoname = new CountryFormGeoname();
		countryFormGeoname.setIso3166Alpha2(iso3166Alpha2);
		countryFormGeoname.setWikipediaURL(wikipediaURL);

		Element table = (Element) div.getChildren().get(1);

		List<Element> rows = table.getChildren();

		String geonameMapURL = this
				.getHtmlDocumentUtil()
				.getFirstElementByElementName(
						(Element) rows.get(0).getChildren().get(1), "a")
				.getAttribute("href").getValue();
		countryFormGeoname.setGeonamesMapURL(geonameMapURL);

		String currency = ((Element) rows.get(6).getChildren().get(1))
				.getValue();
		String[] currencyList = currency.split(",");
		for (String currencyItem : currencyList) {
			currencyItem = currencyItem.split("[(]")[1].replaceAll("[)]", "")
					.trim();
			countryFormGeoname.addCurrency(currencyItem);
		}

		String languajesString = ((Element) rows.get(7).getChildren().get(1))
				.getValue();
		String[] languajes = languajesString.split(",");
		for (String languajeItem : languajes) {

			if (languajeItem.contains("(") && languajeItem.contains(")")) {
				languajeItem = languajeItem.split("[(]")[1].replaceAll("[)]",
						"").trim();
				if (languajeItem.contains("-")) {
					languajeItem = languajeItem.split("-")[0];
				}
			}

			if (languajeItem.contains("-")) {
				languajeItem = languajeItem.split("-")[0];
			}
			countryFormGeoname.addLanguage(languajeItem);
		}

		for (Element row : rows) {
			if (((Element) row.getChildren().get(0)).getValue().contains(
					"national flag")) {
				String url = this
						.getHtmlDocumentUtil()
						.getFirstElementByElementName(
								(Element) row.getChildren().get(1), "a")
						.getAttributeValue("href");

				countryFormGeoname.setFlagURL("http://www.geonames.org" + url);

				// this.getHtmlDocumentUtil().download(
				// countryFormGeoname.getFlagURL(),
				// countriesFile.getAbsolutePath()
				// + java.io.File.separatorChar
				// + iso3166Alpha2.toLowerCase() + "_flag_"
				// + url.split("/")[url.split("/").length - 1]);

				// ----------------------------

				String ext = countryFormGeoname.getFlagURL().split("/")[countryFormGeoname
						.getFlagURL().split("/").length - 1].split("[.]")[1];

				String fileName = countryFormGeoname.getIso3166Alpha2()
						.toLowerCase() + "_national_flag_" + ext + "." + ext;

				countryFormGeoname.setFlagPath(countriesFile.getAbsolutePath()
						+ File.separatorChar + fileName);

				this.getHtmlDocumentUtil().download(
						countryFormGeoname.getFlagURL(),
						countryFormGeoname.getFlagPath());

				// ----------------------------

				break;
			}
		}

		div = (Element) div.getChildren().get(3);

		String url = this.getHtmlDocumentUtil()
				.getFirstElementByElementName((Element) div, "img")
				.getAttributeValue("src");

		countryFormGeoname.setMapURL("http://www.geonames.org" + url);

		// ----------------------------

		String ext = countryFormGeoname.getMapURL().split("/")[countryFormGeoname
				.getMapURL().split("/").length - 1].split("[.]")[1];

		String fileName = countryFormGeoname.getIso3166Alpha2().toLowerCase()
				+ "_national_map_" + ext + "." + ext;

		countryFormGeoname.setMapPath(countriesFile.getAbsolutePath()
				+ File.separatorChar + fileName);

		try {
			this.getHtmlDocumentUtil().download(countryFormGeoname.getMapURL(),
					countryFormGeoname.getMapPath());			
		} catch (Exception e) {
			System.out.println("WARNING: Unknown "
					+ countryFormGeoname.getMapURL());
			countryFormGeoname.setMapPath(null);
		}
		
		// ----------------------------

		div = (Element) body.getChildren().get(6);
		table = (Element) div.getChildren().get(0);
		Element tr = (Element) table.getChildren().get(0);
		Element td = (Element) tr.getChildren().get(1);
		table = (Element) td.getChildren().get(0);
		tr = (Element) table.getChildren().get(0);
		td = (Element) tr.getChildren().get(0);
		Element small = (Element) td.getChildren().get(0);

		Element a = (Element) small.getChildren().get(8);
		String otherCountryNamesURL = "http://www.geonames.org"
				+ a.getAttributeValue("href");

		countryFormGeoname.setOtherCountryNamesURL(otherCountryNamesURL);

		a = (Element) small.getChildren().get(0);
		String administrativeDivisionURL = "http://www.geonames.org"
				+ a.getAttributeValue("href");
		countryFormGeoname
				.setAdministrativeDivisionURL(administrativeDivisionURL);

		return countryFormGeoname;

	}

}
