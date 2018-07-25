package org.cendra.geoobject.populate.download.wikipedia.countries;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.cendra.geoobject.populate.PopulateProperties;
import org.cendra.geoobject.populate.download.Download;
import org.cendra.geoobject.populate.download.HTMLDocumentUtil;
import org.cendra.geoobject.populate.download.geonames.countries.CountryFormGeoname;
import org.cendra.geoobject.populate.download.geonames.countries.DownloadCountriesFormGeonames;
import org.codehaus.jackson.map.ObjectMapper;
import org.jdom.Document;
import org.jdom.Element;

public class DownloadCountriesFormWikipedia extends Download {

	public static final String FILE_NAME = "countries_form";

	public static String FULL_PATH = "downloads/wikipedia/countries";

	private String urlStsartString = " https://en.wikipedia.org";

	public DownloadCountriesFormWikipedia(HTMLDocumentUtil htmlDocumentUtil,
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

		List<CountryFormGeoname> countries = mapper.readValue(
				DownloadCountriesFormGeonames.getFullPathFileDownload(this
						.getPopulateProperties().getPathHome()),
				mapper.getTypeFactory().constructCollectionType(List.class,
						CountryFormGeoname.class));

		List<CountryFormWikipedia> newCountries = new ArrayList<CountryFormWikipedia>();

		for (CountryFormGeoname country : countries) {
			CountryFormWikipedia countryFormGeoname = parse(countriesFile,
					country.getIso3166Alpha2(), country.getWikipediaURL());
			newCountries.add(countryFormGeoname);
		}

		// -------------------------------------------------------------

		File json = new File(countriesFile.getAbsolutePath()
				+ File.separatorChar + FILE_NAME + ".json");

		mapper.writeValue(json, newCountries);

		return json;
	}

	public File check() throws Exception {

		System.out
				.println("Controlando que los links a wikipedia de Geonames sean iguales a estos, a continuación los paíese en conflicto:\n");
		System.out.println("Nota: Wikipedia URL - Geoname URL\n");

		ObjectMapper mapper = new ObjectMapper();

		List<CountryWikipedia> countries = mapper.readValue(
				DownloadWikiCountries.getFullPathFileDownload(this
						.getPopulateProperties().getPathHome()),
				mapper.getTypeFactory().constructCollectionType(List.class,
						CountryWikipedia.class));

		for (CountryWikipedia country : countries) {

			List<CountryFormGeoname> countries2 = mapper.readValue(
					DownloadCountriesFormGeonames.getFullPathFileDownload(this
							.getPopulateProperties().getPathHome()),
					mapper.getTypeFactory().constructCollectionType(List.class,
							CountryFormGeoname.class));
			for (CountryFormGeoname country2 : countries2) {

				if (country.getIso3166Alpha2().equalsIgnoreCase(
						country2.getIso3166Alpha2())
						&& country.getWikipediaURL().equalsIgnoreCase(
								country2.getWikipediaURL()) == false) {

					String msg = country.getIso3166Alpha2() + " URL1: "
							+ country.getWikipediaURL() + " URL2: "
							+ country2.getWikipediaURL();

					// throw new IllegalStateException(msg);

					System.out.println(msg);
				}
			}

		}

		// -------------------------------------------------------------

		return null;
	}

	public CountryFormWikipedia parse(File countriesFile, String iso3166Alpha2,
			String wikipediaURL) throws Exception {

		String f = wikipediaURL;
		String[] ff = f.split("[/]");
		f = ff[ff.length - 1].toLowerCase().replace(":", "_");

		if (f.endsWith(".html") == false) {
			f += ".html";
		}

		Document doc = this.getHtmlDocumentUtil().getDocument(
				wikipediaURL,
				true,
				countriesFile.getAbsolutePath() + java.io.File.separatorChar
						+ iso3166Alpha2.toLowerCase() + "_" + f);

		String shortName = getShortNanme(doc);

		CountryFormWikipedia countryFormWikipedia = new CountryFormWikipedia();

		countryFormWikipedia.setIso3166Alpha2(iso3166Alpha2);

		countryFormWikipedia.setCountry(shortName);

		getFiles(countryFormWikipedia, doc, countriesFile, wikipediaURL);

		return countryFormWikipedia;

	}

	private String getShortNanme(Document doc) {

		return this.getHtmlDocumentUtil()
				.getElementById(doc.getRootElement(), "firstHeading")
				.getValue();
	}

	private void getFiles(CountryFormWikipedia countryFormWikipedia,
			Document doc, File countriesFile, String wikipediaURL)
			throws Exception {

		Element e = this.getHtmlDocumentUtil().getElementById(
				doc.getRootElement(), "mw-content-text");

		if (this.getHtmlDocumentUtil().isContainsElementByClassElementName(e,
				"infobox geography vcard", "table")) {
			e = this.getHtmlDocumentUtil().getFirstElementByClassElementName(e,
					"infobox geography vcard", "table");
		} else if (this.getHtmlDocumentUtil()
				.isContainsElementByClassElementName(e, "infobox", "table")) {
			e = this.getHtmlDocumentUtil().getFirstElementByClassElementName(e,
					"infobox", "table");
		} else if (this.getHtmlDocumentUtil()
				.isContainsElementByClassElementName(e, "infobox", "table")) {
			e = this.getHtmlDocumentUtil().getFirstElementByClassElementName(e,
					"infobox vcard", "table");
		}

		List<Element> elements = this.getHtmlDocumentUtil()
				.getElementsByClassElementName(e, "image", "a");

		for (Element item : elements) {

			// String title = item.getAttributeValue("title");

			String f = item.getAttributeValue("href");
			String[] ff = f.split("[/]");
			f = ff[ff.length - 1].toLowerCase().replace(":", "_");

			if (f.endsWith(".html")) {
				f = f.replaceAll(".html", "");
			}

			f += "_wikipedia.html";

			doc = this.getHtmlDocumentUtil().getDocument(
					urlStsartString + item.getAttributeValue("href"),
					true,
					countriesFile.getAbsolutePath()
							+ java.io.File.separatorChar
							+ countryFormWikipedia.getIso3166Alpha2()
									.toLowerCase() + "_" + f);

			e = this.getHtmlDocumentUtil().getElementById(doc.getRootElement(),
					"file");

			e = this.getHtmlDocumentUtil().getFirstElementByElementName(e, "a");

			String url = null;

			if (e.getAttributeValue("href").startsWith(
					wikipediaURL.split(":")[0])) {
				url = e.getAttributeValue("href");
			} else {
				url = wikipediaURL.split(":")[0] + ":"
						+ e.getAttributeValue("href");
			}

			e = this.getHtmlDocumentUtil().getFirstElementByElementName(e,
					"img");
			// String fileName = e.getAttributeValue("alt");

			// File urlFile = new File(File.SOURCE_URL_WIKIPEDIA);
			// urlFile.setFileName(fileName);
			// urlFile.setTitle(title);
			// urlFile.setLink(url);
			//
			// files.add(urlFile);

			if (url.toLowerCase().contains("flag")) {
				countryFormWikipedia.setFlagURL(url);

				String ext = countryFormWikipedia.getFlagURL().split("/")[countryFormWikipedia
						.getFlagURL().split("/").length - 1].split("[.]")[1];

				String fileName = countryFormWikipedia.getIso3166Alpha2()
						.toLowerCase() + "_flag_" + ext + "." + ext;

				countryFormWikipedia.setFlagPath(countriesFile
						.getAbsolutePath() + File.separatorChar + fileName);

				this.getHtmlDocumentUtil().download(
						countryFormWikipedia.getFlagURL(),
						countryFormWikipedia.getFlagPath());

			} else if (url.toLowerCase().contains("coat")) {
				countryFormWikipedia.setCoatOfArmsURL(url);

				String ext = countryFormWikipedia.getCoatOfArmsURL().split("/")[countryFormWikipedia
						.getCoatOfArmsURL().split("/").length - 1].split("[.]")[1];

				String fileName = countryFormWikipedia.getIso3166Alpha2()
						.toLowerCase() + "_coat_of_arms_" + ext + "." + ext;

				countryFormWikipedia.setCoatOfArmsPath(countriesFile
						.getAbsolutePath() + File.separatorChar + fileName);

				this.getHtmlDocumentUtil().download(
						countryFormWikipedia.getCoatOfArmsURL(),
						countryFormWikipedia.getCoatOfArmsPath());

				// String fileName = countryFormWikipedia.getIso3166Alpha2()
				// .toLowerCase()
				// + "_coat_of_arms_"
				// + url.split("/")[url.split("/").length - 1];
				//
				// this.getHtmlDocumentUtil().download(
				// countryFormWikipedia.getCoatOfArmsURL(),
				// countriesFile.getAbsolutePath()
				// + java.io.File.separatorChar + fileName);
				//
				// countryFormWikipedia.setCoatOfArmsPath(countriesFile
				// .getAbsolutePath() + File.separatorChar + fileName);

			} else if (url.toLowerCase().contains("orthogonal")
					|| url.toLowerCase().contains("orthographic_projection")) {
				countryFormWikipedia.setOrthographicProjectionURL(url);

				String ext = countryFormWikipedia.getOrthographicProjectionURL().split("/")[countryFormWikipedia
						.getOrthographicProjectionURL().split("/").length - 1].split("[.]")[1];

				String fileName = countryFormWikipedia.getIso3166Alpha2()
						.toLowerCase() + "_orthographic_projection_" + ext + "." + ext;

				countryFormWikipedia.setOrthographicProjectionPath(countriesFile
						.getAbsolutePath() + File.separatorChar + fileName);

				this.getHtmlDocumentUtil().download(
						countryFormWikipedia.getOrthographicProjectionURL(),
						countryFormWikipedia.getOrthographicProjectionPath());

//				String fileName = countryFormWikipedia.getIso3166Alpha2()
//						.toLowerCase()
//						+ "_orthographic_projection_"
//						+ url.split("/")[url.split("/").length - 1];
//
//				this.getHtmlDocumentUtil().download(
//						countryFormWikipedia.getOrthographicProjectionURL(),
//						countriesFile.getAbsolutePath()
//								+ java.io.File.separatorChar + fileName);
//
//				countryFormWikipedia
//						.setOrthographicProjectionPath(countriesFile
//								.getAbsolutePath()
//								+ File.separatorChar
//								+ fileName);
			}

		}
	}

}
