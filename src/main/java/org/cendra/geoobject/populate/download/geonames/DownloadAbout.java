package org.cendra.geoobject.populate.download.geonames;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.cendra.geoobject.populate.PopulateProperties;
import org.cendra.geoobject.populate.download.Download;
import org.cendra.geoobject.populate.download.HTMLDocumentUtil;
import org.cendra.geoobject.populate.download.geonames.continents.ContinentGeoname;
import org.cendra.geoobject.populate.download.geonames.continents.DownloadContinents;
import org.cendra.geoobject.populate.download.geonames.countries.CountryInfoGeoname;
import org.cendra.geoobject.populate.download.geonames.countries.DownloadCountriesInfo;
import org.cendra.geoobject.populate.download.geonames.countries.alternatenames.NameAbout;
import org.cendra.geoobject.populate.download.geonames.countries.subdivisions.Admin1;
import org.cendra.geoobject.populate.download.geonames.countries.subdivisions.ContinentAboutCountrySubdivisions;
import org.cendra.geoobject.populate.download.geonames.countries.subdivisions.CountryAboutSubdivisions;
import org.cendra.geoobject.populate.download.geonames.countries.subdivisions.DownloadAdmin1;
import org.codehaus.jackson.map.ObjectMapper;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class DownloadAbout extends Download {

	// public static final String FILE_NAME = "subdivisions_about";
	// public static String FULL_PATH =
	// "downloads/geonames/countries/subdivisions";

	public static final String FILE_NAME = "all_about";
	public static String FULL_PATH = "downloads/geonames/all";

	private int c = 1999;

	public DownloadAbout(HTMLDocumentUtil htmlDocumentUtil,
			PopulateProperties populateProperties) {
		super(htmlDocumentUtil, populateProperties);
		c = 1999;
	}

	public static File getFullPathFileDownload(String pathHome) {
		return new File(pathHome + File.separatorChar
				+ replaceSeparatorChar(FULL_PATH) + File.separatorChar
				+ FILE_NAME + ".json");
	}

	public File download(boolean forceDownload) throws Exception {
		c = 1999;
		c = 0;

		File newSubdivisionsFile = this.getFileFullPath(FULL_PATH, true);

		ObjectMapper mapper = new ObjectMapper();

		List<Admin1> subdivisions = mapper.readValue(DownloadAdmin1
				.getFullPathFileDownload(this.getPopulateProperties()
						.getPathHome()), mapper.getTypeFactory()
				.constructCollectionType(List.class, Admin1.class));

		List<ContinentGeoname> continentsInfo = mapper.readValue(
				DownloadContinents.getFullPathFileDownload(this
						.getPopulateProperties().getPathHome()),
				mapper.getTypeFactory().constructCollectionType(List.class,
						ContinentGeoname.class));

		List<CountryInfoGeoname> countriesInfo = mapper.readValue(
				DownloadCountriesInfo.getFullPathFileDownload(this
						.getPopulateProperties().getPathHome()),
				mapper.getTypeFactory().constructCollectionType(List.class,
						CountryInfoGeoname.class));

		List<ContinentAboutCountrySubdivisions> continents = new ArrayList<ContinentAboutCountrySubdivisions>();

		for (Admin1 admin1 : subdivisions) {

			String path = newSubdivisionsFile.getAbsolutePath()
					+ File.separatorChar
					+ admin1.getIso3166Alpha2().toLowerCase() + "_"
					+ admin1.getFips().toLowerCase() + "_"
					+ admin1.getGeonameId() + "_about.rdf.xml";

			GeonameAbout subdivisionGeonameAbout = new GeonameAbout();
			subdivisionGeonameAbout = buildGeonameAbout(
					subdivisionGeonameAbout, path, admin1.getGeonameId(),
					admin1.getIso3166Alpha2Fips(), forceDownload);

			ContinentAboutCountrySubdivisions continent = null;
			CountryAboutSubdivisions country = null;

			for (ContinentAboutCountrySubdivisions continentItem : continents) {

				List<CountryAboutSubdivisions> countries = continentItem
						.getCountries();

				boolean b = false;

				for (CountryAboutSubdivisions countryItem : countries) {
					if (countryItem.getCode().equalsIgnoreCase(
							admin1.getIso3166Alpha2())) {
						country = countryItem;
						b = true;
						break;
					}
				}

				if (b) {
					continent = continentItem;
					break;
				}
			}

			if (country == null) {
				country = new CountryAboutSubdivisions();

				for (CountryInfoGeoname countryInfoGeoname : countriesInfo) {

					if (countryInfoGeoname.getIso().equalsIgnoreCase(
							admin1.getIso3166Alpha2())) {

						String path2 = newSubdivisionsFile.getAbsolutePath()
								+ File.separatorChar
								+ countryInfoGeoname.getIso().toLowerCase()
								+ "_" + countryInfoGeoname.getGeonameId()
								+ "_about.rdf.xml";

						country = (CountryAboutSubdivisions) buildGeonameAbout(
								country, path2,
								countryInfoGeoname.getGeonameId(),
								countryInfoGeoname.getIso(), forceDownload);

						if (continent == null) {
							continent = new ContinentAboutCountrySubdivisions();
							for (ContinentGeoname continentInfoGeoname : continentsInfo) {
								if (countryInfoGeoname.getContinent()
										.equalsIgnoreCase(
												continentInfoGeoname.getCode())) {

									String path3 = newSubdivisionsFile
											.getAbsolutePath()
											+ File.separatorChar
											+ continentInfoGeoname.getCode()
													.toLowerCase()
											+ "_"
											+ continentInfoGeoname
													.getGeonameId()
											+ "_about.rdf.xml";

									continent = (ContinentAboutCountrySubdivisions) buildGeonameAbout(
											continent,
											path3,
											continentInfoGeoname.getGeonameId(),
											continentInfoGeoname.getCode(), forceDownload);

									break;

								}
							}
						}

						break;

					}
				}
				continent.addCountry(country);
				continents.add(continent);

			}

			country.addSubdivision(subdivisionGeonameAbout);

		}

		// -------------------------------------------------------------

		File json = new File(newSubdivisionsFile.getAbsolutePath()
				+ File.separatorChar + FILE_NAME + ".json");

		mapper.writeValue(json, continents);

		return newSubdivisionsFile;
	}

	private GeonameAbout buildGeonameAbout(GeonameAbout geonameAbout,
			String path, Long geonameId, String code, boolean forceDownload) throws Exception {

		if (c >= 1999) {

			int m = 60;
			long s = (long) (60000 * m);

			System.out.println("\nSe va a hacer una interrupción de " + m
					+ " minutos de espera, luego se retomara con la descarga. "
					+ LocalDateTime.now());

			c = 1;

			try {
				Thread.sleep(s);
			} catch (InterruptedException e) {
				System.out
						.println("\n"
								+ m
								+ " minutos de espera transcurridos, se comenzara con la descarga nuevamente. "
								+ LocalDateTime.now());
			}
		}

		String link = "http://sws.geonames.org/" + geonameId + "/about.rdf";

		File f = new File(path);
		boolean b = f.exists();

		if (forceDownload || b == false) {
			this.getHtmlDocumentUtil().download(link, path);
		}

		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(path);

		// GeonameAbout geonameAbout = new GeonameAbout();
		geonameAbout.setCode(code);

		try {

			Document document = (Document) builder.build(xmlFile);

			Element rootNode = document.getRootElement();

			@SuppressWarnings("rawtypes")
			List list = ((Element) rootNode.getChildren().get(0)).getChildren();

			for (int i = 0; i < list.size(); i++) {
				Element element = (Element) list.get(i);
				if (element.getName().equals(NameAbout.NAME)) {
					NameAbout name = new NameAbout();
					name.setName(element.getValue());
					name.setLanguageCode(null);
					name.setType(NameAbout.NAME);
					geonameAbout.addName(name);
				} else if (element.getName().equals(NameAbout.ALTERNATE_NAME)) {
					NameAbout name = new NameAbout();
					name.setName(element.getValue());
					if (element.getAttributes().size() > 0) {
						name.setLanguageCode(((org.jdom.Attribute) element
								.getAttributes().get(0)).getValue());
					}
					name.setType(NameAbout.ALTERNATE_NAME);
					geonameAbout.addName(name);
				} else if (element.getName().equals(NameAbout.OFFICIAL_NAME)) {
					NameAbout name = new NameAbout();
					name.setName(element.getValue());
					if (element.getAttributes().size() > 0) {
						name.setLanguageCode(((org.jdom.Attribute) element
								.getAttributes().get(0)).getValue());
					}
					name.setType(NameAbout.OFFICIAL_NAME);
					geonameAbout.addName(name);
				} else if (element.getName().equals(NameAbout.SHORT_NAME)) {
					NameAbout name = new NameAbout();
					name.setName(element.getValue());
					if (element.getAttributes().size() > 0) {
						name.setLanguageCode(((org.jdom.Attribute) element
								.getAttributes().get(0)).getValue());
					}
					name.setType(NameAbout.SHORT_NAME);
					geonameAbout.addName(name);
				} else if (element.getName().equals("lat")) {
					geonameAbout.setLat(Double.valueOf(element.getValue()
							.toString()));
				} else if (element.getName().equals("long")) {
					geonameAbout.setLng(Double.valueOf(element.getValue()
							.toString()));
				} else if (element.getName().equals("seeAlso")) {
					geonameAbout.setDbpediaURL(((org.jdom.Attribute) element
							.getAttributes().get(0)).getValue());
				} else if (element.getName().toLowerCase().endsWith("name")
						&& element.getName().equals(NameAbout.NAME) == false
						&& element.getName().equals(NameAbout.ALTERNATE_NAME) == false
						&& element.getName().equals(NameAbout.OFFICIAL_NAME) == false
						&& element.getName().equals(NameAbout.SHORT_NAME) == false) {
					System.out
							.println(" ......................................  "
									+ element.getValue());
					throw new RuntimeException(element.getValue());
				}

			}

		} catch (IOException io) {
			throw io;
		} catch (JDOMException jdomex) {
			throw jdomex;
		}

		// this.getHtmlDocumentUtil().download(
		// geonameAbout.getDbpediaURL().replace("resource", "data")
		// + ".json", path + ".json");

		

		if(forceDownload || b == false){
			System.out.println(c + " descagas completadas...");
			c++;	
		}
		

		return geonameAbout;

	}

}
