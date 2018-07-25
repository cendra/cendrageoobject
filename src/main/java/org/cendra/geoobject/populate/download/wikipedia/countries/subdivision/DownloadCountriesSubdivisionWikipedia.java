package org.cendra.geoobject.populate.download.wikipedia.countries.subdivision;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.cendra.geoobject.populate.PopulateProperties;
import org.cendra.geoobject.populate.download.Download;
import org.cendra.geoobject.populate.download.HTMLDocumentUtil;
import org.cendra.geoobject.populate.download.geonames.countries.subdivisions.CountrySubdivisionsGeoname;
import org.cendra.geoobject.populate.download.geonames.countries.subdivisions.DownloadCountriesSubdivisionGeonames;
import org.cendra.geoobject.populate.download.geonames.countries.subdivisions.Subdivision;
import org.codehaus.jackson.map.ObjectMapper;
import org.jdom.Document;
import org.jdom.Element;

public class DownloadCountriesSubdivisionWikipedia extends Download {

	public static final String FILE_NAME = "countries_subdivisions";

	public static String FULL_PATH = "downloads/wikipedia/countries/subdivisions";

	private String urlStsartString = " https://en.wikipedia.org";

	public DownloadCountriesSubdivisionWikipedia(
			HTMLDocumentUtil htmlDocumentUtil,
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

		List<CountrySubdivisionsGeoname> countries = mapper.readValue(
				DownloadCountriesSubdivisionGeonames
						.getFullPathFileDownload(this.getPopulateProperties()
								.getPathHome()),
				mapper.getTypeFactory().constructCollectionType(List.class,
						CountrySubdivisionsGeoname.class));

		List<CountrySubdivisionsWikipedia> newCountries = new ArrayList<CountrySubdivisionsWikipedia>();

		for (CountrySubdivisionsGeoname country : countries) {

			CountrySubdivisionsWikipedia countrySubdivisionsWikipedia = new CountrySubdivisionsWikipedia();
			countrySubdivisionsWikipedia.setIso3166Alpha2(country
					.getIso3166Alpha2());

			for (Subdivision subdivision : country.getSubdivisions()) {

				System.out.println(country.getIso3166Alpha2() + " --- "
						+ subdivision.getIso31662());

				if (subdivision.getWikipediaURL() != null) {

					String code = subdivision.getIso31662();
					if (code == null) {
						code = subdivision.getFips();
					}
					if (code == null) {
						code = subdivision.getGn();
					}

					SubdivisionsFormWikipedia newSubdivision = parse(
							countriesFile, country.getIso3166Alpha2(), code,
							subdivision.getWikipediaURL());

					countrySubdivisionsWikipedia
							.addSubdivisions(newSubdivision);
				}

			}

			newCountries.add(countrySubdivisionsWikipedia);
		}

		// -------------------------------------------------------------

		File json = new File(countriesFile.getAbsolutePath()
				+ File.separatorChar + FILE_NAME + ".json");

		mapper.writeValue(json, newCountries);

		return json;
	}

	public SubdivisionsFormWikipedia parse(File countriesFile,
			String iso3166Alpha2, String iso31662, String wikipediaURL)
			throws Exception {

		String f = wikipediaURL;
		String[] ff = f.split("[/]");
		f = ff[ff.length - 1].toLowerCase().replace(":", "_");

		if (f.endsWith(".html") == false) {
			f += ".html";
		}

		Document doc = null;

		try {
			doc = this.getHtmlDocumentUtil().getDocument(
					wikipediaURL,
					true,
					countriesFile.getAbsolutePath()
							+ java.io.File.separatorChar
							+ iso3166Alpha2.toLowerCase() + "_"
							+ iso31662.toLowerCase() + "_" + f);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		String shortName = getShortNanme(doc);

		SubdivisionsFormWikipedia subdivisionsFormWikipedia = new SubdivisionsFormWikipedia();

		subdivisionsFormWikipedia.setIso31662(iso31662);

		subdivisionsFormWikipedia.setCountry(shortName);

		getFiles(subdivisionsFormWikipedia, doc, countriesFile, wikipediaURL,
				iso3166Alpha2);

		return subdivisionsFormWikipedia;

	}

	private String getShortNanme(Document doc) {

		return this.getHtmlDocumentUtil()
				.getElementById(doc.getRootElement(), "firstHeading")
				.getValue();
	}

	private void getFiles(SubdivisionsFormWikipedia subdivisionsFormWikipedia,
			Document doc, File countriesFile, String wikipediaURL,
			String iso3166Alpha2) throws Exception {

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

			try {
				doc = this.getHtmlDocumentUtil().getDocument(
						urlStsartString + item.getAttributeValue("href"),
						true,
						countriesFile.getAbsolutePath()
								+ java.io.File.separatorChar
								+ iso3166Alpha2.toLowerCase()
								+ "_"
								+ subdivisionsFormWikipedia.getIso31662()
										.toLowerCase() + "_" + f);

			} catch (Exception e2) {
				doc = null;
				e2.printStackTrace();
			}

			if (doc != null) {
				e = this.getHtmlDocumentUtil().getElementById(
						doc.getRootElement(), "file");

				e = this.getHtmlDocumentUtil().getFirstElementByElementName(e,
						"a");

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
					subdivisionsFormWikipedia.setFlagURL(url);
				} else if (url.toLowerCase().contains("coat")) {
					subdivisionsFormWikipedia.setCoatOfArmsURL(url);
				} else if (url.toLowerCase().contains("orthogonal")
						|| url.toLowerCase()
								.contains("orthographic_projection")) {
					subdivisionsFormWikipedia.setOrthographicProjectionURL(url);
				}
			}

		}
	}
}
