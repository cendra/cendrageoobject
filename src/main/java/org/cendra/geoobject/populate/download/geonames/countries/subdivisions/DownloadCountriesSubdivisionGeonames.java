package org.cendra.geoobject.populate.download.geonames.countries.subdivisions;

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

public class DownloadCountriesSubdivisionGeonames extends Download {

	public static final String FILE_NAME = "countries_subdivisions";

	public static String FULL_PATH = "downloads/geonames/subdivisions";

	public DownloadCountriesSubdivisionGeonames(HTMLDocumentUtil htmlDocumentUtil,
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

		List<CountrySubdivisionsGeoname> newCountries = new ArrayList<CountrySubdivisionsGeoname>();

		for (CountryFormGeoname country : countries) {

			CountrySubdivisionsGeoname countrySubdivisionsGeoname = new CountrySubdivisionsGeoname();
			countrySubdivisionsGeoname.setIso3166Alpha2(country
					.getIso3166Alpha2());

			List<Subdivision> subdivisions = parse(countriesFile,
					country.getIso3166Alpha2(),
					country.getAdministrativeDivisionURL());

			countrySubdivisionsGeoname.setSubdivisions(subdivisions);

			newCountries.add(countrySubdivisionsGeoname);
		}

		// -------------------------------------------------------------

		File json = new File(countriesFile.getAbsolutePath()
				+ File.separatorChar + FILE_NAME + ".json");

		mapper.writeValue(json, newCountries);

		return json;
	}

	public List<Subdivision> parse(File countriesFile, String iso3166Alpha2,
			String administrativeDivisionURL) throws Exception {

		List<Subdivision> subdivisions = new ArrayList<Subdivision>();

		String f = administrativeDivisionURL;
		String[] ff = f.split("[/]");
		f = ff[ff.length - 1].toLowerCase().replace(":", "_");

		if (f.endsWith(".html")) {
			f = f.replaceAll(".html", "");
		}

		f += "_geonames.html";

		Document doc = this.getHtmlDocumentUtil().getDocument(
				administrativeDivisionURL,
				true,
				countriesFile.getAbsolutePath() + java.io.File.separatorChar
						+ iso3166Alpha2.toLowerCase() + "_" + f);

		Element table = this.getHtmlDocumentUtil().getElementById(
				doc.getRootElement(), "subdivtable1");

		if (table != null) {

			@SuppressWarnings("unchecked")
			List<Element> rows = table.getChildren();

			for (int i = 0; i < rows.size(); i++) {
				if (i > 0) {
					Element row = rows.get(i);

					if (row.getChildren().size() == 12) {

						String iso31662 = ((Element) ((Element) row
								.getChildren().get(1)).getChildren().get(0))
								.getValue();
						String fips = ((Element) ((Element) row.getChildren()
								.get(2)).getChildren().get(0)).getValue();
						String gn = ((Element) row.getChildren().get(3))
								.getValue();
						String name = ((Element) ((Element) row.getChildren()
								.get(4)).getChildren().get(0)).getValue();

						Element td4 = (Element) row.getChildren().get(4);

						Element span = (Element) td4.getChildren().get(0);

						String wikipediaURL = null;

						String geonamesMapURL = null;

						if (span.getChildren().size() > 0) {

							Element a1 = (Element) span.getChildren().get(0);

							geonamesMapURL = a1.getAttributeValue("href");

							if (span.getChildren().size() > 1) {

								Element a2 = (Element) span.getChildren()
										.get(1);

								wikipediaURL = a2.getAttributeValue("href");

								if (wikipediaURL.startsWith("https") == false) {
									wikipediaURL = wikipediaURL.replaceAll(
											"http", "");
									wikipediaURL = "https" + wikipediaURL;
								}
							}

						}

						String type = ((Element) ((Element) row.getChildren()
								.get(5)).getChildren().get(0)).getValue();
						String capital = ((Element) ((Element) row
								.getChildren().get(6)).getChildren().get(0))
								.getValue();
						String geonamesMapCapitalURL = null;
						if (capital != null && capital.isEmpty() == false) {
							Element td6 = (Element) row.getChildren().get(6);
							Element span6 = (Element) td6.getChildren().get(0);

							if (span6.getChildren().size() > 0) {
								Element a6 = (Element) span6.getChildren().get(
										0);

								geonamesMapCapitalURL = a6
										.getAttributeValue("href");
							}

						} else {
							capital = null;
						}

						String languaje = ((Element) ((Element) row
								.getChildren().get(8)).getChildren().get(0))
								.getValue();
						String continentCode = ((Element) ((Element) row
								.getChildren().get(9)).getChildren().get(0))
								.getValue();

						Subdivision subDivision = new Subdivision();
						subDivision.setIso31662(iso31662);
						subDivision.setFips(fips);
						subDivision.setGn(gn);
						subDivision.setName(name);						
						subDivision.setGeonamesMapURL(geonamesMapURL);
						
//						if(wikipediaURL != null && wikipediaURL.equalsIgnoreCase("https://en.wikipedia.org/wiki/Douglas%2C_Isle_of_Ma%E2%80%A6")){
//							wikipediaURL = "https://en.wikipedia.org/wiki/Douglas,_Isle_of_Man";
//						}
						
						subDivision.setWikipediaURL(wikipediaURL);
						subDivision.setType(type);
						subDivision.setCapital(capital);
						subDivision
								.setGeonamesMapCapitalURL(geonamesMapCapitalURL);

						if (languaje != null && languaje.isEmpty() == false) {

							String[] languajes = languaje.split(",");
							for (String code : languajes) {

								if (code.contains("(") && code.contains(")")) {
									code = code.split("[(]")[1].replaceAll(
											"[)]", "").trim();
									if (code.contains("-")) {
										code = code.split("-")[0];
									}
								}

								if (code.contains("-")) {
									code = code.split("-")[0];
								}

								subDivision.addLanguage(code);
							}
						}

						subDivision.setContinent(continentCode);
						subdivisions.add(subDivision);
					}

				}

			}
		}

		return subdivisions;

	}

}
